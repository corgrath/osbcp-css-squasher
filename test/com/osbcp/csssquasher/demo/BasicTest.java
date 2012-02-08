package com.osbcp.csssquasher.demo;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.osbcp.cssparser.Rule;
import com.osbcp.csssquasher.ResultObject;
import com.osbcp.csssquasher.Squasher;

public class BasicTest {

	@Test
	public void testBasic() throws Exception {

		String contents = IOUtils.toString(BasicTest.class.getResourceAsStream("basic.css"));

		ResultObject results = Squasher.squash(contents);

		Assert.assertEquals("a,b{width:100px}d,e{margin:10px;padding:10px}a,b,c{color:red}", results.getCompressedCSS());

	}

	//	rder: 1px black solid; product-row { bord
	@Test
	public void testDuplicatedPropertyNamesShouldBeSquashed() throws Exception {

		ResultObject results = Squasher.squash("product-row { background: #ABC123; border: 1px black solid; } product-row { border: none;background:   url(http://www.domain.com/image.jpg);}");

		Rule rule = results.getRules().get(0);
		Assert.assertEquals("product-row", rule.getSelectors().get(0).toString());

		Assert.assertEquals("background", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("#ABC123", rule.getPropertyValues().get(0).getValue());

		Assert.assertEquals("border", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("1px black solid", rule.getPropertyValues().get(1).getValue());

		Assert.assertEquals("border", rule.getPropertyValues().get(2).getProperty());
		Assert.assertEquals("none", rule.getPropertyValues().get(2).getValue());

		Assert.assertEquals("background", rule.getPropertyValues().get(3).getProperty());
		Assert.assertEquals("url(http://www.domain.com/image.jpg)", rule.getPropertyValues().get(3).getValue());

		//		results.get
		//
		//		Assert.assertEquals("a,b{width:100px}a,b,c{color:red}", results.getCompressedCSS());

	}
}
