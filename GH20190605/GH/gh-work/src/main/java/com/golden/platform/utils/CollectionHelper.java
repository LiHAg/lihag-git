package com.golden.platform.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 列表操作帮助类
 */
public class CollectionHelper {
	/**
	 * 将输入对象转换为数组
	 * 
	 * @param input
	 *            输入对象
	 * @return 对象数组
	 */
	public static Object[] toArray(Object input) {
		// 如果输入对象是空则创建一个对象数组返
		if (input == null)
			return new Object[] {};
		// 如果输入对象是数组将其转换为数组返回
		if (input.getClass().isArray())
			return (Object[]) input;
		// 如果输入对象是迭代则创建列表其将迭代对象中的每个对加入列表后转换为数组返回
		if (input instanceof Iterable) {
			List<Object> result = new ArrayList<Object>();
			@SuppressWarnings("unchecked")
			Iterator<Object> iterator = ((Iterable<Object>) input).iterator();
			while (iterator.hasNext()) {
				result.add(iterator.next());
			}
			return result.toArray(new Object[] {});
		}
		return new Object[] { input };
	}

	public static <T> void addAll(Collection<? super T> collection, T[] elements) {
		if (collection == null || elements == null)
			return;
		for (T element : elements)
			collection.add(element);
	}

	public static <T> void addAll(Collection<? super T> collection, Collection<T> elements) {
		if (collection == null || elements == null)
			return;
		collection.addAll(elements);
	}

	public static <T> void addAll(Collection<? super T> collection, Iterator<T> iterator) {
		if (collection == null || iterator == null)
			return;
		while (iterator.hasNext())
			collection.add(iterator.next());
	}

	public static <K, V> void allAll(Map<? super K, ? super V> map, Map<K, V> elements) {
		if (map == null)
			return;
		if (elements == null || elements.size() == 0)
			return;
		map.putAll(elements);
	}

	public static <T> List<T> toValueList(Map<?, T> map) {
		List<T> result = new ArrayList<T>(map.size());
		if (map != null && map.size() > 0)
			result.addAll(map.values());
		return result;
	}

	public static boolean isEmpty(Collection<?> collection) {
		if (collection == null)
			return true;
		return collection.size() == 0;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null)
			return true;
		return map.size() == 0;
	}

	public static boolean isEmpty(Object[] array) {
		if (array == null)
			return true;
		return array.length == 0;
	}

	public static <T> boolean contains(Collection<T> collection, T element) {
		if (collection == null)
			return false;
		return collection.contains(element);
	}

	public static <T> boolean contains(T[] array, T element) {
		Set<T> collection = new HashSet<T>(array.length);
		addAll(collection, array);
		return collection.contains(element);
	}

	public static <T> List<T> splice(List<T> list, int startIndex, int endIndex) {
		if (list == null)
			return null;
		List<T> result = new ArrayList<T>();
		for (int i = startIndex; i < endIndex && i < list.size(); i++) {
			if (i < 0)
				continue;
			result.add(list.get(i));
		}

		return result;
	}

}
