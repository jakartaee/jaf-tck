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

package	javasoft.sqe.tests.api.jakarta.activation.MailcapCommandMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import	java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MailcapCommandMap;

/** Test: MailcapCommandMap() ; default constructor. <p>
    Test: MailcapCommandMap(String) ; allows the caller to specify the path of a mailcap file. <p>
    Test: MailcapCommandMap(InputStream) ; allows the caller to specify an InputStream containing a mailcap file.
*/

public class MailcapCommandMap_Test
{

private static String testMailcap[] = { 
	"text/*;;		x-java-view=rom.pun.activation.viewers.FooViewer",
	"text/*;;		x-java-content-handler=javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH",
	"text/*;;		x-java-view=rom.pun.activation.viewers.MooViewer"  // make sure the last one takes precedence
};

private MailcapCommandMap commandMap = null;
private File testMcapFile = null;
private String message;

@Test
public void testMailcapCommandMapTest1() throws IOException
{
    Status s = mailcapCommandMapTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

private void setupFile() throws IOException
{
	// The easiest way to make sure that the file is available and has the correct content
	// is open it and stick the content in it:

	testMcapFile = new File("testMailcap");
	FileOutputStream os = new FileOutputStream(testMcapFile);

	try {
		for (int i = 0; i < testMailcap.length; i++)
		{
			byte[] bytes = testMailcap[i].getBytes(StandardCharsets.US_ASCII);
			os.write(bytes); os.write('\n');
		}
	} finally {
		os.close();
	}
}

public Status mailcapCommandMapTest1()
{
	message = "MailcapCommandMap() default constructor test succeeded";

	commandMap = new MailcapCommandMap();		// API TEST
	if ( commandMap == null )
		return Status.failed("MailcapCommandMap() returned null object.");

    	return Status.passed(message);
}

@Test
public void testMailcapCommandMapTest2() throws IOException
{
    Status s = mailcapCommandMapTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status mailcapCommandMapTest2() throws IOException
{
	message = "MailcapCommandMap(fileName) test succeeded";

	setupFile();

	commandMap = new MailcapCommandMap("testMailcap");	// API TEST
        if ( commandMap == null )
                return Status.failed("MailcapCommandMap() returned null object.");

	return Status.passed(message);
}

@Test
public void testMailcapCommandMapTest3() throws IOException
{
    Status s = mailcapCommandMapTest3();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status mailcapCommandMapTest3() throws FileNotFoundException, IOException
{
	boolean lPassed = true;
	message = "MailcapCommandMap(subStream) test succeeded";

	setupFile();

	// exectued after subFileTest, we now have a test mailcap file, so just get a stream
	InputStream is = new FileInputStream(testMcapFile);

	commandMap = new MailcapCommandMap(is);		// API TEST
        if ( commandMap == null )
                return Status.failed("MailcapCommandMap() returned null object.");

	return Status.passed(message);
}

@Test
public void testMailcapCommandMapTest4() throws IOException
{
    Status s = mailcapCommandMapTest4();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status mailcapCommandMapTest4()
{
	try {
	    commandMap = new MailcapCommandMap("SomeFileWhichDoesntExist");	// API TEST
	} catch(FileNotFoundException fnex) {
	    return Status.passed("MailcapCommandMap() missingFile test succeeded");
	} catch(IOException ioex) {
	    return Status.failed("missingFileTest: expected FileNotFoundException, but threw " + ioex.toString());
	}
	return Status.failed("missingFile test didn't throw an exception");
}

}
