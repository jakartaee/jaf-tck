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

package	javasoft.sqe.tests.api.jakarta.activation.MimeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeType; 

/**
 * Create a MimeType instance, use it to call setParameter() method
 * with given string parameters, then call getParameter(), if set/get
 * values are equal, then this test passes otherwise it fails.
 */

public class setParameter_Test
{
@Test
public void testSetParameterTest()
{
    Status s = setParameterTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status setParameterTest()
{
	try {	// create MimeType object
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using a constructor");

		objmt.setParameter("charset", "us-ascii");	// API TEST

		if( !(objmt.getParameter("charset")).equals("us-ascii") )
		    return Status.failed("Failed: setParameter(charset, us-ascii)");

		objmt.setParameter("abc", "xyz");		// API TEST

		if( !(objmt.getParameter("abc")).equals("xyz") )
		    return Status.failed("Failed: setParameter(abc, xyz)");

		objmt.setParameter("foo", "");		// API TEST

                if( objmt.getParameter("foo") != "" )
                    return Status.failed("Failed: setParameter(foo, emptyString)");

	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("setParameter(string,string) threw " + ex.toString());
	}

	return Status.passed("setParameter(String,String) test succeeded");
}

}
