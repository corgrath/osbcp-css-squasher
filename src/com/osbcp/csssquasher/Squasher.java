package com.osbcp.csssquasher;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.osbcp.cssparser.CSSParser;
import com.osbcp.cssparser.Rule;
import com.yahoo.platform.yui.compressor.CssCompressor;

public class Squasher {

	public static void main(final String[] arguments) throws Exception {

		if (arguments.length != 1) {

			System.out.println("Usage: java -jar osbcp-css-squasher-x.y.z.jar [input file]");

		} else {

			File file = new File(arguments[0]);

			String contents = FileUtils.readFileToString(file);

			ResultObject results = Squasher.squash(contents);

			String compressedCSS = results.getCompressedCSS();

			System.out.println(compressedCSS);

		}

	}

	public static ResultObject squash(final String originalCSS) throws Exception {

		List<Rule> rules = CSSParser.parse(originalCSS);
		StringBuilder log = new StringBuilder();

		while (true) {

			LogicSquashDuplicates squasher = new LogicSquashDuplicates(log);
			rules = squasher.packDuplicates(rules);
			int squashed = squasher.getNumberOfSquashed();
			log.append("Managed to squash '" + squashed + "' rules.\n");
			//			print(rules);

			LogicRefactor refactorizer = new LogicRefactor(log);
			rules = refactorizer.refactor(rules);
			int refactored = refactorizer.getNumberOfRefactored();
			log.append("Managed to refactor '" + refactored + "' rules.\n");
			//			print(rules);

			// Job done if no more to squash or refactor 
			if (squashed == 0 && refactored == 0) {
				log.append("Could not squash or refactor anymore.\n");
				break;
			}

		}

		String refactoredCSS = toString(rules);

		String compressedCSS = compress(log, refactoredCSS);

		log.append("Squasher is happy. Exiting.\n");

		return new ResultObject(originalCSS, refactoredCSS, compressedCSS, log.toString(), rules);

	}

	private static String compress(final StringBuilder log, final String originalCSS) throws IOException {

		StringReader reader = new StringReader(originalCSS);

		StringWriter writer = new StringWriter();

		CssCompressor compressor = new CssCompressor(reader);

		compressor.compress(writer, -1);

		String compressedCSS = writer.toString();

		log.append("Compressed the CSS from '" + originalCSS.length() + "' characters to '" + compressedCSS.length() + "' characters.\n");

		return compressedCSS;

	}

	/**
	 * Compiles the whole list of rules into a String.
	 * 
	 * @param rules The list of rules.
	 * @return A pretty String.
	 */

	private static String toString(final List<Rule> rules) {

		StringBuilder out = new StringBuilder();

		for (Rule rule : rules) {
			out.append(rule.toString());
		}

		return out.toString();

	}

}