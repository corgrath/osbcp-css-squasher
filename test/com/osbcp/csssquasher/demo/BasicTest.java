package com.osbcp.csssquasher.demo;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.osbcp.csssquasher.ResultObject;
import com.osbcp.csssquasher.Squasher;

public class BasicTest {

	@Test
	public void testBasic() throws Exception {

		String contents = IOUtils.toString(BasicTest.class.getResourceAsStream("basic.css"));

		ResultObject results = Squasher.squash(contents);

		Assert.assertEquals("a,b{width:100px}a,b,c{color:red}", results.getCompressedCSS());

	}
}
