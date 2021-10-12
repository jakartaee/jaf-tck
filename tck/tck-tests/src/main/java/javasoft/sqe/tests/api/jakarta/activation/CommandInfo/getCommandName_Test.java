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

package	javasoft.sqe.tests.api.jakarta.activation.CommandInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.CommandInfo;

/** FileTypeMap is an abstract class. The main functional testing is in the only
 *  provided concrete implementation, MimetypesFileTypeMap, tested separately. <p>
 *  Create a CommandInfo object with desired CommandName and then invoke getCommandName()
 *  api if it returns the expected value then this testcase passes otherwsie it fails.
 */

public class getCommandName_Test
{
	private String message = null;

    @Test
    public void run()
    {
        Status s = test();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

	public Status test()
	{
		// Test with a CommandObject
		String verb = "view";
		String className = "javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject";

		CommandInfo cInfo = new CommandInfo(verb, className);

		if (!validateInfo(cInfo, verb, className))
		     return Status.failed(message);

		return Status.passed("getCommandName() Test succeeded");
	}

	private boolean validateInfo(CommandInfo cInfo, String verb, String className)
	{
		String resVerb = cInfo.getCommandName();	// API TEST

    		if (verb == null) {
		    if (resVerb != null){
	    	        message = "getCommandName(): expected null, but got " + resVerb;
	    	        return false;
		    }
    		} else if (!verb.equals(resVerb)) {
			    message = "getCommandName(): expected " + verb + " got " + resVerb;
			return false;
		}
		return true; 
	}
}
