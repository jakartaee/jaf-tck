/*
 * Copyright (c) 2021, 2023 Oracle and/or its affiliates. All rights reserved.
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
package javasoft.sqe.tests.jakarta.activation.Module;

import com.sun.javatest.Status;
import com.sun.javatest.lib.MultiTest;

import jakarta.activation.DataSource;

/**
 * Verify that API on module path is able to find and use an implementation,
 * which is provided on the classpath.
 */
public class UnnamedModule_Test extends MultiTest {

    private static final String ACTIVATION_API_MODULE = "jakarta.activation";

    public static void main(String argv[]) {
        UnnamedModule_Test lTest = new UnnamedModule_Test();
        Status lStatus = lTest.test();
        lStatus.exit();
    }

    public Status test() {
        Module m = DataSource.class.getModule();
        if (m.isNamed()) {
            String moduleName = m.getName();
            if (ACTIVATION_API_MODULE.equals(moduleName)) {
                return Status.passed(moduleName);
            } else {
                return Status.failed("Module name must be: '" + ACTIVATION_API_MODULE + "' but is '" + moduleName + "'");
            }
        }
        return Status.failed(ACTIVATION_API_MODULE + " module is not on the module path");
    }

}
