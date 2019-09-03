package com.ciTreat.dicom.services;

import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.net.URL;
import java.net.ConnectException;
import java.security.GeneralSecurityException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.text.ParseException;

import org.dcm4che.data.Command;
import org.dcm4che.data.Dataset;
import org.dcm4che.data.DcmObjectFactory;
import org.dcm4che.data.DcmParserFactory;
import org.dcm4che.dict.DictionaryFactory;
import org.dcm4che.dict.Tags;
import org.dcm4che.dict.UIDDictionary;
import org.dcm4che.dict.UIDs;
import org.dcm4che.net.AAssociateAC;
import org.dcm4che.net.AAssociateRQ;
import org.dcm4che.net.ActiveAssociation;
import org.dcm4che.net.Association;
import org.dcm4che.net.AssociationFactory;
import org.dcm4che.net.Dimse;
import org.dcm4che.net.FutureRSP;
import org.dcm4che.net.PDU;
import org.dcm4che.net.PresContext;
import org.dcm4che.util.DcmURL;
import org.dcm4che.util.SSLContextAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciTreat.dicom.util.ConfigProperties;

/**
 *
 * @author jinma.zheng
 */
public class CDimseService {
	private static final Logger log = LoggerFactory.getLogger(CDimseService.class);
	final static boolean DEBUG = log.isDebugEnabled();
	private static final UIDDictionary uidDict = DictionaryFactory.getInstance().getDefaultUIDDictionary();
	private static final AssociationFactory aFact = AssociationFactory.getInstance();
	private static final DcmObjectFactory oFact = DcmObjectFactory.getInstance();
	private static final DcmParserFactory pFact = DcmParserFactory.getInstance();
	private final static DcmObjectFactory dof = DcmObjectFactory.getInstance();

	public static String DEFAULT_CALLING_AET = "Golden";
	static public final int PATIENT_LEVEL = 0;
	static public final int STUDY_LEVEL = 1;
	static public final int SERIES_LEVEL = 2;
	static public final int IMAGE_LEVEL = 3;

	PresContext pc;
	private DcmURL url = null;
	private int priority = Command.MEDIUM;
	private int acTimeout = 5000;
	private int dimseTimeout = 0;
	private int soCloseDelay = 500;
	private AAssociateRQ assocRQ = aFact.newAAssociateRQ();
	private Association assoc = null;
	private ActiveAssociation aassoc = null;
	private boolean packPDVs = false;
	private SSLContextAdapter tls = null;
	private String[] cipherSuites = null;
	private Dataset keys = dof.newDataset();
	private String dest;

	public CDimseService(ConfigProperties cfg, DcmURL url) throws ParseException {
		System.out.println("cfg=" + cfg.toString());
		this.url = url;
		this.priority = Integer.parseInt(cfg.getProperty("prior", "0"));
		this.packPDVs = "true".equalsIgnoreCase(cfg.getProperty("pack-pdvs", "false"));
		initAssocParam(cfg, url);
		initTLS(cfg);
		initKeys(cfg);
		this.dest = cfg.getProperty("dest");
	}

	private final void initAssocParam(ConfigProperties cfg, DcmURL url) {
		String callingAET = null;
		acTimeout = Integer.parseInt(cfg.getProperty("ac-timeout", "5000"));
		dimseTimeout = Integer.parseInt(cfg.getProperty("dimse-timeout", "0"));
		soCloseDelay = Integer.parseInt(cfg.getProperty("so-close-delay", "500"));
		assocRQ.setCalledAET(url.getCalledAET());
		if (url.getCallingAET() != null) {
			callingAET = url.getCallingAET();
		} else {
			callingAET = DEFAULT_CALLING_AET;
		}
		assocRQ.setCallingAET(callingAET);
		assocRQ.setMaxPDULength(Integer.parseInt(cfg.getProperty("max-pdu-len", "16352")));
		assocRQ.setAsyncOpsWindow(aFact.newAsyncOpsWindow(Integer.parseInt(cfg.getProperty("max-op-invoked", "0")), 1));

		for (Enumeration it = cfg.keys(); it.hasMoreElements();) {
			String key = (String) it.nextElement();
			if (key.startsWith("pc.")) {
				initPresContext(Integer.parseInt(key.substring(3)),
						cfg.tokenize(cfg.getProperty(key), new LinkedList()));
			}
		}
	}

	private final void initPresContext(int pcid, List val) {
		Iterator it = val.iterator();
		String as = UIDs.forName((String) it.next());
		System.out.println("pcid=" + pcid + "|as=" + as);
		String[] tsUIDs = new String[val.size() - 1];
		for (int i = 0; i < tsUIDs.length; ++i) {
			tsUIDs[i] = UIDs.forName((String) it.next());
		}
		assocRQ.addPresContext(aFact.newPresContext(pcid, as, tsUIDs));
	}

