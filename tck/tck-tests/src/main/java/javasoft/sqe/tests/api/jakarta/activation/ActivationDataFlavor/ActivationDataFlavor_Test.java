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
 * Here we test the three ActivationDataFlavor(..) constructors.
 * We do this by passing the expected parameters and then by verifying
 * that ActivationDataFlavor object type get created. If so then this
 * testcase passes otherwise it fails.
 */

public class ActivationDataFlavor_Test {

    // Tests for ActivationDataFlavor constructors:
    @Test
    public void testForActivationDF1()
    {
        Status s = callADF_1("text/plain", "text/plain", true);
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    @Test
    public void testForActivationDF2()
    {
    	Status s = callADF_2();
    	assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    @Test
    public void testForActivationDF3()
    {
        Status s = callADF_3();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    // private implementation methods

    private Status callADF_1(String adfMimeType,String dfMimeType,boolean expectedResult)
    {
	ActivationDataFlavor adf = new ActivationDataFlavor(adfMimeType,adfMimeType);	// API TEST
	ActivationDataFlavor df = new ActivationDataFlavor(dfMimeType,dfMimeType);

	boolean result = adf.equals(df);

	if( result == expectedResult)
	    return Status.passed("ActivationDataFlavor(String,String) test succeeded");
	else
	    return Status.failed("ActivationDataFlavor(String,String) test failed");
    }

    private Status callADF_2()
    {
        String humanName = "Human Presentable Name";
        Class myClass = humanName.getClass();

        ActivationDataFlavor adf = new ActivationDataFlavor(myClass,humanName);	// API TEST

        if(adf.getRepresentationClass().equals(myClass))
            return Status.passed("ActivationDataFlavor(Class,String) test succeeded");
        else
            return Status.failed("ActivationDataFlavor(Class,String) test failed");
    }

    private Status callADF_3()
    {
        String humanName = "Human Presentable Name";
        Class myClass = humanName.getClass();
	String mimeType = "text/plain";

        ActivationDataFlavor adf = new ActivationDataFlavor(myClass,mimeType,humanName); // API TEST

        if(adf.getRepresentationClass().equals(myClass))
            return Status.passed("ActivationDataFlavor(Class,String,String) test succeeded");
        else
            return Status.failed("ActivationDataFlavor(Class,String,String) test failed");
    }
}
