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

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeType; 

/**
 * Create a MimeType instance, use it to call getParameter() method with a given
 * string name, if it returns a string value associated with the given name then
 * this test passes, otherwise it fails.
 */

public class getParameter_Test
{

@Test
public void testGetParameterTest1() throws IOException
{
    Status s = getParameterTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status	getParameterTest1()
{	
	try {
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

		String param =  objmt.getParameter("charset");       // API TEST

		if( param == null )
                    return Status.passed("getBaseType(void) test passed");

	} catch(Exception ex) {
		return Status.failed("getParameter() threw " + ex.toString());
	}

	return Status.failed("getBaseType(void) test failed");
}

@Test
public void testGetParameterTest2() throws IOException
{
    Status s = getParameterTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status   getParameterTest2()
{
        try {
                MimeType objmt =  new MimeType("text/plain;charset=us-ascii");

                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                String param =  objmt.getParameter("charset");       // API TEST

                if( param == null )
                    return Status.failed("Failed: getParameter() returned null string!");

        }catch(Exception ex) {
                return Status.failed("getParameter() threw " + ex.toString());
        }

        return Status.passed("getParameter(void) test succeeded");
}
}
