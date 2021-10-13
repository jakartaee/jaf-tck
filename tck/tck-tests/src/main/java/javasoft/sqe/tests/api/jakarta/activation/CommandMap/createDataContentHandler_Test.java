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

package	javasoft.sqe.tests.api.jakarta.activation.CommandMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.CommandMap;
import jakarta.activation.DataContentHandler;
import jakarta.activation.MailcapCommandMap;

/** CommandMap is an abstract class. The main functional testing is in the only
 *  provided concrete implementation, MailcapCommandMap, done separately. <p>
 *  We get default CommandMap objedct then using it call createDataContentHandler()
 *  api with string parameter. If this call returns a non-null DataContentHandler
 *  object then this testcase passes otherwise it fails.
 */

public class createDataContentHandler_Test
{
    @Disabled("createDataContentHandler_Test was disabled in ts.jtx")
    @Test
    public void disabled()
    {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    public Status run()
    {
        // does getDefaultCommandMap return a MailcapCommandMap ?
        CommandMap lCommandMap = CommandMap.getDefaultCommandMap();

        if (!(lCommandMap instanceof MailcapCommandMap))
                return Status.failed("getDefaultCommandMap() did not return MailcapCommandMap");
    
        DataContentHandler dch = lCommandMap.createDataContentHandler("text/plain"); //API TEST

        if( dch == null )
            return Status.failed("createDataContentHandler failed to return DataContentHandler object!");

        return Status.passed("createDataContentHandler() Test succeeded");
    }
}
