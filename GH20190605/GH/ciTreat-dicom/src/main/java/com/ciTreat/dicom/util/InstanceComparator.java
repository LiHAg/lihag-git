package com.ciTreat.dicom.util;

import java.util.Comparator;
import com.ciTreat.dicom.model.InstanceModel;

/**
 *
 * @author jinma.zheng
 */
public class InstanceComparator implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		InstanceModel im1 = (InstanceModel) o1;
		InstanceModel im2 = (InstanceModel) o2;

		if (im1.getInstanceNumber() == null) {
			return (-1);
		} else if (im2.getInstanceNumber() == null) {
			return (1);
		} else if (im1.getInstanceNumber().indexOf("+") >= 0 || im1.getInstanceNumber().indexOf("-") >= 0
				|| im2.getInstanceNumber().indexOf("+") >= 0 || im2.getInstanceNumber().indexOf("-") >= 0) {
			return (im1.getInstanceNumber().compareTo(im2.getInstanceNumber()));
		} else {
			int a = Integer.parseInt(im1.getInstanceNumber().trim());
			int b = Integer.parseInt(im2.getInstanceNumber().trim());
			int t = (a == b ? 0 : (a > b ? 1 : -1));
			return t;
		}
	}

}