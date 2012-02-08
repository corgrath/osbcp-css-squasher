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
import java.util.List;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;
import com.osbcp.cssparser.Selector;

/**
 * Main logic for merging rules.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

class LogicMerger {

	private StringBuilder log;
	private int nbrOfMerges = 0;

	/**
	 * Creates a new merge logic.
	 * 
	 * @param log For logging.
	 */

	public LogicMerger(final StringBuilder log) {
		this.log = log;
	}

	/**
	 * Main logic.
	 * 
	 * @param rules A list of rules that should be merged.
	 * @return A list of rules that are merged.
	 */

	public List<Rule> merge(final List<Rule> rules) {

		List<Rule> newRules = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Rule ruleWithSaveValues = findBySelectorName(newRules, rule);

			if (ruleWithSaveValues != null) {

				log.append("Merging together '" + rule.getSelectors() + "' and '" + ruleWithSaveValues.getSelectors() + "'.\n");

				for (PropertyValue pv : rule.getPropertyValues()) {

					if (!ruleWithSaveValues.getPropertyValues().contains(pv)) {
						ruleWithSaveValues.addPropertyValue(pv);
					}

				}

				nbrOfMerges++;

			} else {

				newRules.add(rule);

			}

		}

		return newRules;
	}

	/**
	 * Returns the number of merges.
	 * 
	 * @return The number of merges.
	 */

	public int getNumberOfMerges() {
		return nbrOfMerges;
	}

	/**
	 * Finds another rule with the same selectors in the list of rules.
	 *  
	 * @param newRules List of rules.
	 * @param targetRule The rule
	 * @return A rule with the same selectors, or null if none was found.
	 */

	private Rule findBySelectorName(final List<Rule> newRules, final Rule targetRule) {

		for (Rule rule : newRules) {

			if (hasSameSelectors(targetRule, rule)) {
				return rule;
			}

		}

		return null;

	}

	/**
	 * Compares two rules and checks if they have the same selectors.
	 * 
	 * @param r1 A rule.
	 * @param r2 A rule.
	 * @return True if both rules have the same selectors.
	 */

	private boolean hasSameSelectors(final Rule r1, final Rule r2) {

		List<Selector> selectors = r1.getSelectors();

		if (r1.getSelectors().size() != r2.getSelectors().size()) {
			return false;
		}

		for (Selector selector : selectors) {

			if (!r2.getSelectors().contains(selector)) {
				return false;
			}

		}

		return true;

	}

}
