package com.osbcp.squicss;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EliminateDuplicatesLogic {

	public List<Rule> packDuplicates(final List<Rule> rules) {

		List<Rule> newRules = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Rule ruleWithSaveValues = contains(newRules, rule);

			if (ruleWithSaveValues != null) {

				ruleWithSaveValues.addSelector(rule.getSelectors());

			} else {
				newRules.add(rule);
			}

		}

		// TODO Auto-generated method stub
		return newRules;
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

		Set<PropertyValue> values = targetRule.getValues();

		if (targetRule.getValues().size() != rule.getValues().size()) {
			return false;
		}

		for (PropertyValue value : values) {

			if (!rule.getValues().contains(value)) {
				return false;
			}

		}

		return true;

	}

}
