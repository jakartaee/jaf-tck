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

package javasoft.sqe.tests.jakarta.activation.Pluggability;

import java.io.IOException;

import com.sun.javatest.Status;
import com.sun.javatest.lib.MultiTest;

import jakarta.activation.MailcapCommandMap;

/**
 * Checks that a custom implementation of MailcapRegistryProvider can be loaded by SPI added
 * for JAF 2.1.
 */
public class MailcapRegistryProvider_Test extends MultiTest {

    public static void main(String argv[]) {
        MailcapRegistryProvider_Test lTest = new MailcapRegistryProvider_Test();
        Status lStatus = lTest.test();
        lStatus.exit();
    }

    public Status test() {
        try {
            MailcapCommandMap mailcap = new MailcapCommandMap("MailcapRegistryProvider_Test");
            if (mailcap.getMimeTypes() != null && mailcap.getMimeTypes()[0].equals("MIME/Pluggability_Test")) {
                return Status.passed("com.sun.ts.tests.activation.provider.MyMailcapRegistryProvider was loaded correctly");
            } else {
                return Status.failed("com.sun.ts.tests.activation.provider.MyMailcapRegistryProvider was NOT loaded");
            }
        } catch (IOException e) {
            return Status.failed(e.getMessage());
        }
    }

}
