Please note: Work still in progress, first version not yet availale!
========================================

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
	
To this:
	
	a, b {
		width: 100px;
	}
	
	a, b, c {
		color: red;
	}
	
By squashing and refactoring your CSS code, and then it uses the [YUI Compressor](http://developer.yahoo.com/yui/compressor/) to turn it into this:

	a,b{width:100px;color:red}	
	

Live demo
========================================

http://osbcp-css-squasher-demo.appspot.com/


Download
========================================
Download the latest version here:

https://github.com/corgrath/osbcp-css-squasher/downloads/

Dependencies:

* [OSBCP CSS Parser](https://github.com/corgrath/osbcp-css-parser)
* [Commons IO](http://commons.apache.org/io/download_io.cgi)
* [YUI Compressor](http://yuilibrary.com/download/yuicompressor/)


How to use it in an Eclipse project
========================================

To be written.
	
	

How to use it as a stand alone application
========================================

Usage:

	java -jar osbcp-css-squasher-x.y.z.jar [input file]


JavaDoc
========================================

To be written.

License
========================================

OSBCP CSS Squasher
Copyright 2012 Christoffer Pettersson, christoffer[at]christoffer.me.

Licensed under the Apache License, Version 2.0