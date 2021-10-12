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

package	javasoft.sqe.tests.api.jakarta.activation.DataHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.DataHandler;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject;

/**
 * Create a DataHandler object then use it to call getContent() api,
 * if this method returns non-null Object then this testcase passes.
 */

public class getContent_Test
{
    @Test
    public void run()
    {
        Status s = test();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

	public Status test()
	{
		try {
			// create a DataHandler object
			DataHandler dhl = new DataHandler(new TestCommandObject(), "foo/goo");

                	if( dhl == null )
                    		return Status.failed("DataHandler() failed to return DataHandler object!");

			// get content
			Object content = dhl.getContent();	// API TEST

			if( content == null )
		    		return Status.failed("getContent failed to return non-null Object!");
		} catch (Exception e) {
			return Status.failed("Exception caught!");
		}
		return Status.passed("getContent() test succeeded");
	}
}
