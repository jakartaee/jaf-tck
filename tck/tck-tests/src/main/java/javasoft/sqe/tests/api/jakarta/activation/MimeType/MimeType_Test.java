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
 * Create MimeType instances, using various constructors, if these
 * operations are successfull, then this test passes, otherwise
 * it fails.
 */

public class MimeType_Test
{

@Test
public void testMimeTypeTest1()
{
    Status s = mimeTypeTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status	mimeTypeTest1()
{	
	try {
		MimeType ojbmt1 =  new MimeType();	// API TEST

		if( ojbmt1 == null )
		    return Status.failed("Failed: to create MimeType object using default constructor");
	}catch(Exception ex) {
		return Status.failed("MimeType() threw " + ex.toString());
	}

        try {
                MimeType ojbmt2 =  new MimeType("text/plain");       // API TEST

                if( ojbmt2 == null )
                    return Status.failed("Failed: to create MimeType object using constructor");
        }catch(Exception ex) {
                return Status.failed("MimeType(string) threw " + ex.toString());
        }

	return Status.passed("MimeType(void | String) test succeeded");
}

@Test
public void testMimeTypeTest2()
{
    Status s = mimeTypeTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status mimeTypeTest2()
{
	try {
		MimeType objmt = new MimeType("text", "html");		// API TEST

                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

	} catch (Exception ex) {
		return Status.failed("MimeType(string, string) threw " + ex.toString());
	}

	return Status.passed("MimeType(String,String) test succeeded");
}

}
