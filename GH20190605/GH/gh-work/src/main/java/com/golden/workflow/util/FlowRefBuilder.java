package com.golden.workflow.util;

import java.sql.Timestamp;

/**
 * @author Administrator
 *
  * Window - Preferences - Java - Code Style - Code Templates
 */
public class FlowRefBuilder {
    //private static FlowRefBuilder m_ProcessUUID;
    private static long PROCESS_SERIAL_NUM = 1;
    private static long BIGPROCESS_SERIAL_NUM = 1;
    private static long INSTANCE_NODE_SERIAL_NUM = 1;
    private static long BIGINSTANCE_NODE_SERIAL_NUM = 1;
    
    /*
     * 这个地方生成的主键会有线程问题
     * 所以加上以下几把锁进行线程同步
     * 以后再优化
     * by z3y2
     * 2011.11.17
     */
    private static final byte[] ProcessSerialLock = new byte[0];
    private static final byte[] BigProcessSerialLock = new byte[0];
    private static final byte[] BigInstanceNodeSerialLock = new byte[0];
    private static final byte[] InstanceNodeSerialLock = new byte[0];
     
//  add by lzy 20091209 
    public static String getProcessId(String orgId,String busType) throws Exception {
    	String processNo = "";
        //if(orgId == null) 
        orgId = "";
        if(busType == null) busType = "";
        try {  
            String sNo = "";
            synchronized (ProcessSerialLock) {
	            if(String.valueOf(PROCESS_SERIAL_NUM).length() == 1) 
	                sNo = "00" + PROCESS_SERIAL_NUM;
	            else if(String.valueOf(PROCESS_SERIAL_NUM).length() == 2)
	                sNo = "0" + PROCESS_SERIAL_NUM; 
	            else if(String.valueOf(PROCESS_SERIAL_NUM).length() == 3)
	                sNo = "" + PROCESS_SERIAL_NUM; 
	            processNo = (new Timestamp(System.currentTimeMillis()) + "").toString();
	            processNo = processNo.replaceAll("-","").replaceAll(" ","").replaceAll(":","").replaceAll("\\.","").substring(0,14) ;
	            processNo  ="WF" + orgId   + processNo + sNo;
	        	FlowRefBuilder.PROCESS_SERIAL_NUM ++;
	        	if(FlowRefBuilder.PROCESS_SERIAL_NUM >= 1000) {
	        		FlowRefBuilder.PROCESS_SERIAL_NUM  = 1;
	        	}
            }
            return processNo;
        } catch ( Exception E) {
            throw E;
        }
    }    

    public static String getBigProcessId( ) throws Exception {
    	String processNo = "";
        
        try {  
        	synchronized (BigProcessSerialLock) {
	            String sNo = "" + BIGPROCESS_SERIAL_NUM;
	            if(String.valueOf(BIGPROCESS_SERIAL_NUM).length() == 1) 
	                sNo = "00" + BIGPROCESS_SERIAL_NUM;
	            else if(String.valueOf(BIGPROCESS_SERIAL_NUM).length() == 2)
	                sNo = "0" + BIGPROCESS_SERIAL_NUM; 
	            processNo = (new Timestamp(System.currentTimeMillis()) + "").toString();
	            processNo = processNo.replaceAll("-","").replaceAll(" ","").replaceAll(":","").replaceAll("\\.","").substring(0,14) ;
	            processNo  ="BWF" + processNo + sNo;
	        	FlowRefBuilder.BIGPROCESS_SERIAL_NUM ++;
	        	if(FlowRefBuilder.BIGPROCESS_SERIAL_NUM >= 1000) FlowRefBuilder.BIGPROCESS_SERIAL_NUM  = 1;
        	}
            return processNo;
        } catch ( Exception E) {
            throw E;
        }
    }    
    
    public static String getInstanceNodeId() throws Exception {
    	String instanceNodeId = "";
        
        try {  
        	synchronized (InstanceNodeSerialLock) {
        		String sNo = "" + INSTANCE_NODE_SERIAL_NUM;
                if(String.valueOf(INSTANCE_NODE_SERIAL_NUM).length() == 1) 
                    sNo = "00" + INSTANCE_NODE_SERIAL_NUM;
                else if(String.valueOf(INSTANCE_NODE_SERIAL_NUM).length() == 2)
                    sNo = "0" + INSTANCE_NODE_SERIAL_NUM; 
            	instanceNodeId = (new Timestamp(System.currentTimeMillis()) + "").toString();
            	instanceNodeId = instanceNodeId.replaceAll("-","").replaceAll(" ","").replaceAll(":","").replaceAll("\\.","").substring(0,14);
            	
            	instanceNodeId  = "NODE" + instanceNodeId + sNo;
            	FlowRefBuilder.INSTANCE_NODE_SERIAL_NUM ++;
            	if(FlowRefBuilder.INSTANCE_NODE_SERIAL_NUM >= 1000) FlowRefBuilder.INSTANCE_NODE_SERIAL_NUM = 1;
			}
            return instanceNodeId;
        } catch ( Exception E) {
            throw E;
        }
    }      
    
    public static String getBigInstanceNodeId() throws Exception {
    	String instanceNodeId = "";
        
        try {  
        	synchronized (BigInstanceNodeSerialLock) {
	            String sNo = "" + BIGINSTANCE_NODE_SERIAL_NUM;
	            if(String.valueOf(BIGINSTANCE_NODE_SERIAL_NUM).length() == 1) 
	                sNo = "00" + BIGINSTANCE_NODE_SERIAL_NUM;
	            else if(String.valueOf(BIGINSTANCE_NODE_SERIAL_NUM).length() == 2)
	                sNo = "0" + BIGINSTANCE_NODE_SERIAL_NUM; 
	        	instanceNodeId = (new Timestamp(System.currentTimeMillis()) + "").toString();
	        	instanceNodeId = instanceNodeId.replaceAll("-","").replaceAll(" ","").replaceAll(":","").replaceAll("\\.","").substring(0,14);
	        	
	        	instanceNodeId  = "BNODE" + instanceNodeId + sNo;
	        	FlowRefBuilder.BIGINSTANCE_NODE_SERIAL_NUM ++;
	        	if(FlowRefBuilder.BIGINSTANCE_NODE_SERIAL_NUM >= 1000) FlowRefBuilder.BIGINSTANCE_NODE_SERIAL_NUM = 1;
        	}
            return instanceNodeId;
        } catch ( Exception E) {
            throw E;
        }
    }      
    
    public static void main(String [] argv)
    {
    	try {
			// String timestr = (new Timestamp(System.currentTimeMillis()) +
			// "").toString();
			// System.err.println("time==" + timestr);
			// timestr =
			// timestr.replaceAll("-","").replaceAll(" ","").replaceAll(":","");
			// System.err.println("time==" + timestr.substring(0,8));
			// System.err.println("sss==" +
			// timestr.replaceAll("-","").replaceAll(" ","").replaceAll("\\.","").replaceAll(":","").substring(0,14));
		    for(int i=0; i<1020; i++)
		        System.err.println( getProcessId("","") + ":" + i);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}