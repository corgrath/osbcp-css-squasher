/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 */

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

/**
 * Main class for OSBCP CSS Squasher.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class Squasher {

	/**
	 * Main entry point for using the minifer as a stand alone application.
	 * 
	 * @param arguments First argument [0] should be the path of the CSS file that should be minified.
	 * @throws Exception If any errors occur.
	 */

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

	/**
	 * Squashes CSS.
	 * 
	 * @param originalCSS String representation of CSS.
	 * @return Squashed, refacotored and compressed CSS.
	 * @throws Exception If any error occurs.
	 */

	public static ResultObject squash(final String originalCSS) throws Exception {

		List<Rule> rules = CSSParser.parse(originalCSS);
		StringBuilder log = new StringBuilder();

		while (true) {

			LogicMerger merger = new LogicMerger(log);
			rules = merger.merge(rules);
			int merged = merger.getNumberOfMerges();
			log.append("Managed to merge '" + merged + "' rules.\n");
			//	System.out.println(toString(rules));

			LogicSquashDuplicates squasher = new LogicSquashDuplicates(log);
			rules = squasher.squash(rules);
			int squashed = squasher.getNbrOfSquashedRules();
			log.append("Managed to squash '" + squashed + "' rules.\n");
			//			System.out.println(toString(rules));

			LogicRefactor refactorizer = new LogicRefactor(log);
			rules = refactorizer.refactor(rules);
			int refactored = refactorizer.getNumberOfRefactored();
			log.append("Managed to refactor '" + refactored + "' rules.\n");
			//			System.out.println(toString(rules));

			// Job done if no more to squash or refactor 
			if (merged == 0 && squashed == 0 && refactored == 0) {
				log.append("Could not merge, squash or refactor anymore.\n");
				break;
			}

		}

		String refactoredCSS = toString(rules);

		String compressedCSS = compress(log, refactoredCSS);

		log.append("Squasher is happy. Exiting.\n");

		return new ResultObject(originalCSS, refactoredCSS, compressedCSS, log.toString(), rules);

	}

	/**
	 * Performs the YUI Compressor on a String.
	 * 
	 * @param log Log object.
	 * @param css The CSS that should be compressed.
	 * @return Compressed CSS.
	 * @throws IOException If any error occurs.
	 */

	private static String compress(final StringBuilder log, final String css) throws IOException {

		StringReader reader = new StringReader(css);

		StringWriter writer = new StringWriter();

		CssCompressor compressor = new CssCompressor(reader);

		compressor.compress(writer, -1);

		String compressedCSS = writer.toString();

		log.append("Compressed the CSS from '" + css.length() + "' characters to '" + compressedCSS.length() + "' characters.\n");

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