Wraps the static code analyze tool [PMD](http://pmd.sourceforge.net/) into a simple JUnit test. 

![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/results.png "The JUnit results in Eclipse")


Download
========================================
Download the latest version here:

https://github.com/corgrath/JUnit-PMD-Test-Wrapper/downloads/


How to use it in an Eclipse project
========================================

Download the JUnit PMD Test Wrapper
----------------------------------------

Download the latest version of JUnit PMD Test Wrapper and add it to the project. 

![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/download_wrapper.png "Download JUnit-PMD-Test-Wrapper and add it to Eclipse")

Download PMD
----------------------------------------

Download the latest version of PMD and add it to the project.

Download PMD from: http://sourceforge.net/projects/pmd/files/pmd/4.2.5/ 

![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/download_pmd.png "Download PNG and add it to Eclipse")

Create a JUnit test case
----------------------------------------

Write a JUnit test case using the wrapper.

	package src;
	
	import com.osbcp.junitpmdtestwrapper.JUnitPMDTestWrapper;
	
	import org.junit.Test;
	
	public class TestPMD {
	
	        @Test
	        public void testSRC() throws Exception {
	
	                JUnitPMDTestWrapper.run(this, "src/", "pmd.xml");
	
	        }
	
	}
	
![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/create_pmd_test.png "Create the JUnit test in Eclipse")

Create a PMD rule set file
----------------------------------------

Create a PMD rule set file and add it in the same folder as the test.

Guide: http://pmd.sourceforge.net/rules/index.html

Or download our sample file: https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/pmd.xml

![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/add_pmd_file.png "Create the rule files for PMD")

Run and view the results
----------------------------------------

![alt text](https://github.com/corgrath/JUnit-PMD-Test-Wrapper/raw/master/doc/wiki/results.png "The JUnit results in Eclipse")

JavaDoc
========================================

http://dl.dropbox.com/u/8183146/persistent/projects/java_junit_pmd_test_wrapper/javadoc/index.html

License
========================================

JUnit PMD Test Wrapper - Copyright 2011 Christoffer Pettersson, christoffer@christoffer.me

Licensed under the Apache License, Version 2.0