/*
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.jakarta.activation.TestClasses;

import jakarta.activation.*;

/**
 * This is utility library routine.
 */

public class TestCommandObject implements CommandObject {

    private DataHandler dh = null;
    private String verb = null;
    private boolean wasCalled = false;
    
    public TestCommandObject() {
    }
    
    public void setCommandContext(String verb, DataHandler dh) {
	this.dh = dh;
	this.verb = verb;
	wasCalled = true;
    }
    
    public String getVerb() {
	return verb;
    }

    public DataHandler getDataHandler() {
	return dh;
    }

    public boolean getWasCalled() {
	return wasCalled;
    }
}
