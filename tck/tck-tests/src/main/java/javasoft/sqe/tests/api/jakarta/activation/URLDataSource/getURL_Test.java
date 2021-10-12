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

/**
 * Create an instance of URLDataSource, use it call getURL() api, if it
 * returns expected URL for this DataSource, then this testcase passes,
 * otherwise it fails.
 */

public class getURL_Test
{
private static final String httpURLSpec = "http://www.sun.com/index.html";
private static final String badURLSpec  = "http://www.nonexistentdomain.com";

@Test
public void testGetURL_Test() throws MalformedURLException, IOException
{
    Status s = getURL_Test();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getURL_Test() throws IOException, MalformedURLException
{
	URL goodURL  = new URL(httpURLSpec);
	URLDataSource gds = new URLDataSource(goodURL);

	if (!goodURL.equals(gds.getURL()))	// API TEST
		return Status.failed("Failed: getURL didn't match initializer");

	URL badURL = new URL(badURLSpec);
	URLDataSource bds = new URLDataSource(badURL);

	if (!badURL.equals(bds.getURL()))	// API TEST
		return Status.failed("Failed: bad url, getURL didn't match initializer");

	return Status.passed("getURL() test succeeded");
}

}
