OSBCP CSS Squasher
========================================

OSBCP CSS Squasher is simply a clever CSS squasher tool, written in Java, that converts CSS like this:

	a {
		width: 100px;
		color: red;
	}
	
	b {
		width: 100px;
		color: red;
	}
	
	c {
		color red;
	}
	
	d, e {
		margin: 10px;
	}
	
	e, d {
		padding: 10px;
	}
	
To this:
	
	a, b {
		width: 100px;
	}
	
	a, b, c {
		color: red;
	}
	
	d, e {
		margin: 10px;
		padding: 10px;
	}
	
By merging, squashing and refactoring your CSS code and then it uses the [YUI Compressor](http://developer.yahoo.com/yui/compressor/) to turn it into this:

	a,b{width:100px}a,b,c{color:red}d,e{margin:10px;padding:10px}
		

Try it yourself
========================================

[Click here for an online demo tool.](http://osbcp-css-squasher-demo.appspot.com/)



Download
========================================
Download the latest version here:

https://github.com/corgrath/osbcp-css-squasher/downloads/

Dependencies:

* [OSBCP CSS Parser](https://github.com/corgrath/osbcp-css-parser)
* [Commons IO](http://commons.apache.org/io/download_io.cgi)
* [YUI Compressor](http://yuilibrary.com/download/yuicompressor/)


How to use it as a stand alone application
========================================

Usage:

	java -jar osbcp-css-squasher-x.y.z.jar [input file]


How to use it in a Java project
========================================

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
	

Please view the JavaDoc for additional information.


JavaDoc
========================================

http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_squasher/javadoc/index.html


Please report bugs
========================================

Please report any bugs by [creating a new issue](https://github.com/corgrath/osbcp-css-squasher/issues).


Comparison numbers
========================================

Here are some fancy numbers compared to just using the YUI Compressor.

![Fancy numbers](http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_squasher/comparison.png "Fancy numbers")


License
========================================

OSBCP CSS Squasher
Copyright 2012 Christoffer Pettersson, christoffer[at]christoffer.me.

Licensed under the Apache License, Version 2.0