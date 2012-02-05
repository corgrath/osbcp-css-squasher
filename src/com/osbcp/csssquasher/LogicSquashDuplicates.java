package com.osbcp.csssquasher;

import java.util.ArrayList;
import java.util.List;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;

public class LogicSquashDuplicates {

	private int fixedDuplications = 0;

	public List<Rule> packDuplicates(final List<Rule> rules) {

		List<Rule> newRules = new ArrayList<Rule>();

		for (Rule rule : rules) {

			Rule ruleWithSaveValues = contains(newRules, rule);

			if (ruleWithSaveValues != null) {

				System.out.println("Squashing '" + rule.getSelectors() + "' to '" + ruleWithSaveValues.getSelectors() + "'.");
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
