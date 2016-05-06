package com.ashtav.dangerousdave.framework;

import java.util.Collection;

public class Utils {

	public static void addAll(Collection<Object> array, Collection<? extends Object> elements) {
		array.addAll(elements);
	}

	public static double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180.0;
	}

	public static String leadingZeros(Integer value, Integer size) {
		String result = "";
		for (Integer i = 0; i < size - value.toString().length(); i++) {
			result += "0";
		}
		result += value.toString();
		return result;
	}
}