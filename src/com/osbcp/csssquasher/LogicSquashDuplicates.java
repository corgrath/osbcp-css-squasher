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

/**
 * Main logic for squashing duplicated rules.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

class LogicSquashDuplicates {

	private StringBuilder log;
	private int nbrOfSquashedRules = 0;

	/**
	 * Creates a new squasher.
	 * 
	 * @param log For logging.
	 */

	public LogicSquashDuplicates(final StringBuilder log) {
		this.log = log;
	}

	/**
	 * Main logic.
	 * 
	 * @param rules A list of rules that should be squashed.
	 * @return A list of rules that have been squashed.
	 */

	public List<Rule> squash(final List<Rule> rules) {

		List<Rule> newRules = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Rule ruleWithSaveValues = contains(newRules, rule);

			if (ruleWithSaveValues != null) {

				log.append("Squashing '" + rule.getSelectors() + "' to '" + ruleWithSaveValues.getSelectors() + "'.\n");
				//				System.out.println(rule);
				//				System.out.println(ruleWithSaveValues);
				ruleWithSaveValues.addSelectors(rule.getSelectors());

				nbrOfSquashedRules++;

			} else {

				newRules.add(rule);

			}

		}

		return newRules;
	}

	/**
	 * Returns the number of squashed rules.
	 * 
	 * @return The number of squashed rules.
	 */

	public int getNbrOfSquashedRules() {
		return nbrOfSquashedRules;
	}

	/**
	 * Checks if the list of rules has already a rule that share the same property values as the incoming rule.
	 * 
	 * @param newRules The list of rules.
	 * @param targetRule The rule to check.
	 * @return The rule that share the same property values, null if none was found.
	 */

	private Rule contains(final List<Rule> newRules, final Rule targetRule) {

		for (Rule rule : newRules) {

			if (hasSameValues(targetRule, rule)) {
				return rule;
			}

		}

		return null;

	}

	/**
	 * Checks if two rules share the same property values.
	 * 
	 * @param r1 First rule.
	 * @param r2 Second rule.
	 * @return True if the rules share the same property values.
	 */

	private boolean hasSameValues(final Rule r1, final Rule r2) {

		List<PropertyValue> values = r1.getPropertyValues();

		if (r1.getPropertyValues().size() != r2.getPropertyValues().size()) {
			return false;
		}

		for (PropertyValue value : values) {

			if (!r2.getPropertyValues().contains(value)) {
				return false;
			}

		}

		return true;

	}

}
