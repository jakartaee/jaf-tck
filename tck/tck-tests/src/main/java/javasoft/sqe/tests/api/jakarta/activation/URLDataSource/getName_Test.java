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

package	javasoft.sqe.tests.api.jakarta.activation.URLDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.URLDataSource; 

// Calls the getFile method on the URL used to instantiate the object.

/**
 * Create an instance of URLDataSource, then use it to call getName() method, if it
 * returns a non-null expected string, then this testcase passes, otherwise it fails.
 */

public class getName_Test
{
private static final String httpURLSpec = "http://www.sun.com/index.html";
private static final String badURLSpec = "http://www.nonexistentdomain.com";

@Test
public void testGetName_Test() throws MalformedURLException, IOException
{
    Status s = getName_Test();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getName_Test() throws IOException, MalformedURLException
{
	URL goodURL  = new URL(httpURLSpec);
	URLDataSource gds = new URLDataSource(goodURL);

	if (!goodURL.equals(gds.getURL()))
		return Status.failed("Failed: getURL didn't match initializer");

	String urlName = goodURL.getFile();
	String dsName = gds.getName();		// API TEST

	if (urlName == null) {
	    if (dsName != null)
		return Status.failed("Failed: getName() != URL.getFile()");
	} else if (!urlName.equals(dsName)){
	    	   return Status.failed("Failed: getName() != URL.getFile()");
	}

	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);

	if (!badURL.equals(bds.getURL()))
		return Status.failed("Failed: bad url, getURL didn't match initializer");

	urlName = badURL.getFile();
	dsName = bds.getName();   	// API TEST

	if (urlName == null) {
	    if (dsName != null)
		return Status.failed("Failed: getName() != URL.getFile()");
	} else if (!urlName.equals(dsName)){
	    	   return Status.failed("Failed: getName() != URL.getFile()");
	}

	return Status.passed("getName() test succeeded");
}

}
