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

class LogicSquashDuplicates {

	private StringBuilder log;
	private int fixedDuplications = 0;

	public LogicSquashDuplicates(final StringBuilder log) {
		this.log = log;
	}

	public List<Rule> packDuplicates(final List<Rule> rules) {

		List<Rule> newRules = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Rule ruleWithSaveValues = contains(newRules, rule);

			if (ruleWithSaveValues != null) {

				log.append("Squashing '" + rule.getSelectors() + "' to '" + ruleWithSaveValues.getSelectors() + "'.\n");
				//				System.out.println(rule);
				//				System.out.println(ruleWithSaveValues);
				ruleWithSaveValues.addSelectors(rule.getSelectors());

				fixedDuplications++;

			} else {

				newRules.add(rule);

			}

		}

		return newRules;
	}

	public int getNumberOfSquashed() {
		return fixedDuplications;
	}

	private Rule contains(final List<Rule> newRules, final Rule targetRule) {

		for (Rule rule : newRules) {

			if (hasSameValues(targetRule, rule)) {
				return rule;
			}

		}

		return null;

	}

	private boolean hasSameValues(final Rule targetRule, final Rule rule) {

		List<PropertyValue> values = targetRule.getPropertyValues();

		if (targetRule.getPropertyValues().size() != rule.getPropertyValues().size()) {
			return false;
		}

		for (PropertyValue value : values) {

			if (!rule.getPropertyValues().contains(value)) {
				return false;
			}

		}

		return true;

	}

}
