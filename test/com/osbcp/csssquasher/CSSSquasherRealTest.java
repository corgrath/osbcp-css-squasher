package com.osbcp.csssquasher;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CSSSquasherRealTest {

	@Test
	public void testBasic() throws Exception {

		String contents = IOUtils.toString(CSSSquasher.class.getResourceAsStream("demo.css"));

		String newCSS = CSSSquasher.squash(contents);

		System.out.println(newCSS);

	}

}
