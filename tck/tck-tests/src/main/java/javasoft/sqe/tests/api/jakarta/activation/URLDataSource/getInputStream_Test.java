/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.URLDataSource; 

/**
 * Create an instance of URLDataSource, use it call getInputStream() api, if
 * it returns non-null object of type InputStream, then this testcase passes,
 * otherwise it fails.
 */

public class getInputStream_Test {

private static final String fileName = "test.txt1";
private static final String badURLSpec = "http://www.adfkasdklfjdsaf.com/";

@Test
public void testGetInputStreamTest1() throws MalformedURLException, IOException
{
    Status s = getInputStreamTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
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

@Test
public void testGetInputStreamTest2() throws MalformedURLException, IOException
{
    Status s = getInputStreamTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
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
