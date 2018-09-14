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
 * We first create an object of type ActivationDataFlavor(Class,String),
 * call getRepresentationClass() api on that object, if it returns
 * the same string value as that passed to ActivationDataFlavor
 * constructor then this testcase passes otherwise it fails.
 */

public class getRepresentationClass_Test extends MultiTest {

    // main
    public static void main(String[] args)
    {
	Test t = new getRepresentationClass_Test(); 
	Status s = t.run(args, new PrintWriter(System.err, true), new PrintWriter(System.out, true)); 
	s.exit(); 
    }

    // Tests for equals:
    public Status testGetRepresentationClass()
    {
	String humanName = "Human Presentable Name";
	Class myClass = humanName.getClass();

	ActivationDataFlavor adf = new ActivationDataFlavor(myClass,humanName);

 	if(adf.getRepresentationClass().equals(myClass))
	    return Status.passed("getRepresentationClass() test succeeded");
	else
	    return Status.failed("getRepresentationClass() test failed: "
					+ adf.getRepresentationClass() + " != " + myClass);
    }
}
