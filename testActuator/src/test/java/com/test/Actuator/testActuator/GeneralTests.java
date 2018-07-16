package com.test.Actuator.testActuator;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class GeneralTests {

	private Map<String, List<String>> data;

	@Before
	public void init() {
		data = new HashMap<>();
		List<String> arrayList = new ArrayList<>();
		arrayList.add("dd1");
		arrayList.add("dd2");
		arrayList.add("dd3");
		arrayList.add("dd4");
		arrayList.add("dd5");
		data.put("first", arrayList);
		arrayList = new ArrayList<>();
		arrayList.add("ff1");
		arrayList.add("ff2");
		arrayList.add("ff3");
		arrayList.add("ff4");
		arrayList.add("ff5");
		data.put("second", arrayList);
	}

	public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
		return map.entrySet().stream().filter(x -> predicate.test(x.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public static <K, V> Map<K, List<V>> filterByValueList(Map<K, List<V>> map, Predicate<V> predicate) {
		Map<K, List<V>> res = new HashMap<>();
		map.forEach((final K key, List<V> value) -> {
			value = value.stream().filter(predicate).collect(Collectors.toList());
			res.put(key, value);
		});
		return res;
	}

	public static Predicate<String> containsOnes = new Predicate<String>() {

		@Override
		public boolean test(String t) {
			return t.contains("1");
		}
		
	};

	public static Predicate<String> containsTwos = new Predicate<String>() {

		@Override
		public boolean test(String t) {
			return t.contains("2");
		}
		
	};

	public static Predicate<String> aggrigation = new Predicate<String>() {

		@Override
		public boolean test(String t) {
			return containsOnes.test(t) || containsTwos.test(t);
		}
		
	};
	
	@Test
	public void filterByValueMap() {
		Map<String, List<String>> filteredMap = filterByValueList(data, x -> aggrigation.test(x));
		filteredMap.forEach((key, value) -> {
			System.out.println("Key : " + key + " Value : " + value);
		});
	}

}
