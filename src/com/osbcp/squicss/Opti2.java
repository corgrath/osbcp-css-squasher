package com.osbcp.squicss;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Opti2 {

	List<Rule> rules = new ArrayList<Rule>();

	public void register(final Selector selector, final Set<PropertyValue> propertyValues) {

		Rule rule = new Rule(selector);
		for (PropertyValue properyValue : propertyValues) {
			rule.addPropertyValue(properyValue);
		}

		rules.add(rule);

		//		}

	}

	public void print2() {

		rules = new EliminateDuplicatesLogic().packDuplicates(rules);

		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}

	}

}
