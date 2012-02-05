package com.osbcp.squicss;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;
import com.osbcp.cssparser.Selector;

public class Opti2 {

	private List<Rule> rules = new ArrayList<Rule>();

	public void register(final Selector selector, final Set<PropertyValue> propertyValues) {

		Rule rule = new Rule(selector);
		for (PropertyValue properyValue : propertyValues) {
			rule.addPropertyValue(properyValue);
		}

		rules.add(rule);

	}

	public List<Rule> getRules() {
		return rules;
	}

}
