/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 */

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

/**
 * Main logic for refactoring rules.
 * 
 * Meaning moving property values between rules to minimize the size.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

class LogicRefactor {

	private final StringBuilder log;
	private Map<PropertyValue, List<List<Selector>>> entries = new LinkedHashMap<PropertyValue, List<List<Selector>>>();
	private int fixed;

	/**
	 * Creates the refactor.
	 * @param log For logging.
	 */

	public LogicRefactor(final StringBuilder log) {
		this.log = log;
	}

	/**
	 * Main logic.
	 * 
	 * @param rules List of rules that should be refactored.
	 * @return A List of rules that are refactored.
	 */

	public List<Rule> refactor(final List<Rule> rules) {

		for (Rule rule : rules) {

			for (PropertyValue propertyValue : rule.getPropertyValues()) {

				register(propertyValue, rule.getSelectors());

			}

		}

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

	/**
	 * Deletes property values from all rules that may have those values.
	 * 
	 * @param rules A list of rules to go through.
	 * @param propertyValue Property value that should be removed from the rules.
	 */

	private void deletePropertyValueFromSelectors(final List<Rule> rules, final PropertyValue propertyValue) {

		final List<Rule> rulesToBeRemoved = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Set<PropertyValue> values = new HashSet<PropertyValue>(rule.getPropertyValues());

			for (PropertyValue pv : values) {

				if (pv.equals(propertyValue)) {
					rule.removePropertyValue(pv);
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

	/**
	 * Get the total number of selectors.
	 * 
	 * @param selectors The nested list of selectors to search through. 
	 * @return The total number of selectors.
	 */

	private int getSelectorNameLength(final List<List<Selector>> selectors) {

		int length = 0;

		for (List<Selector> s1 : selectors) {
			for (Selector s2 : s1) {

				length += s2.toString().length();
			}
		}

		return length;
	}

	/**
	 * Registers selectors associated with a property value.
	 * 
	 * @param propertyValue The property value.
	 * @param selector The selectors that should be registered.
	 */

	public void register(final PropertyValue propertyValue, final List<Selector> selector) {

		List<List<Selector>> selectors = entries.get(propertyValue);

		if (selectors == null) {
			selectors = new ArrayList<List<Selector>>();
			entries.put(propertyValue, selectors);
		}

		selectors.add(selector);

	}

	/**
	 * Returns the number of refactored rules.
	 * 
	 * @return The number of refactored rules.
	 */

	public int getNumberOfRefactored() {
		return fixed;
	}

}
