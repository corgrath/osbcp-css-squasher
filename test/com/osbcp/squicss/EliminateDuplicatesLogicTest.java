package com.osbcp.squicss;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class EliminateDuplicatesLogicTest {

	SquashDuplicatedRulesLogic logic;

	public EliminateDuplicatesLogicTest() {
		this.logic = new SquashDuplicatedRulesLogic();
	}

	@Test
	public void testBasic() {

		Selector s1 = new Selector("s1");
		Selector s2 = new Selector("s2");

		PropertyValue pv1 = new PropertyValue("p1", "v1");
		PropertyValue pv2 = new PropertyValue("p2", "v2");
		PropertyValue pv3 = new PropertyValue("p3", "v3");

		Set<Rule> rules = new HashSet<Rule>();

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

		Set<Rule> newRules = logic.packDuplicates(rules);

		Assert.assertEquals(1, newRules.size());

	}
}