	private void initTLS(ConfigProperties cfg) throws ParseException {
		char[] keystorepasswd;
		char[] keypasswd;
		char[] cacertspasswd;
		URL keyURL;
		URL cacertURL;
		String value;

		try {
			cipherSuites = url.getCipherSuites();
			if (cipherSuites == null) {
				return;
			}

			tls = SSLContextAdapter.getInstance();
			keystorepasswd = cfg.getProperty("tls-keystore-passwd", "secret").toCharArray();
			keypasswd = cfg.getProperty("tls-key-passwd", "secret").toCharArray();
			keyURL = CDimseService.class.getResource("/resources/identityJava.jks");
			if ((value = cfg.getProperty("tls-key")) != null) {
				try {
					keyURL = ConfigProperties.fileRefToURL(CDimseService.class.getResource(""), value);
				} catch (Exception e) {
					log.warn("Wrong value for tls-key: " + value + ". tls-key was set to default value.");
				}
			}
			tls.setKey(tls.loadKeyStore(keyURL, keystorepasswd), keypasswd);
			cacertspasswd = cfg.getProperty("tls-cacerts-passwd", "secret").toCharArray();
			cacertURL = CDimseService.class.getResource("/resources/identityJava.jks");
			if ((value = cfg.getProperty("tls-cacerts")) != null) {
				try {
					cacertURL = ConfigProperties.fileRefToURL(CDimseService.class.getResource(""), value);
				} catch (Exception e) {
					log.warn("Wrong value for tls-cacerts: " + value + ". tls-cacerts was set to default value.");
				}
			}
			tls.setTrust(tls.loadKeyStore(cacertURL, cacertspasswd));
			tls.init();
		} catch (Exception ex) {
			throw new ParseException("Could not initalize TLS configuration.", 0);
		}
	}

	private void initKeys(ConfigProperties cfg) throws ParseException {
		keys = dof.newDataset();
		keys.putCS(Tags.QueryRetrieveLevel, getQueryRetrieveLevel(STUDY_LEVEL));
		keys.putUI(Tags.StudyInstanceUID);
		keys.putDA(Tags.StudyDate);
		keys.putPN(Tags.PatientName);
		keys.putLO(Tags.PatientID);
		keys.putUS(Tags.NumberOfStudyRelatedSeries);
		keys.putUS(Tags.NumberOfStudyRelatedInstances);
		keys.putUS(Tags.InstitutionName);
		addQueryKeys(cfg);
	}

	private void addQueryKeys(ConfigProperties cfg) throws ParseException {
		for (Enumeration it = cfg.keys(); it.hasMoreElements();) {
			String key = (String) it.nextElement();
			if (key.startsWith("key.")) {
				try {
					keys.putXX(Tags.forName(key.substring(4)), cfg.getProperty(key));
				} catch (Exception e) {
					throw new ParseException("Illegal entry in configuration filr: " + key + "=" + cfg.getProperty(key),
							0);
				}
			}
		}
	}

	public void setQueryKeys(Dataset ds) {
		keys = ds;
	}

	public void setQueryKeys(ConfigProperties cfg) throws ParseException {
		keys = dof.newDataset();
		addQueryKeys(cfg);
	}

	public boolean aASSOCIATE() throws ConnectException, IOException, GeneralSecurityException {

		// No association may be active
		if (assoc != null) {
			throw new ConnectException("Association already established");
		}

		// New Association object for establishing an active association
		assoc = aFact.newRequestor(newSocket(url.getHost(), url.getPort()));

		// >>>> Fill the Association object with relevant data
		assoc.setAcTimeout(acTimeout);
		assoc.setDimseTimeout(dimseTimeout);
		assoc.setSoCloseDelay(soCloseDelay);
		assoc.setPackPDVs(packPDVs);

		PDU assocAC = assoc.connect(assocRQ);

		if (!(assocAC instanceof AAssociateAC)) {
			assoc = null;
			return false;
		}
		aassoc = aFact.newActiveAssociation(assoc, null);
		aassoc.start();
		return true;
	}

	private Socket newSocket(String host, int port) throws IOException, GeneralSecurityException {
		if (cipherSuites != null) {
			return tls.getSocketFactory(cipherSuites).createSocket(host, port);
		} else {
			System.out.println("host=" + host + "|port=" + port);
			return new Socket(host, port);
		}
	}

	public void aRELEASE(boolean waitOnRSP) throws InterruptedException, IOException {
		if (assoc != null) {
			try {
				aassoc.release(waitOnRSP);
			} finally {
				assoc = null;
				aassoc = null;
			}
		}
	}

