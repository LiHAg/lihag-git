
package com.ciTreat.dicom.util;

import java.util.Comparator;

import com.ciTreat.dicom.model.SeriesModel;

/**
 *
 * @author jinma.zheng
 */
public class SeriesComparator implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		SeriesModel sm1 = (SeriesModel) o1;
		SeriesModel sm2 = (SeriesModel) o2;

		if (sm1.getSeriesNumber() == null || sm1.getSeriesNumber().equals("unknown")) {
			return (-1);
		} else if (sm2.getSeriesNumber() == null || sm2.getSeriesNumber().equals("unknown")) {
			return (1);
		} else if (sm1.getSeriesNumber().indexOf("+") >= 0 || sm1.getSeriesNumber().indexOf("-") >= 0
				|| sm2.getSeriesNumber().indexOf("+") >= 0 || sm2.getSeriesNumber().indexOf("-") >= 0) {
			return (sm1.getSeriesNumber().compareTo(sm2.getSeriesNumber()));
		} else {
			int a = Integer.parseInt(sm1.getSeriesNumber());
			int b = Integer.parseInt(sm2.getSeriesNumber());
			int t = (a == b ? 0 : (a > b ? 1 : -1));
			return t;
		}
	}

}