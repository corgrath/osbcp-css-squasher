package com.osbcp.csssquasher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;
import com.osbcp.cssparser.Selector;

class LogicRefactor {

	private final StringBuilder log;
	private Map<PropertyValue, List<List<Selector>>> entries = new LinkedHashMap<PropertyValue, List<List<Selector>>>();
	private int fixed;

	public LogicRefactor(final StringBuilder log) {
		this.log = log;
	}

	public List<Rule> refactor(final List<Rule> rules) {

		for (Rule rule : rules) {

			for (PropertyValue propertyValue : rule.getPropertyValues()) {

				//				for (Selector selector : ) {

				register(propertyValue, rule.getSelectors());

				//				}

			}

		}

		//		Map<Selector, Set<PropertyValue>> newTomten = new HashMap<Selector, Set<PropertyValue>>();

		for (Entry<PropertyValue, List<List<Selector>>> entry : entries.entrySet()) {

			PropertyValue propertyValueKeyString = entry.getKey();
			List<List<Selector>> selectors = entry.getValue();

			if (selectors.size() < 2) {
				continue;
			}

			int selectorNameLength = getSelectorNameLength(selectors);

			// If the size is ok, create a new rule
			if (selectorNameLength < propertyValueKeyString.toString().length()) {

				//				System.out.println("property=" + propertyValueKeyString);
				//				System.out.println("selectors=" + selectors);
				log.append("Refactoring the property '" + propertyValueKeyString + "'.\n");
				fixed++;

				deletePropertyValueFromSelectors(rules, propertyValueKeyString);

				//				selectors.add(new Selector("LOLZ"));

				List<Selector> mmm = new ArrayList<Selector>();
				for (List<Selector> ss : selectors) {
					for (Selector s : ss) {
						mmm.add(s);
					}
				}

				Rule rule = new Rule(mmm);
				rule.addPropertyValue(propertyValueKeyString);

				rules.add(rule);

			}

		}

		return rules;

	}

	private void deletePropertyValueFromSelectors(final List<Rule> rules, final PropertyValue propertyValueKeyString) {

		final List<Rule> rulesToBeRemoved = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Set<PropertyValue> values = new HashSet<PropertyValue>(rule.getPropertyValues());

			for (PropertyValue propertyValue : values) {

				if (propertyValue.equals(propertyValueKeyString)) {
					rule.removePropertyValue(propertyValue);
				}

			}

			if (rule.getPropertyValues().size() == 0) {
				rulesToBeRemoved.add(rule);
			}

		}

		for (Rule rule : rulesToBeRemoved) {
			rules.remove(rule);
		}

	}

	private int getSelectorNameLength(final List<List<Selector>> selectors) {

		int length = 0;

		for (List<Selector> s1 : selectors) {
			for (Selector s2 : s1) {

				length += s2.toString().length();
			}
		}

		return length;
	}

	public void register(final PropertyValue propertyValue, final List<Selector> selector) {

		List<List<Selector>> selectors = entries.get(propertyValue);

		if (selectors == null) {
			selectors = new ArrayList<List<Selector>>();
			entries.put(propertyValue, selectors);
		}

		//		if (!selectors.contains(selector)) {

		selectors.add(selector);

		//		}

	}

	public int getNumberOfRefactored() {
		return fixed;
	}

}
