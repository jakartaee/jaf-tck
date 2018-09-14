/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.javax.activation.TestClasses;

import	javax.activation.*;
import  javasoft.sqe.tests.api.javax.activation.TestClasses.*;

/** This class is used to provide an alternative to the default MailcapCommandMap
 *  for testing purposes. It provides a number of distinct values which can be used
 *  to validate that it is being correctly used by a DataHandler.
 */

public class TestCommandMap extends CommandMap {

	public static String getClassName() {
		return  "test.package.TestClass";
	}

	public static String getVerb() {
		return "testVerb";
	}

	public static String getPreferredClassName() {
		return "test.package.PreferredClass";
	}

	public static String getPreferredVerb() {
		return "preferredVerb";
	}

	public static String getIncorrectMimeTypeVerb() {
		return "incorrectMimeType";
	}

	private String requiredMimeType = null;
	
	public TestCommandMap(String requiredMimeType)
	{
		this.requiredMimeType = requiredMimeType;
	}

	public CommandInfo[] getPreferredCommands(String mimeType)
	{
    	      CommandInfo cmdArray[] = new CommandInfo[1];
    	      String verb = (requiredMimeType.equals(mimeType)) ? getPreferredVerb() : getIncorrectMimeTypeVerb();
    	      cmdArray[0] = new CommandInfo(verb, getPreferredClassName());
    	      return cmdArray;
	}

	public CommandInfo[] getAllCommands(String mimeType)
	{
    		CommandInfo cmdArray[] = new CommandInfo[1];
    		String verb = (requiredMimeType.equals(mimeType)) ? getVerb() : getIncorrectMimeTypeVerb();
    		cmdArray[0] = new CommandInfo(verb, getClassName());
    		return cmdArray;
	}

	public CommandInfo getCommand(String mimeType, String cmdName)
	{
		String verb = (requiredMimeType.equals(mimeType)) ? cmdName : getIncorrectMimeTypeVerb();
		return new CommandInfo(verb, getClassName());
	}

	public DataContentHandler createDataContentHandler(String mimeType)
	{
    		return (requiredMimeType.equals(mimeType)) ? new TestDCH(mimeType) : null;
	}
}
