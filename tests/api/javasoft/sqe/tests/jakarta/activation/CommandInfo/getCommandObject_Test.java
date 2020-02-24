/*
 * Copyright (c) 1997, 2019 Oracle and/or its affiliates. All rights reserved.
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

import	java.io.*;
import  java.net.URL;
import  java.net.MalformedURLException;
import  java.net.URLClassLoader;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	javasoft.sqe.tests.api.jakarta.activation.TestClasses.*;

/**
 *  Create a CommandInfo object using one its contructors, then invoke 
 *  getCommandObject(), if it returns a non-null object then this test
 *  passes otherwise it fails.
 */

public class getCommandObject_Test implements Test
{
	private String message = null;
	private DataHandler dh = null;
	public PrintWriter outLog;
	public PrintWriter outConsole;

	public static void main(String argv[])
	{
		getCommandObject_Test lTest = new getCommandObject_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		this.outLog = outLog;
		// Test with a CommandObject
		String verb = "view";
		String className = "javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject";

		CommandInfo cInfo = new CommandInfo(verb, className);

		if (!validateInfo(cInfo, verb, className))
		     return Status.failed(message);

		Object obj = null;
		outLog.println("UNIT TEST 1:      getCommandObject(DataHandler, ClassLoader)");

		try {
			obj = cInfo.getCommandObject(dh, this.getClass().getClassLoader());	// API TEST
		} catch(ClassNotFoundException cnfex) {
			return Status.failed("getCommandObject() unexpected throw: " + cnfex.toString());
		} catch(IOException ioex) {
			return Status.failed("getCommandObject() unexpected throw: " + ioex.toString());
		}

		outLog.println("UNIT TEST 1:	passed\n");

		outLog.println("UNIT TEST 2:      getCommandObject(DataHandler, ClassLoader) no java.beans.Beans");

		/*
		 * Check that CommandInfo.getCommandObject can create a
		 * command object even in an environment without
		 * java.beans.Beans.  We need to load the CommandInfo
		 * class (and test class) in another class loader because
		 * CommandInfo caches the Beans.instantiate method reference
		 * when the CommandInfo class is loaded.
		 */
		Class<?> test = null;
		FilterClassLoader fcl = new FilterClassLoader("java.beans.Beans");
		try {
		    test = reloadClass(fcl, CommandObject.class, TestNoBeans.class);
		    test.getMethod("test").invoke(null);
		} catch(Exception ex) {
ex.printStackTrace();
			return Status.failed("getCommandObject() unexpected throw: " + ex.toString());
		}
		if (!fcl.getFiltered())
			return Status.failed("getCommandObject() failed to filter");


		outLog.println("UNIT TEST 2:	passed\n");
		return Status.passed("getCommandObject() Test succeeded");
	}

	private boolean validateInfo(CommandInfo cInfo, String verb, String className)
	{
		String resVerb = cInfo.getCommandName();

    		if (verb == null) {
		    if (resVerb != null){
	    	        message = "getCommandName(): expected null, but got " + resVerb;
	    	        return false;
		    }
    		} else if (!verb.equals(resVerb)) {
			    message = "getCommandName(): expected " + verb + " got " + resVerb;
			return false;
		}
    
		String resClass = cInfo.getCommandClass();

    		if (className == null) {
		    if (resClass != null){
	    		message = "getCommandClass(): expected null, but got " + resClass;
	    		return false;
		    }
    		} else {
			if (!className.equals(resClass)) {
	    		    message = "getCommandClass(): expected " + className + " got " + resClass;
	    		    return false;
			}	
    		}
		return true; 
	}

    /**
     * A special class loader that refuses to load a specified class.
     */
    static class FilterClassLoader extends ClassLoader {
	private final String clz;
	private boolean filtered;

	public FilterClassLoader(String clz) {
	    super(FilterClassLoader.class.getClassLoader());
	    this.clz = clz;
	}

	protected Class loadClass(String name, boolean resolve)
				    throws ClassNotFoundException {
	    if (name.equals(clz)) {
		filtered = true;
		throw new ClassNotFoundException(name);
	    }
	    return super.loadClass(name, resolve);
	}

	public boolean getFiltered() {
	    return filtered;
	}
    }


    /**
     * A special class loader that loads classes from its own class path
     * (specified via URLs) before delegating to the parent class loader.
     * This is used to load the test classes in separate class loaders,
     * even though those classes are also loaded in the parent class loader.
     */
    static class TestClassLoader extends URLClassLoader {
	public TestClassLoader(URL[] urls, ClassLoader parent) {
	    super(urls, parent);
	}

	@Override
	public Class<?> loadClass(String name, boolean resolve)
				    throws ClassNotFoundException {
	    Class<?> c = null;
	    try {
		c = findLoadedClass(name);
		if (c != null)
		    return c;
		c = findClass(name);
		if (resolve)
		    resolveClass(c);
	    } catch (SecurityException sex) {
		c = super.loadClass(name, resolve);
	    } catch (ClassNotFoundException cex) {
		c = super.loadClass(name, resolve);
	    }
	    return c;
	}
    }


    /**
     * Reload the class in a separate class loader.
     */
    private static Class<?> reloadClass(ClassLoader parent,
			Class<?> classToTest, Class<?> testClass)
			throws ClassNotFoundException {
	URL[] urls = new URL[] {
	    classpathOf(testClass),
	    classpathOf(classToTest),
	    classpathOf(getCommandObject_Test.class)
	};
	ClassLoader cl = new TestClassLoader(urls, parent);
	return cl.loadClass(testClass.getName());
    }

    /**
     * Return the classpath entry used to load the named resource.
     * XXX - Only handles file: and jar: URLs.
     */
    private static URL classpathOf(Class<?> c) {
	String name = "/" + c.getName().replace('.', '/') + ".class";
	try {
	    URL url = getCommandObject_Test.class.getResource(name);
	    if (url.getProtocol().equals("file")) {
		String file = url.getPath();
		if (file.endsWith(name))	// has to be true?
		    file = file.substring(0, file.length() - name.length() + 1);
//System.out.println("file URL " + url + " has CLASSPATH " + file);
		return new URL("file", null, file);
	    } else if (url.getProtocol().equals("jar")) {
		String file = url.getPath();
		int i = file.lastIndexOf('!');
		if (i >= 0)
		    file = file.substring(0, i);
//System.out.println("jar URL " + url + " has CLASSPATH " + file);
		return new URL(file);
	    } else
		return url;
	} catch (MalformedURLException mex) {
	    return null;
	}
    }
}
