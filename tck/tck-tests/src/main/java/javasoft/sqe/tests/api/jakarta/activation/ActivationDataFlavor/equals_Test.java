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

package javasoft.sqe.tests.api.jakarta.activation.ActivationDataFlavor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.ActivationDataFlavor; 

/**
 * We test the 'equals(ActivationDataFlavor)' by passing it ActivationDataFlavor object,
 * if this api returns a boolean {true|false} value then it passes.
 * We test by using equal/not-equal parameter values.
 */

public class equals_Test {

    // Tests for equals:
    @Test
    public void testForEquals()
    {
        Status s = callADFEquals("text/plain", "text/plain", true);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Tests for not equals:
    @Test
    public void testForNotEquals()
    {
        Status s = callADFEquals("text/plain", "image/gif", false);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Test for equals with params
    @Test
    public void testForEqualsParams()
    {
        Status s = callADFEquals("text/plain; charset=us-ascii; x-java-view=Viewer",
                "text/plain; x-java-view=Viewer; charset=us-ascii", true);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Test for not equals with params
    @Test
    public void testForNotEqualsParams()
    {
        Status s = callADFEquals("text/plain; charset=us-ascii; x-java-view=Viewer",
                "text/plain; x-java-view=Viewer", true);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // private implementation methods
    private Status callADFEquals(String adfMimeType,String dfMimeType,
				 boolean expectedResult)
    {
	ActivationDataFlavor adf = new ActivationDataFlavor(adfMimeType,adfMimeType);
	ActivationDataFlavor df = new ActivationDataFlavor(dfMimeType,dfMimeType);

	boolean result;
	result = adf.equals(df);

	if( result == expectedResult)
	    return Status.passed("equals() test succeeded");
	else
	    return Status.failed("equals() test failed");
    }
}
