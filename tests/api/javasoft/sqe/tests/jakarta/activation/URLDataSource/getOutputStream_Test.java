/*
 * Copyright (c) 1997, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javasoft.sqe.tests.api.jakarta.activation.URLDataSource;

import  java.net.*;
import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * Create an instance of URLDataSource, use it call getOutputStream() api, if
 * it returns non-null object of type OutputStream, then this testcase passes,
 * otherwise it fails.
 */

public class getOutputStream_Test extends MultiTest {

private static final String badURLSpec = "http://www.adfkasdklfjdsaf.com/";

public static void main(String argv[])
{
	getOutputStream_Test lTest = new getOutputStream_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

@SuppressWarnings("deprecation")
public Status getOutputStreamTest1() throws IOException, MalformedURLException
{
	File test = File.createTempFile("output", ".txt");
	test.deleteOnExit();

	URL goodURL = test.toURL();
	URLDataSource gds = new URLDataSource(goodURL);

	String errMsg = testIO(gds);
	if (errMsg != null)
		return Status.failed("URLDataSourceTest failed: " + errMsg);

	return Status.passed("getOutputStream() test succeeded");
}

public Status getOutputStreamTest2() throws IOException, MalformedURLException
{
	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);

	if (!badURL.equals(bds.getURL()))
		return Status.failed("Failed: bad url, getURL didn't match initializer");

	if (!testBadOut(bds))
		return Status.failed("URLDataSourceTest for bad url failed: getOutputStream() didn't throw" );

	return Status.passed("getOutputStream() test succeeded");
}

private String testIO(URLDataSource uds)
{
	try {
		byte[] lFileBuffer = { 34, 35, 36, 37 };
	    // write some data
	    // We will only do this test if the file protocol
	    // implements writing which it doesn't seem to do in 1.1.x

	    try{
		OutputStream lFileOStream = uds.getOutputStream();	// API TEST

		if(lFileOStream == null) {
			return "getOutputStream returned null";
		}
		lFileOStream.write(lFileBuffer);
		lFileOStream.flush();
		lFileOStream.close();

	    } catch(UnknownServiceException use){
		//use.printStackTrace();
		//Status.failed("Warning: skipping getOutputStream test, 'file' protocol doesn't support 'write' operation");
		return null;
	    }
		// read the same data
		InputStream lFileIStream = uds.getInputStream();
		lFileIStream.read(lFileBuffer);

		if((lFileBuffer[0] != 34) || (lFileBuffer[1] != 35) || (lFileBuffer[2] != 36) || (lFileBuffer[3] != 37)) {
			return "data read from DataSource didn't match what was written";
		}
		lFileIStream = null;

	} catch(Exception inException) {
		return "Exception: " + inException.toString();
	}
	return null;
}

private boolean testBadOut(URLDataSource bds) throws IOException
{
	OutputStream os = null;
	try {
		os = bds.getOutputStream();	// API TEST
	} catch (IOException ioex) { 
		return true;
	}
	os.close();

	return false;
}

}
