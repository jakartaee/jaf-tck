/*
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.activation.URLDataSource;

import java.net.*;
import java.io.*;
import jakarta.activation.*;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * Create an instance of URLDataSource, use it call getInputStream() api, if
 * it returns non-null object of type InputStream, then this testcase passes,
 * otherwise it fails.
 */

public class getInputStream_Test extends MultiTest {

private static final String fileName = "test.txt1";
private static final String badURLSpec = "http://www.adfkasdklfjdsaf.com/";

public static void main(String argv[])
{
	getInputStream_Test lTest = new getInputStream_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

@SuppressWarnings("deprecation")
public Status getInputStreamTest1()  throws IOException, MalformedURLException
{
	File test = File.createTempFile("input", ".txt");
	test.deleteOnExit();
	FileOutputStream fos = new FileOutputStream(test);
	fos.write("abcd".getBytes("us-ascii"));
	fos.close();

	URL goodURL = test.toURL();
	URLDataSource gds = new URLDataSource(goodURL);

	String errMsg = testIO(gds);
	if (errMsg != null)
		return Status.failed("URLDataSourceTest failed: " + errMsg);

	return Status.passed("getInputStream() test succeeded");
}

public Status getInputStreamTest2()   throws IOException, MalformedURLException
{
	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);

	if (!badURL.equals(bds.getURL()))
		return Status.failed("URLDataSourceTest for bad url failed: getURL didn't match initializer");

	if (!testBadIn(bds))
		return Status.failed("URLDataSourceTest for bad url failed: getInputStream() didn't throw" );

	return Status.passed("getInputStream() test succeeded");
}

private String testIO(URLDataSource uds)
{
	try {
		byte[] lFileBuffer = new byte[4];
		// read the data
		InputStream lFileIStream = uds.getInputStream();	// API TEST
		lFileIStream.read(lFileBuffer);
		lFileIStream.close();

		if ((lFileBuffer[0] != (byte)'a') ||
		    (lFileBuffer[1] != (byte)'b') ||
		    (lFileBuffer[2] != (byte)'c') ||
		    (lFileBuffer[3] != (byte)'d')) {
			return "data read from DataSource didn't match what was written";
		}

	} catch(Exception inException) {
		return "Exception: " + inException.toString();
	}
	return null;
}

private boolean testBadIn(URLDataSource bds) throws IOException
{
	InputStream is = null;	
	try {
		is = bds.getInputStream();	// API TEST
	} catch (IOException ioex) { 
		return true;
	} 
	is.close();

	return false;
}

}
