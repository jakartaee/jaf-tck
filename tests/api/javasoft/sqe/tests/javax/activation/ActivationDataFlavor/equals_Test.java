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
import java.awt.datatransfer.*;
import javax.activation.*;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * We test the 'equals(DataFlavor)' by passing it DataFlavor object,
 * if this api returns a boolean {true|false} value then it passes.
 * We test by using equal/not-equal parameter values.
 */

public class equals_Test extends MultiTest {

    // main
    public static void main(String[] args)
    {
	Test t = new equals_Test(); 
	Status s = t.run(args, new PrintWriter(System.err, true), new PrintWriter(System.out, true)); 
	s.exit(); 
    }

    // Tests for equals:
    public Status testForEquals()
    {
	return callADFEquals("text/plain", "text/plain", true);
    }

    // Tests for not equals:
    public Status testForNotEquals()
    {
	return callADFEquals("text/plain", "image/gif", false);
    }

    // Test for equals with params
    public Status testForEqualsParams()
    {
        return callADFEquals("text/plain; charset=us-ascii; x-java-view=Viewer",
		"text/plain; x-java-view=Viewer; charset=us-ascii", true);
    }

    // Test for not equals with params
    public Status testForNotEqualsParams()
    {
        return callADFEquals("text/plain; charset=us-ascii; x-java-view=Viewer",
		"text/plain; x-java-view=Viewer", true);
    }

    // private implementation methods
    private Status callADFEquals(String adfMimeType,String dfMimeType,
				 boolean expectedResult)
    {
	ActivationDataFlavor adf = new ActivationDataFlavor(adfMimeType,adfMimeType);
	DataFlavor df = new DataFlavor(dfMimeType,dfMimeType);

	boolean result;
	result = adf.equals(df);

	if( result == expectedResult)
	    return Status.passed("equals() test succeeded");
	else
	    return Status.failed("equals() test failed");
    }
}
