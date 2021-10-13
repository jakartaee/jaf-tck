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
 * Create a MimeType instance, use it to call removeParameter() method with
 * given string name, then call getParameter(), if this method returns null
 * (since it has been deleted), then this test passes otherwise it fails.
 */

public class removeParameter_Test
{

@Test
public void testRemoveParameterTest()
{
    Status s = removeParameterTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status removeParameterTest()
{
	try {	// create MimeType object
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using a constructor");

		objmt.setParameter("charset", "us-ascii");
		objmt.removeParameter("charset");		// API TEST

		if( objmt.getParameter("charset") != null )
		    return Status.failed("Failed: removeParameter(charset)");

		objmt.setParameter("abc", "xyz");
		objmt.removeParameter("abc");

		if( objmt.getParameter("abc") != null )	// API TEST
		    return Status.failed("Failed: removeParameter(abc)");

		objmt.setParameter("foo", "");
		objmt.removeParameter("foo");		// API TEST

                if( objmt.getParameter("foo") != null )     // API TEST
                    return Status.failed("Failed: removeParameter(foo)");

	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("removeParameter(string) threw " + ex.toString());
	}

	return Status.passed("removeParameter(String) test succeeded");
}

}
