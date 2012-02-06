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

	/**
	 * Create a new result object.
	 * 
	 * @param originalCSS The original CSS from the input source.
	 * @param refactoredCSS The refactored CSS done by the squasher.
	 * @param compressedCSS The compressed CSS done by YUI Compressor.
	 * @param log The log of the squash process.
	 * @param rules The CSS rules parsed from the input String.
	 */

	ResultObject(final String originalCSS, final String refactoredCSS, final String compressedCSS, final String log, final List<Rule> rules) {
		this.originalCSS = originalCSS;
		this.refactoredCSS = refactoredCSS;
		this.compressedCSS = compressedCSS;
		this.log = log;
		this.rules = rules;
	}

	/**
	 * Returns the original CSS from the input source.
	 * 
	 * @return The original CSS from the input source.
	 */

	public String getOriginalCSS() {
		return originalCSS;
	}

	/**
	 * Returns the refactored CSS done by the squasher.
	 * 
	 * @return The refactored CSS done by the squasher.
	 */

	public String getRefactoredCSS() {
		return refactoredCSS;
	}

	/**
	 * Returns the compressed CSS done by YUI Compressor.
	 * 
	 * @return The compressed CSS done by YUI Compressor.
	 */

	public String getCompressedCSS() {
		return compressedCSS;
	}

	/**
	 * Returns the log of the squash process.
	 * 
	 * @return the log of the squash process.
	 */

	public String getLog() {
		return log;
	}

	/**
	 * Returns the CSS rules parsed from the input String. 
	 * 
	 * @return The CSS rules parsed from the input String.
	 */

	public List<Rule> getRules() {
		return rules;
	}

}