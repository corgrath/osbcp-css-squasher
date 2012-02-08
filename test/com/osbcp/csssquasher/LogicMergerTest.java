package com.osbcp.csssquasher;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.osbcp.cssparser.PropertyValue;
import com.osbcp.cssparser.Rule;
import com.osbcp.cssparser.Selector;

public class LogicMergerTest {

	@Test
	public void testBasic() throws Exception {

		List<Rule> rules = new ArrayList<Rule>();

		Rule r1 = new Rule();
		r1.addSelector(new Selector("s1"));
		r1.addSelector(new Selector("s2"));
		r1.addPropertyValue(new PropertyValue("width", "100px"));
		r1.addPropertyValue(new PropertyValue("color", "blue"));
		rules.add(r1);

		Rule r2 = new Rule();
		r2.addSelector(new Selector("s2"));
		r2.addSelector(new Selector("s1"));
		r2.addPropertyValue(new PropertyValue("width", "100px"));
		r2.addPropertyValue(new PropertyValue("color", "red"));
		rules.add(r2);

		StringBuilder log = new StringBuilder();
		List<Rule> mergedRules = new LogicMerger(log).merge(rules);

		Assert.assertEquals(1, mergedRules.size());

		Rule rule = rules.get(0);
		Assert.assertEquals("width", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getPropertyValues().get(0).getValue());

		Assert.assertEquals("color", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("blue", rule.getPropertyValues().get(1).getValue());

		Assert.assertEquals("color", rule.getPropertyValues().get(2).getProperty());
		Assert.assertEquals("red", rule.getPropertyValues().get(2).getValue());

	}

}
