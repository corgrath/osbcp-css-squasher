package com.osbcp.csssdfasher;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.osbcp.cssparser.Rule;
import com.osbcp.csssquasher.ResultObject;
import com.osbcp.csssquasher.Squasher;

public class TestApp {

	public static void main(final String[] args) throws Exception {

		String contents = IOUtils.toString(Squasher.class.getResourceAsStream("stylesheet.css"));

		// Squash!
		ResultObject results = Squasher.squash(contents);

		// Get the original CSS
		String originalCSS = results.getOriginalCSS();

		// Get the refactored CSS
		String refactoredCSS = results.getRefactoredCSS();

		// Get the compressed CSS
		String comrpressedCSS = results.getCompressedCSS();

		// Get all the CSS rules
		List<Rule> rules = results.getRules();

		// Get the log
		String log = results.getLog();

	}

}