	public void cSTORE(Dataset ds) throws InterruptedException, IOException, ConnectException, ParseException {
		String sopClassUID;
		String sopInstUID;
		PresContext pc = null;
		if (aassoc == null) {
			throw new ConnectException("No Association established");
		}
		if ((sopClassUID = ds.getString(Tags.SOPClassUID)) == null) {
			throw new ParseException("No SOP Class UID in Dataset", 0);
		}
		if ((sopInstUID = ds.getString(Tags.SOPInstanceUID)) == null) {
			throw new ParseException("No SOP Instance UID in Dataset", 0);
		}

		if ((pc = aassoc.getAssociation().getAcceptedPresContext(sopClassUID, UIDs.ImplicitVRLittleEndian)) == null
				&& (pc = aassoc.getAssociation().getAcceptedPresContext(sopClassUID,
						UIDs.ExplicitVRLittleEndian)) == null
				&& (pc = aassoc.getAssociation().getAcceptedPresContext(sopClassUID,
						UIDs.ExplicitVRBigEndian)) == null) {

			throw new ConnectException("No applicable presentation context found");
		}

		Command cStoreRQ = oFact.newCommand();
		cStoreRQ.initCStoreRQ(assoc.nextMsgID(), sopClassUID, sopInstUID, priority);
		Dimse storeRq = aFact.newDimse(pc.pcid(), cStoreRQ, ds);
		FutureRSP future = aassoc.invoke(storeRq);
		Dimse storeRsp = future.get();
		Command rspCmd = storeRsp.getCommand();
		int status = rspCmd.getStatus();
		switch (status) {
		case 0x0000:
			break;
		default:
			log.error("C-STORE failed: " + Integer.toHexString(status));
			break;
		}

	}

