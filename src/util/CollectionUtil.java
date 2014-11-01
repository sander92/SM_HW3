package util;

import java.util.List;

public class CollectionUtil {
	public static double average(List<? extends Number> list) {
		double sum = 0;
		for (Number i : list) {
			sum += i.doubleValue();
		}
		return sum / list.size();
	}
}
