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
package javasoft.sqe.tests.api.jakarta.activation.Module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.DataSource;

/**
 * Verify that API on module path is able to find and use an implementation,
 * which is provided on the classpath.
 */
public class UnnamedModule_Test {

    private static final String ACTIVATION_API_MODULE = "jakarta.activation";

    @Test
    public void test() {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run() {
        String moduleName = DataSource.class.getModule().getName();
        if (ACTIVATION_API_MODULE.equals(moduleName)) {
            return Status.passed(moduleName);
        } else { 
            return Status.failed(ACTIVATION_API_MODULE + " is not visible ");
        }
    }

}
