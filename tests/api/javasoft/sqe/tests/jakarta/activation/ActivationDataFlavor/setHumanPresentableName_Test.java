/*
 * Copyright (c) 1996, 2019 Oracle and/or its affiliates. All rights reserved.
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

import java.io.*;
import jakarta.activation.*;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * We first create an object of type ActivationDataFlavor, then call
 * setHumanPresentableName(string) api on that object, we then call
 * getHumanPresentableName() api, if it returns same string value
 * that was passed to setHumanPresentableName() then this testcase
 * passes otherwise it fails.
 */

public class setHumanPresentableName_Test extends MultiTest {

    // main
    public static void main(String[] args)
    { 
	Test t = new setHumanPresentableName_Test(); 
	Status s = t.run(args, new PrintWriter(System.err, true), new PrintWriter(System.out, true)); 
	s.exit(); 
    }


    // Tests for equals:
    public Status testSetHumanPresentableName()
    {
	String humanName = "TextPlain DataFlavor";
	String newHumanName = "Activation TextPlain DataFlavor";

	ActivationDataFlavor adf = new ActivationDataFlavor("text/plain",humanName);

	// make sure it was set right in the constructor:
	if(!(adf.getHumanPresentableName().equals(humanName)))
	    return Status.failed("test failed, original human readable name not set");

	// now try setting it:
	adf.setHumanPresentableName(newHumanName);	// API TEST

	if(adf.getHumanPresentableName().equals(newHumanName))
	    return Status.passed("setHumanPresentableName() test succeeded");
	else
	    return Status.failed("setHumanPresentableName() test failed: " +
                                 adf.getHumanPresentableName() + " != " + newHumanName);
    }
}
