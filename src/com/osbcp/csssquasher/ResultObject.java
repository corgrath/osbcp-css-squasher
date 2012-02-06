package com.osbcp.csssquasher;

import java.util.List;

import com.osbcp.cssparser.Rule;

/**
 * Result object from squashing.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public class ResultObject {

	private String originalCSS;
	private String refactoredCSS;
	private String compressedCSS;
	private String log;
	private List<Rule> rules;

	public ResultObject(final String originalCSS, final String refactoredCSS, final String compressedCSS, final String log, final List<Rule> rules) {
		this.originalCSS = originalCSS;
		this.refactoredCSS = refactoredCSS;
		this.compressedCSS = compressedCSS;
		this.log = log;
		this.rules = rules;
	}

	public String getOriginal() {
		return originalCSS;
	}

	public String getRefactoredCSS() {
		return refactoredCSS;
	}

	public String getCompressedCSS() {
		return compressedCSS;
	}

	public String getLog() {
		return log;
	}

	public List<Rule> getRules() {
		return rules;
	}

}