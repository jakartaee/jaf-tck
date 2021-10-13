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

import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestActivationDataFlavor;

/**
 * We create a TestActivationDataFlavor object then invoke
 * testNormalizeMimeType() method which in turn calls
 * normalizeMimeType() api, if it returns the expected
 * string value then this testcase passes, otherwsie it fails.
 */

public class normalizeMimeType_Test {

    @Test
    public void run()
    {
        Status s = testNormalizeMimeType();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // Tests for equals:
    public Status testNormalizeMimeType()
    {
	String mimeType = "text/plain";
	String expectedValue = mimeType;

	// actual type doesn't matter
	TestActivationDataFlavor adf = new TestActivationDataFlavor("text/plain","My DataFlavor");

	if(expectedValue.equals(adf.testNormalizeMimeType(mimeType)))
	    return Status.passed("normalizeMimeType() test succeeded");
	else
	    return Status.failed("normalizeMimeType() test failed: "
				+ adf.testNormalizeMimeType(mimeType) + " != " + expectedValue);
    }
}
