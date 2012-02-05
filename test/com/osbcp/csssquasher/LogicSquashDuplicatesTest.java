package com.osbcp.csssquasher;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;
import com.osbcp.cssparser.Selector;

public class LogicSquashDuplicatesTest {

	LogicSquashDuplicates logic;

	public LogicSquashDuplicatesTest() {
		this.logic = new LogicSquashDuplicates();
	}

	@Test
	public void testBasic() {

		Selector s1 = new Selector("s1");
		Selector s2 = new Selector("s2");

		PropertyValue pv1 = new PropertyValue("p1", "v1");
		PropertyValue pv2 = new PropertyValue("p2", "v2");
		PropertyValue pv3 = new PropertyValue("p3", "v3");

		List<Rule> rules = new ArrayList<Rule>();

		Rule rule1 = new Rule(s1);
		rule1.addPropertyValue(pv1);
		rule1.addPropertyValue(pv2);
		rule1.addPropertyValue(pv3);
		rules.add(rule1);

		Rule rule2 = new Rule(s2);
		rule2.addPropertyValue(pv1);
		rule2.addPropertyValue(pv2);
		rule2.addPropertyValue(pv3);
		rules.add(rule2);

		Assert.assertEquals(2, rules.size());

		List<Rule> newRules = logic.packDuplicates(rules);

		Assert.assertEquals(1, newRules.size());

	}
}
