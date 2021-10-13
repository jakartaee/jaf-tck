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

package javasoft.sqe.tests.api.jakarta.activation.MimeTypeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeTypeParseException; 

/**
 * We test this execption by invoking the two constructors for this class.
 * If they throw and we catch the expected execption then this testcase
 * passes, otherwise it fails.
 */

public class MimeTypeParseException_Test {

    String msg = "MimeType parse Exception caught!";

    @Test
    public void testMimeTypeParseEx()
    {
        Status s = test();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Tests for equals:
    public Status test()
    {
	// We will call a method that always throws an
	// USDTE and look at the message

	try {
		// create MimeTypeParseException object
                MimeTypeParseException mtpe = new MimeTypeParseException();  // API TEST

                if( mtpe == null )
                    return Status.failed("Failed: to create MimeTypeParseException object");

	        this.throwMTPException();

	} catch(MimeTypeParseException e) {
	    // check the message and return
	    if(msg.equals(e.getMessage()))
		return Status.passed("MimeTypeParseException() test succeeded");
	    else
		return Status.failed("test failed: exception caught but wrong message returned.");
	}
	// if we didn't catch an exception something
	// went wrong, return failure

	return Status.failed("MimeTypeParseException() test failed to catch exception");
    }
    // stupid little method that always throws an exception.

    public void throwMTPException() throws MimeTypeParseException
    {
	// always throw and Exception when called
	if(true)
	   throw new MimeTypeParseException(msg);	// API TEST
    }
}