	public Vector cGET(Dataset ds) throws ConnectException, IOException, InterruptedException {
		PresContext pc;
		List dimseList;
		Vector datasetVector;
		if (aassoc == null) {
			throw new ConnectException("No Association established");
		}
		if ((pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelGET,
				UIDs.ExplicitVRLittleEndian)) == null
				&& (pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelGET,
						UIDs.ImplicitVRLittleEndian)) == null) {
			throw new ConnectException(
					"Association does not support presentation context for StudyRootQueryRetrieveInformationModelMOVE SOP.");
		}
		String suid = ds.getString(Tags.SOPInstanceUID);
		String patName = ds.getString(Tags.PatientName);
		String patID = ds.getString(Tags.PatientID);
		String studyDate = ds.getString(Tags.StudyDate);
		String prompt = "Study[" + suid + "] from " + studyDate + " for Patient[" + patID + "]: " + patName;
		Command rqCmd = dof.newCommand();
		rqCmd.initCGetRSP(assoc.nextMsgID(), UIDs.StudyRootQueryRetrieveInformationModelGET, priority);
		Dataset rqDs = dof.newDataset();
		rqDs.putCS(Tags.QueryRetrieveLevel, getQueryRetrieveLevel(STUDY_LEVEL));
		rqDs.putUI(Tags.SOPInstanceUID, suid);
		Dimse moveRq = aFact.newDimse(pc.pcid(), rqCmd, rqDs);
		FutureRSP future = aassoc.invoke(moveRq);
		Dimse moveRsp = future.get();
		Command rspCmd = moveRsp.getCommand();

		if (DEBUG) {
			StringWriter w = new StringWriter();
			w.write("C-FIND RQ Identifier:\n");
			keys.dumpDataset(w, null);
			log.debug(w.toString());
		}
		dimseList = future.listPending();
		datasetVector = new Vector();

		if (dimseList == null || dimseList.isEmpty()) {
			return datasetVector;
		}

		for (int i = 0; i < dimseList.size(); i++) {
			datasetVector.addElement(((Dimse) dimseList.get(i)).getDataset());
		}

		int status = rspCmd.getStatus();
		switch (status) {
		case 0x0000:
			break;
		case 0xB000:
			log.error("One or more failures during move of " + prompt);
			break;
		default:
			log.error("Failed to move " + prompt + "\n\terror tstatus: " + Integer.toHexString(status));
			break;
		}
		return datasetVector;
	}

	public Vector cFIND() throws ConnectException, IOException, InterruptedException {

		List dimseList;
		Vector datasetVector;

		if (aassoc == null) {
			throw new ConnectException("No Association established");
		}

		if ((pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelFIND,
				UIDs.ExplicitVRLittleEndian)) == null
				&& (pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelFIND,
						UIDs.ImplicitVRLittleEndian)) == null) {
			throw new ConnectException(
					"Association does not support presentation context for StudyRootQueryRetrieveInformationModelFIND SOP.");
		}

		Command rqCmd = dof.newCommand();
		rqCmd.initCFindRQ(assoc.nextMsgID(), UIDs.StudyRootQueryRetrieveInformationModelFIND, priority);
		Dimse findRq = aFact.newDimse(pc.pcid(), rqCmd, keys);
		if (DEBUG) {
			StringWriter w = new StringWriter();
			w.write("C-FIND RQ Identifier:\n");
			keys.dumpDataset(w, null);
			log.debug(w.toString());
		}

		FutureRSP future = aassoc.invoke(findRq);
		Dimse findRsp = future.get();
		dimseList = future.listPending();
		datasetVector = new Vector();

		if (dimseList == null || dimseList.isEmpty()) {
			return datasetVector;
		}

		for (int i = 0; i < dimseList.size(); i++) {
			datasetVector.addElement(((Dimse) dimseList.get(i)).getDataset());
		}

		return datasetVector;
	}

	public Vector cMOVE(Dataset ds) throws ConnectException, InterruptedException, IOException {
		PresContext pc;
		List dimseList;
		Vector datasetVector;

		if (aassoc == null) {
			throw new ConnectException("No Association established");
		}

		if ((pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelMOVE,
				UIDs.ExplicitVRLittleEndian)) == null
				&& (pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.StudyRootQueryRetrieveInformationModelMOVE,
						UIDs.ImplicitVRLittleEndian)) == null) {
			throw new ConnectException(
					"Association does not support presentation context for StudyRootQueryRetrieveInformationModelMOVE SOP.");
		}

		String suid = ds.getString(Tags.StudyInstanceUID);

		String patName = ds.getString(Tags.PatientName);
		String patID = ds.getString(Tags.PatientID);
		String studyDate = ds.getString(Tags.StudyDate);
		String prompt = "Study[" + suid + "] from " + studyDate + " for Patient[" + patID + "]: " + patName;

		Command rqCmd = dof.newCommand();
		rqCmd.initCMoveRQ(assoc.nextMsgID(), UIDs.StudyRootQueryRetrieveInformationModelMOVE, priority, dest);
		Dataset rqDs = dof.newDataset();
		rqDs.putCS(Tags.QueryRetrieveLevel, getQueryRetrieveLevel(STUDY_LEVEL));
		rqDs.putUI(Tags.StudyInstanceUID, suid);

		Dimse moveRq = aFact.newDimse(pc.pcid(), rqCmd, rqDs);

		FutureRSP future = aassoc.invoke(moveRq);
		Dimse moveRsp = future.get();
		Command rspCmd = moveRsp.getCommand();
		Dataset dds = moveRsp.getDataset();

		if (DEBUG) {
			StringWriter w = new StringWriter();
			w.write("C-FIND RQ Identifier:\n");
			keys.dumpDataset(w, null);
			log.debug(w.toString());
		}

		Dimse dms = future.get();

		datasetVector = new Vector();

		int status = rspCmd.getStatus();
		switch (status) {
		case 0x0000:
			// log.info("Moved: " + prompt);
			break;
		case 0xB000:
			log.error("One or more failures during move of " + prompt);
			break;
		default:
			log.error("Failed to move " + prompt + "\n\terror tstatus: " + Integer.toHexString(status));
			break;
		}
		return datasetVector;
	}

	public long cECHO() throws ConnectException, InterruptedException, IOException {
		PresContext pc;
		long t1 = System.currentTimeMillis();

		if (aassoc == null) {
			throw new ConnectException("No Association established");
		}

		if ((pc = aassoc.getAssociation().getAcceptedPresContext(UIDs.Verification,
				UIDs.ImplicitVRLittleEndian)) == null) {
			throw new ConnectException(
					"Association does not support presentation context: Verification SOP/ImplicitVRLittleEndian.");
		}

		Command cEchoRQ = oFact.newCommand();
		cEchoRQ.initCEchoRQ(1);

		Dimse echoRq = aFact.newDimse(pc.pcid(), cEchoRQ);

		FutureRSP future = aassoc.invoke(echoRq);

		Dimse echoRsp = future.get();
		Command rspCmd = echoRsp.getCommand();

		int status = rspCmd.getStatus();
		switch (status) {
		case 0x0000:
			// Success
			break;
		default:
			log.error("C-ECHO failed: " + Integer.toHexString(status));
			break;
		}

		return System.currentTimeMillis() - t1;
	}

	static public String getQueryRetrieveLevel(int queryRetrieveLevel) {
		switch (queryRetrieveLevel) {
		case PATIENT_LEVEL:
			return "PATIENT";
		case STUDY_LEVEL:
			return "STUDY";
		case SERIES_LEVEL:
			return "SERIES";
		case IMAGE_LEVEL:
			return "IMAGE";
		default:
			return "";
		}
	}
}
