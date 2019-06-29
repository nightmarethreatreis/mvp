package nightmarethreatreis.com.github.mvp.utils;

import java.util.List;
import java.util.stream.Collectors;

public class GeneralUtils {
	public static final <T> List<T> firstListWithoutTheOther(List<T> a, List<T> b) {
		return a.parallelStream().filter(el -> !b.contains(el)).collect(Collectors.toList());
	}
}
