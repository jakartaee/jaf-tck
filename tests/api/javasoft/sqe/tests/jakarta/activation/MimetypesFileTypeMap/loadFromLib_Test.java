/*
 * Copyright (c) 2002, 2019 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.activation.MimetypesFileTypeMap;

import java.util.*;
import java.io.*;
import jakarta.activation.*;
import java.nio.file.Files;
import com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * This class tests that resource files are loaded as expected. <p>
 * api2test: public String[] getNativeCommands(String) <p>
 *
 * how2test: Create a MimetypesFileTypeMap
 */

public class loadFromLib_Test extends MultiTest {

    private boolean skip = false;

    private static final String mimeType = "application/x-test";
    private static final String ext = "xyz";
    private static final String entry = mimeType + " " + ext;

    public static void main( String argv[] )
    {
        loadFromLib_Test test = new loadFromLib_Test();
        Status s = test.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	s.exit();
    }

    /**
     * We don't expect to be able to write into java.home so
     * create a fake java.home and point the System property
     * to it.
     */
    public void init() throws SetupException
    {
	try {
	    File home = File.createTempFile("javahome", "conf");
	    home.delete();	// delete the temp file
	    home.mkdir();	// reuse the name for a directory
	    home.deleteOnExit();
	    File realhome = new File(System.getProperty("java.home"));
	    File realmod = new File(new File(realhome, "lib"), "modules");
	    System.setProperty("java.home", home.getPath());
	    File lib = new File(home, "lib");
	    lib.mkdir();
	    lib.deleteOnExit();

	    // Linux needs the <java.home>/lib/modules file so create a
	    // symlink to the original.
	    File mod = new File(lib, "modules");
	    mod.deleteOnExit();
	    try {
		Files.createSymbolicLink(mod.toPath(), realmod.toPath());
	    } catch (IOException|UnsupportedOperationException ex) {
		System.out.printf("Can't create symbolic link (%s -> %s), " +
			"skipping test", mod, realmod);
		skip = true;
		return;
	    }

	    File mimetypes = new File(lib, "mime.types");
	    mimetypes.deleteOnExit();
	    PrintWriter pw = new PrintWriter(mimetypes);
	    pw.println(entry);
	    pw.close();
	} catch (IOException ioex) {
	    throw new SetupException(ioex.toString());
	}
    }

    public Status loadFromLibTest()
    {
	if (skip)
	    return Status.passed("loadFromLib skipped");
        try {
          // BEGIN UNIT TEST 1:

	     // Get MimetypesFileTypeMap object
	     MimetypesFileTypeMap mt = new MimetypesFileTypeMap();

	     String ct = mt.getContentType("test." + ext);

             if (ct != null && ct.equals(mimeType)) {
		return Status.passed("loadFromLib passed");
             } else {
		return Status.failed("loadFromLib FAILED");
             }
          // END UNIT TEST 1:

        } catch ( Exception e ) {
	    return Status.failed("loadFromLib FAILED with exception " + e);
        }
    }
}
