package com.osbcp.squicss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Selector;

public class Optimizer {

	private Map<PropertyValue, List<Selector>> entries = new LinkedHashMap<PropertyValue, List<Selector>>();

	public void register(final PropertyValue propertyValue, final Selector selector) {

		List<Selector> selectors = entries.get(propertyValue);

		if (selectors == null) {
			selectors = new ArrayList<Selector>();
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

		Map<List<Selector>, List<PropertyValue>> erik = repack(entries);

		System.out.println(erik);

		for (Entry<List<Selector>, List<PropertyValue>> entry : erik.entrySet()) {

			List<Selector> selectors = entry.getKey();
			List<PropertyValue> propertyValues = entry.getValue();

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

	private Map<List<Selector>, List<PropertyValue>> repack(final Map<PropertyValue, List<Selector>> stylesheet) {

		Map<List<Selector>, List<PropertyValue>> newStylesheet = new HashMap<List<Selector>, List<PropertyValue>>();

		for (Entry<PropertyValue, List<Selector>> entry : stylesheet.entrySet()) {

			PropertyValue propertyValue = entry.getKey();
			List<Selector> selectors = entry.getValue();

			register2(newStylesheet, selectors, propertyValue);

		}

		// TODO Auto-generated method stub
		return newStylesheet;
	}

	public void register2(final Map<List<Selector>, List<PropertyValue>> newStylesheet, final List<Selector> selectors, final PropertyValue propertyValue) {

		List<PropertyValue> propertyValues = newStylesheet.get(selectors);

		if (propertyValues == null) {
			propertyValues = new ArrayList<PropertyValue>();
			newStylesheet.put(selectors, propertyValues);
		}

		propertyValues.add(propertyValue);

	}

	public String implode(final List<Selector> values) {

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
