package com.golden.workflow.common;

public abstract class AbstractInfo {
	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		return super.toString();
	}

	public boolean equals(Object o) {
		return super.equals(o);
	}

	public int compareTo(AbstractInfo info) {
		return 0;
	}

	public String intArrayToString(int[] a) {
		StringBuffer buf = new StringBuffer(",");
		for (int i = 0; i < a.length; i++) {
			buf.append(a[i] + ",");
		}
		return buf.toString();
	}

	public int[] stringToIntArray(String str) {
		if (str == null || str.equals(""))
			return null;
		String[] strArray = str.split(",");
		if (strArray.length <= 0)
			return null;
		int[] intArray = new int[strArray.length - 1];
		for (int i = 0; i < intArray.length; i++)
			intArray[i] = Integer.parseInt(strArray[i + 1]);
		return intArray;
	}
}