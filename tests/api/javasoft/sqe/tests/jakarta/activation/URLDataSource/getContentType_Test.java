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

import	java.net.*;
import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

// Returns the value of the content-type header field of the URL.

/**
 * Create an instance URLDataSource, use it to call getContentType() api,
 * if it returns content type string then this testcase passes, otherwise
 * it fails.
 */

public class getContentType_Test extends MultiTest
{
private static final String badURLSpec  = "http://www.xyznonexistentdomain.com";

public static void main(String argv[])
{
	getContentType_Test lTest = new getContentType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

@SuppressWarnings("deprecation")
public Status getContentTypeTest1() throws IOException, MalformedURLException
{
	File test = File.createTempFile("goodurl", ".html");
	test.deleteOnExit();
	URL goodURL = test.toURL();
	URLDataSource gds = new URLDataSource(goodURL);

	if (!goodURL.equals(gds.getURL()))
		return Status.failed("URLDataSourceTest failed: getURL didn't match initializer");

	String urlName = goodURL.getFile();
	String dsName = gds.getName();

	if (urlName == null) {
	    if (dsName != null)
		return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	} else if (!urlName.equals(dsName)){
	    return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	}

	URLConnection uconn = null;
	try {
		uconn = goodURL.openConnection();
	} catch(IOException ioex){
	}

	String refType = null;
	if (uconn != null)
		refType = uconn.getContentType();

	// I don't understand enough about URL's to know how to guarantee a non-null refType
	// so the best we can do is make sure that the results is what's specified for that case.

	String contentType = gds.getContentType();	// API TEST
	if (refType == null){
	    if (!"application/octet-stream".equals(contentType))
			return Status.failed("URLDataSourceTest failed: getContentType() should be application/octet-stream");
	} else {
	    if (!contentType.equals(refType))
			return Status.failed("URLDataSourceTest failed: getContentType() doesn't match");
	}
	return Status.passed("getContentType() test succeeded");
}

public Status getContentTypeTest2()   throws IOException, MalformedURLException
{
	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);

	if (!badURL.equals(bds.getURL()))
		return Status.failed("URLDataSourceTest for bad url failed: getURL didn't match initializer");
	
	String urlName = badURL.getFile();
	String dsName = bds.getName();

	if (urlName == null) {
	    if (dsName != null)
		return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	} else if (!urlName.equals(dsName)){
	    return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	}

    	URLConnection uconn = null;
	try {
		uconn = badURL.openConnection();
	}catch(IOException ioex){
	}

	String refType = null;
	if (uconn != null)
		refType = uconn.getContentType();

	String contentType = bds.getContentType();	// API TEST
	if (refType == null){
	    if (!"application/octet-stream".equals(contentType))
			return Status.failed("URLDataSourceTest for bad URL failed: getContentType() should be application/octet-stream");
	} else {
	    if (!contentType.equals(refType))
			return Status.failed("URLDataSourceTest for bad URL failed: getContentType() doesn't match");
	}
	return Status.passed("getContentType() test succeeded");
}

}
