/*
 * Copyright (c) 1996, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.javax.activation.ActivationDataFlavor;

import java.io.*;
import java.util.Locale;
import java.awt.datatransfer.*;
import javax.activation.*;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.api.javax.activation.TestClasses.*;

/**
 * We create a TestActivationDataFlavor object then invoke 
 * testNormalizeMimeTypeParameter() method which in turn calls
 * normalizeMimeTypeParameter() api, if it returns the expected
 * string value then this testcase passes, otherwsie it fails.
 */

public class normalizeMimeTypeParameter_Test extends MultiTest {

    // main
    public static void main(String[] args)
    {
	Test t = new normalizeMimeTypeParameter_Test(); 
	Status s = t.run(args, new PrintWriter(System.err, true), new PrintWriter(System.out, true)); 
	s.exit(); 
    }

    // Tests for equals:
    public Status normalizeMimeTypeParameter()
    {
	String parameterName = "charset";
	String parameterValue = "US-ASCII";
	String expectedValue = parameterValue.toLowerCase(Locale.ENGLISH);

	// actual type doesn't matter
	TestActivationDataFlavor adf = new TestActivationDataFlavor("text/plain","My DataFlavor");
		// API TEST
	if(expectedValue.equalsIgnoreCase(adf.testNormalizeMimeTypeParameter(parameterName,parameterValue)))
	    return Status.passed("normalizeMimeTypeParameter() test succeeded");
	else
	    return Status.failed("normalizeMimeTypeParameter() test failed: "
				+ adf.testNormalizeMimeTypeParameter(parameterName,parameterValue)
				+ " != " + expectedValue);
    }
}
