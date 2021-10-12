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

package	javasoft.sqe.tests.api.jakarta.activation.FileTypeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.FileTypeMap;

// FileTypeMap is an abstract class. The main functional testing is in the only provided
// concrete implementation, MimetypesFileTypeMap, tested separately.  Here we just test
// the default constructor.

/** FileTypeMap is an abstract class. The main functional testing is in the only provided
 *  concrete implementation, MimetypesFileTypeMap, tested separately.<p>  
 *  Get object of type FileTypeMap, by calling getDefaultFileTypeMap() api, 
 *  If this method returns a non-null object of type FileTypeMap then this
 *  testcase passes, otherwise it fails.
 */

public class FileTypeMap_Test
{

    @Test
    public void test() throws IOException
    {
        Status s = run();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

public Status run()
{
	FileTypeMap ftm = FileTypeMap.getDefaultFileTypeMap();	// API TEST

	if( ftm == null )
	    return Status.failed("FileTypeMap() returned null");

	return Status.passed("FileTypeMap() test succeeded");
}

}
