package com.test.Actuator.testActuator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.Actuator.testActuator.utils.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralTests implements BaseTest {

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

	public static List<String> cleanUp(List<String> names, String toRemove) {
		List<String> res = new ArrayList<>();
		res = names.stream().map(t -> t.replaceAll(toRemove, "")).collect(Collectors.toList());
		return res;
	}

	@Test
	public void testCleanUp() {
		List<String> res = new ArrayList<>();
		res.add("shshssh_Start");
		res.add("shshssh_start");
		res.add("shshssh_Stop");
		res.add("shshssh");
		List<String> cleanUp = cleanUp(res, "_Start");

		cleanUp.stream().forEach((t) -> {
			System.out.println(t);
		});

		Assert.assertEquals("shshssh", cleanUp.get(0));
		Assert.assertEquals("shshssh_start", cleanUp.get(1));
		Assert.assertEquals("shshssh_Stop", cleanUp.get(2));
		Assert.assertEquals("shshssh", cleanUp.get(3));

	}

	public static Predicate<String> containsOnes = (Predicate<String>) t -> t.contains("1");

	public static Predicate<String> containsTwos = (Predicate<String>) t -> t.contains("2");

	public static Predicate<String> aggrigation = t -> containsOnes.test(t) || containsTwos.test(t);

	@Test
	public void filterByValueMap() {
		Map<String, List<String>> filteredMap = filterByValueList(data, x -> aggrigation.test(x));
		filteredMap.forEach((key, value) -> {
			System.out.println("Key : " + key + " Value : " + value);
		});
		Assert.assertTrue(filteredMap.containsKey("first"));
		Assert.assertTrue(filteredMap.containsKey("second"));
		Assert.assertTrue(filteredMap.get("first").get(0).equals("dd1"));
		Assert.assertTrue(filteredMap.get("first").get(1).equals("dd2"));
		Assert.assertTrue(filteredMap.get("second").get(0).equals("ff1"));
		Assert.assertTrue(filteredMap.get("second").get(1).equals("ff2"));
	}

}
