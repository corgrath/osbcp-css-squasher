package com.osbcp.csssquasher;

import java.util.List;

import com.osbcp.cssparser.CSSParser;
import com.osbcp.cssparser.Rule;

public class CSSSquasher {

	public static void main(final String[] args) {

	}

	public static String squash(final String css) throws Exception {

		List<Rule> rules = CSSParser.parse(css);

		while (true) {

			LogicSquashDuplicates squasher = new LogicSquashDuplicates();
			rules = squasher.packDuplicates(rules);
			int squashed = squasher.getNumberOfSquashed();
			System.out.println("Managed to squash '" + squashed + "' rules.");
			//			print(rules);

			LogicRefactor refactorizer = new LogicRefactor();
			rules = refactorizer.hello(rules);
			int refactored = refactorizer.getNumberOfRefactored();
			System.out.println("Managed to refactor '" + refactored + "' rules.");
			//			print(rules);

			// Job done if no more to squash or refactor 
			if (squashed == 0 && refactored == 0) {
				System.out.println("Squasher is happy. Exiting.");
				break;
			}

		}

		return toString(rules);

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