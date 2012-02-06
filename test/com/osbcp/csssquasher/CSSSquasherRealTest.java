package com.osbcp.csssquasher;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CSSSquasherRealTest {

	@Test
	public void testBasic() throws Exception {

		String contents = IOUtils.toString(Squasher.class.getResourceAsStream("demo.css"));

		ResultObject results = Squasher.squash(contents);

		System.out.println(results.getCompressedCSS());
		System.out.println(results.getLog());

	}
}
