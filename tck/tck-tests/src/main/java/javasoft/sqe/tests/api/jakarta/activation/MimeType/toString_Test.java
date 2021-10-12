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
 * Create a MimeType instance, use it to call toString() method,
 * if it successfully returns non-null string representation of this
 * object, then this test passes otherwise it fails.
 */

public class toString_Test
{
@Test
public void testToStringTest1()
{
    Status s = toStringTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status toStringTest1()
{	
	try {
		MimeType objmt =  new MimeType("text/plain");
		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                String tostr =  objmt.toString();       // API TEST

                if( tostr == null )
                    return Status.failed("Failed: toString() returned null string!");
 
	} catch(Exception ex) {
		return Status.failed("toString() threw " + ex.toString());
	}

	return Status.passed("toString(void) test succeeded");
}

@Test
public void testToStringTest2()
{
    Status s = toStringTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status toStringTest2()
{
        try {
                MimeType objmt =  new MimeType();
                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                String tostr =  objmt.toString();       // API TEST

                if( tostr == null )
                    return Status.failed("Failed: toString() returned null string!");

        } catch(Exception ex) {
                return Status.failed("toString() threw " + ex.toString());
        }

        return Status.passed("toString(void) test succeeded");
}

}
