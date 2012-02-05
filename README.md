What is SquiCSS Optimizer?
========================================

Squicss CSS Optimizer is simply a clever CSS optimization tool, written in Java, that converts CSS like this:

	a {
		width: 100px;
		color: red;
	}
	
	b {
		width: 100px;
		color: red;
	}
	
To this:
	
	a, b {
		width: 100px;
		color: red;
	}
	
By refactoring and reorganizing the CSS by:

* Eliminate duplicated rules
* and more!
	

And of course, it is still possible to minify the CSS afterwards by using tools such as the [YUI Compressor](http://developer.yahoo.com/yui/compressor/).

Download
========================================
Download the latest version here:

https://github.com/corgrath/squicss-css-optimizer/downloads/

How to use it in an Eclipse project
========================================

To be written.

How to use it as a stand alone application
========================================

To be written.

JavaDoc
========================================

To be written.

License
========================================

SquiCSS Optimizer
Copyright 2012 Christoffer Pettersson, christoffer@christoffer.me.

Licensed under the Apache License, Version 2.0