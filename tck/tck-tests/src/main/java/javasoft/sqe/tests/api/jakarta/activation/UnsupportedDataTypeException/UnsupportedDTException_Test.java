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

package javasoft.sqe.tests.api.jakarta.activation.UnsupportedDataTypeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.UnsupportedDataTypeException; 

/**
 * We test this execption by invoking the two constructors for this class.
 * If they throw and we catch the expected execption then this testcase
 * passes, otherwise it fails.
 */

public class UnsupportedDTException_Test {

    String msg = "unsupported data type exception";

    @Test
    public void testUnsupportedDTEx()
    {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Tests for equals:

    public Status run()
    {
	// We will call a method that always throws an
	// USDTE and look at the message

	try {
		// create a UnsupportedDataTypeException object
		UnsupportedDataTypeException usdte = new UnsupportedDataTypeException();  // API TEST

                if( usdte == null )
                    return Status.failed("Failed: to create UnsupportedDataTypeException object");

		this.throwUSDTException();

	} catch(UnsupportedDataTypeException e){
	    // check the message and return
	    if(msg.equals(e.getMessage()))
		return Status.passed("UnsupportedDataTypeException() test succeeded");
	    else
		return Status.failed("test failed: exception caught but wrong message returned.");
	}
	// if we didn't catch an exception something
	// went wrong, return failure

	return Status.failed("UnsupportedDataTypeException() test failed to catch exception");
    }
    // stupid little method that always throws an exception.

    public void throwUSDTException() throws UnsupportedDataTypeException
    {
	// always throw and Exception when called
	if(true)
	   throw new UnsupportedDataTypeException(msg);		// API TEST
    }
}
