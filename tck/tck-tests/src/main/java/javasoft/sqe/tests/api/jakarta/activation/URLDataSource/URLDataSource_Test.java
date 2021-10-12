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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.URLDataSource; 

/**
 * Create instances of URLDataSource, using the constructors of this class.
 * If it returns objects of type URLDataSource, then this testcase passes,
 * otherwise it fails.
 */

public class URLDataSource_Test
{
private static final String fileURLSpec = "file:test.txt";
private static final String httpURLSpec = "http://www.sun.com/index.html";
private static final String badURLSpec  = "http://www.nonexistentdomain.com";

@Test
public void testURLDataSourceTest1() throws MalformedURLException, IOException
{
    Status s = URLDataSourceTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status URLDataSourceTest1() throws IOException, MalformedURLException
{
	URL goodURL  = new URL(httpURLSpec);
	URLDataSource gds = new URLDataSource(goodURL); 	// API TEST

	if (!goodURL.equals(gds.getURL()))
		return Status.failed("URLDataSourceTest failed: getURL didn't match initializer");

	String urlName = goodURL.getFile();
	String dsName  = gds.getName();

	if (urlName == null) {
	    if (dsName != null)
		return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	} else if (!urlName.equals(dsName)){
	           return Status.failed("URLDataSourceTest failed: getName() != URL.getFile()");
	}
	return Status.passed("URLDataSource(URL http) test succeeded");
}

@Test
public void testURLDataSourceTest2() throws MalformedURLException, IOException
{
    Status s = URLDataSourceTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status URLDataSourceTest2() throws IOException, MalformedURLException
{
	URL goodURL = new URL(fileURLSpec);
	URLDataSource gds = new URLDataSource(goodURL); 	// API TEST

	if( gds == null)
	    return Status.failed("Failed to create URLDataSource object");

	return Status.passed("URLDataSource(URL goodURL) test succeeded");
}

@Test
public void testURLDataSourceTest3() throws MalformedURLException, IOException
{
    Status s = URLDataSourceTest3();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status URLDataSourceTest3() throws IOException, MalformedURLException
{
	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);		// API TEST

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
	return Status.passed("URLDataSource(URL badURL) test succeeded");
}

}
