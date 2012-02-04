package com.osbcp.squicss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Optimizer {

	private Map<PropertyValue, Set<Selector>> entries = new HashMap<PropertyValue, Set<Selector>>();

	public void register(final PropertyValue propertyValue, final Selector selector) {

		Set<Selector> selectors = entries.get(propertyValue);

		if (selectors == null) {
			selectors = new HashSet<Selector>();
			entries.put(propertyValue, selectors);
		}

		//		if (!selectors.contains(selector)) {

		selectors.add(selector);

		//		}

	}

	//	public Map<Selector, Set<PropertyValue>> convert(final Map<String, Set<String>> stylesheet) {
	//
	//		Map<Selector, Set<PropertyValue>> newTomten = new HashMap<Selector, Set<PropertyValue>>();
	//
	//		for (Entry<String, Set<String>> entry : stylesheet.entrySet()) {
	//
	//			String propertyValueKeyString = entry.getKey();
	//			PropertyValue propertyValue = new PropertyValue(propertyValueKeyString, "");
	//
	//			Set<String> selectors = entry.getValue();
	//
	//			for (String selectorString : selectors) {
	//
	//				Selector selector = new Selector(selectorString);
	//
	//				register2(newTomten, selector, propertyValue);
	//
	//			}
	//
	//		}
	//
	//		return newTomten;
	//
	//	}

	public void print() {

		StringBuilder out = new StringBuilder();

		System.out.println(entries);

		Map<Set<Selector>, Set<PropertyValue>> erik = repack(entries);

		System.out.println(erik);

		for (Entry<Set<Selector>, Set<PropertyValue>> entry : erik.entrySet()) {

			Set<Selector> selectors = entry.getKey();
			Set<PropertyValue> propertyValues = entry.getValue();

			//			for (String selectorString : selectors) {
			//
			//				out.append(selectorString + ",");
			//
			//			}

			out.append(implode(selectors) + " {\n");
			for (PropertyValue propertyValue : propertyValues) {
				out.append("\t" + propertyValue + ";\n");
			}
			out.append("}\n");

		}

		//		for (Entry<PropertyValue, Set<Selector>> entry : entries.entrySet()) {
		//
		//			PropertyValue propertyValue = entry.getKey();
		//			Set<Selector> selectors = entry.getValue();
		//
		//			//			for (String selectorString : selectors) {
		//			//
		//			//				out.append(selectorString + ",");
		//			//
		//			//			}
		//
		//			out.append(implode(selectors) + " {\n");
		//			out.append("\t" + propertyValue + ";\n");
		//			out.append("}\n");
		//
		//		}

		System.out.println(out);

		//		Map<Selector, Set<PropertyValue>> map = convert(entries);

		//		System.out.println(map);

	}

	private Map<Set<Selector>, Set<PropertyValue>> repack(final Map<PropertyValue, Set<Selector>> stylesheet) {

		Map<Set<Selector>, Set<PropertyValue>> newStylesheet = new HashMap<Set<Selector>, Set<PropertyValue>>();

		for (Entry<PropertyValue, Set<Selector>> entry : stylesheet.entrySet()) {

			PropertyValue propertyValue = entry.getKey();
			Set<Selector> selectors = entry.getValue();

			register2(newStylesheet, selectors, propertyValue);

		}

		// TODO Auto-generated method stub
		return newStylesheet;
	}

	public void register2(final Map<Set<Selector>, Set<PropertyValue>> newStylesheet, final Set<Selector> selectors, final PropertyValue propertyValue) {

		Set<PropertyValue> propertyValues = newStylesheet.get(selectors);

		if (propertyValues == null) {
			propertyValues = new HashSet<PropertyValue>();
			newStylesheet.put(selectors, propertyValues);
		}

		propertyValues.add(propertyValue);

	}

	public String implode(final Set<Selector> values) {

		StringBuilder sb = new StringBuilder();

		Iterator<Selector> iterator = values.iterator();

		while (iterator.hasNext()) {

			Selector selector = iterator.next();

			sb.append(selector.toString());

			if (iterator.hasNext()) {
				sb.append(", ");
			}

		}

		return sb.toString();

	}

}
