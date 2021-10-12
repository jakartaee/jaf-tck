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

package javasoft.sqe.tests.api.jakarta.activation.MailcapCommandMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MailcapCommandMap; 

/**
 * This class tests that resource files are loaded as expected. <p>
 * api2test: public String[] getNativeCommands(String) <p>
 *
 * how2test: Create a MailcapCommandMap
 */

public class loadFromLib_Test {

    private boolean skip = false;

    private static final String mimeType = "application/x-test";
    private static final String entry = mimeType + ";test1";

    @Test
    public void run()
    {
        Status s = loadFromLibTest();
        assertEquals(Status.PASSED, s.getType(), "Status " + s);
    }

    /**
     * We don't expect to be able to write into java.home so
     * create a fake java.home and point the System property
     * to it.
     */
    @BeforeEach
    public void init()
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

	    File mailcap = new File(lib, "mailcap");
	    mailcap.deleteOnExit();
	    PrintWriter pw = new PrintWriter(mailcap);
	    pw.println(entry);
	    pw.close();
	} catch (IOException ioex) {
	    throw new IllegalStateException(ioex.toString());
	}
    }

    public Status loadFromLibTest()
    {
	if (skip)
	    return Status.passed("loadFromLib skipped");
        try {
          // BEGIN UNIT TEST 1:

	     // Get MailcapCommandMap object
	     MailcapCommandMap mc = new MailcapCommandMap();

	     String[] cmds = mc.getNativeCommands(mimeType);

             if (cmds != null && cmds.length == 1 && cmds[0].equals(entry)) {
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
