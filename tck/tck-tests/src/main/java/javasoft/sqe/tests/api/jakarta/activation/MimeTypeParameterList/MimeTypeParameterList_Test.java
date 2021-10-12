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

package	javasoft.sqe.tests.api.jakarta.activation.MimeTypeParameterList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeTypeParameterList;

/**
 * Create MimeTypeParameterList objects using constructors listed below: <p>
 *
 * TEST: MimeTypeParameterList();  <p>
 * TEST: MimeTypeParameterList(String);  <p>
 *
 * If this operation is successfull then this testcase passes, otherwise it fails.
 */

public class MimeTypeParameterList_Test
{

@Test
public void testMimeTypeParameterListTest()
{
    Status s = mimeTypeParameterListTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status mimeTypeParameterListTest()
{
	try {
		MimeTypeParameterList mtpl1 =  new MimeTypeParameterList();	// API TEST

		if( mtpl1 == null )
		    return Status.failed("Failed: to create MimeTypeParameterList object using default constructor");
	} catch(Exception ex) {
		return Status.failed("MimeType() threw " + ex.toString());
	}

        try {
              MimeTypeParameterList mtpl2 =  new MimeTypeParameterList(";charset=us-ascii;isoset=iso-9000;foo=abc");	// API TEST

                if( mtpl2 == null )
                    return Status.failed("Failed: to create MimeTypeParameterList object using constructor");
        } catch(Exception ex) {
                return Status.failed("MimeType(string) threw " + ex.toString());
        }

	return Status.passed("MimeTypeParameterList(void | String) test succeeded");
}

}
