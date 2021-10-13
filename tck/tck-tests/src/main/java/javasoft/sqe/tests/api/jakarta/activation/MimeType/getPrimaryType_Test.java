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
 * Create a MimeType instance, use it to call getPrimaryType() method, if it
 * returns a primary (string) type of this object, then this test passes,
 * otherwise it fails.
 */

public class getPrimaryType_Test
{
@Test
public void testGetPrimaryTypeTest1() throws IOException
{
    Status s = getPrimaryTypeTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status	getPrimaryTypeTest1()
{	
	try {
		MimeType objmt =  new MimeType("text/plain");
		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                String primetype =  objmt.getPrimaryType();       // API TEST

                if( primetype == null )
                    return Status.failed("Failed: getPrimaryType() returned null string!");
 
	} catch(Exception ex) {
		return Status.failed("getPrimaryType() threw " + ex.toString());
	}

	return Status.passed("getPrimaryType(void) test succeeded");
}

@Test
public void testGetPrimaryTypeTest2() throws IOException
{
    Status s = getPrimaryTypeTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status   getPrimaryTypeTest2()
{
        try {
                MimeType objmt =  new MimeType();
                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                String primetype =  objmt.getPrimaryType();       // API TEST

                if( primetype != null )
                    return Status.passed("getPrimaryType() test succeeded");

        } catch(Exception ex) {
                return Status.failed("getPrimaryType() threw " + ex.toString());
        }

        return Status.failed("getPrimaryType(void) test failed");
}

}
