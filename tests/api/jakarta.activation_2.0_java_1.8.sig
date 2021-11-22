#Signature file v4.3
#Version 

CLSS public jakarta.activation.ActivationDataFlavor
cons public <init>(java.lang.Class,java.lang.String)
cons public <init>(java.lang.Class,java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
meth protected java.lang.String normalizeMimeType(java.lang.String)
 anno 0 java.lang.Deprecated()
meth protected java.lang.String normalizeMimeTypeParameter(java.lang.String,java.lang.String)
 anno 0 java.lang.Deprecated()
meth public boolean equals(jakarta.activation.ActivationDataFlavor)
meth public boolean equals(java.lang.Object)
meth public boolean equals(java.lang.String)
 anno 0 java.lang.Deprecated()
meth public boolean isMimeTypeEqual(java.lang.String)
meth public int hashCode()
meth public java.lang.Class getRepresentationClass()
meth public java.lang.String getHumanPresentableName()
meth public java.lang.String getMimeType()
meth public void setHumanPresentableName(java.lang.String)
supr java.lang.Object
hfds humanPresentableName,mimeObject,mimeType,representationClass

CLSS public jakarta.activation.CommandInfo
cons public <init>(java.lang.String,java.lang.String)
meth public java.lang.Object getCommandObject(jakarta.activation.DataHandler,java.lang.ClassLoader) throws java.io.IOException,java.lang.ClassNotFoundException
meth public java.lang.String getCommandClass()
meth public java.lang.String getCommandName()
supr java.lang.Object
hfds className,verb
hcls Beans

CLSS public abstract jakarta.activation.CommandMap
cons public <init>()
meth public abstract jakarta.activation.CommandInfo getCommand(java.lang.String,java.lang.String)
meth public abstract jakarta.activation.CommandInfo[] getAllCommands(java.lang.String)
meth public abstract jakarta.activation.CommandInfo[] getPreferredCommands(java.lang.String)
meth public abstract jakarta.activation.DataContentHandler createDataContentHandler(java.lang.String)
meth public jakarta.activation.CommandInfo getCommand(java.lang.String,java.lang.String,jakarta.activation.DataSource)
meth public jakarta.activation.CommandInfo[] getAllCommands(java.lang.String,jakarta.activation.DataSource)
meth public jakarta.activation.CommandInfo[] getPreferredCommands(java.lang.String,jakarta.activation.DataSource)
meth public jakarta.activation.DataContentHandler createDataContentHandler(java.lang.String,jakarta.activation.DataSource)
meth public java.lang.String[] getMimeTypes()
meth public static jakarta.activation.CommandMap getDefaultCommandMap()
meth public static void setDefaultCommandMap(jakarta.activation.CommandMap)
supr java.lang.Object
hfds defaultCommandMap,map

CLSS public abstract interface jakarta.activation.CommandObject
meth public abstract void setCommandContext(java.lang.String,jakarta.activation.DataHandler) throws java.io.IOException

CLSS public abstract interface jakarta.activation.DataContentHandler
meth public abstract jakarta.activation.ActivationDataFlavor[] getTransferDataFlavors()
meth public abstract java.lang.Object getContent(jakarta.activation.DataSource) throws java.io.IOException
meth public abstract java.lang.Object getTransferData(jakarta.activation.ActivationDataFlavor,jakarta.activation.DataSource) throws java.io.IOException
meth public abstract void writeTo(java.lang.Object,java.lang.String,java.io.OutputStream) throws java.io.IOException

CLSS public abstract interface jakarta.activation.DataContentHandlerFactory
meth public abstract jakarta.activation.DataContentHandler createDataContentHandler(java.lang.String)

CLSS public jakarta.activation.DataHandler
cons public <init>(jakarta.activation.DataSource)
cons public <init>(java.lang.Object,java.lang.String)
cons public <init>(java.net.URL)
meth public boolean isDataFlavorSupported(jakarta.activation.ActivationDataFlavor)
meth public jakarta.activation.ActivationDataFlavor[] getTransferDataFlavors()
meth public jakarta.activation.CommandInfo getCommand(java.lang.String)
meth public jakarta.activation.CommandInfo[] getAllCommands()
meth public jakarta.activation.CommandInfo[] getPreferredCommands()
meth public jakarta.activation.DataSource getDataSource()
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.Object getBean(jakarta.activation.CommandInfo)
meth public java.lang.Object getContent() throws java.io.IOException
meth public java.lang.Object getTransferData(jakarta.activation.ActivationDataFlavor) throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public static void setDataContentHandlerFactory(jakarta.activation.DataContentHandlerFactory)
meth public void setCommandMap(jakarta.activation.CommandMap)
meth public void writeTo(java.io.OutputStream) throws java.io.IOException
supr java.lang.Object
hfds currentCommandMap,dataContentHandler,dataSource,emptyFlavors,factory,factoryDCH,objDataSource,object,objectMimeType,oldFactory,shortType,transferFlavors

CLSS public abstract interface jakarta.activation.DataSource
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException
meth public abstract java.io.OutputStream getOutputStream() throws java.io.IOException
meth public abstract java.lang.String getContentType()
meth public abstract java.lang.String getName()

CLSS public jakarta.activation.FileDataSource
cons public <init>(java.io.File)
cons public <init>(java.lang.String)
intf jakarta.activation.DataSource
meth public java.io.File getFile()
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public void setFileTypeMap(jakarta.activation.FileTypeMap)
supr java.lang.Object
hfds _file,typeMap

CLSS public abstract jakarta.activation.FileTypeMap
cons public <init>()
meth public abstract java.lang.String getContentType(java.io.File)
meth public abstract java.lang.String getContentType(java.lang.String)
meth public static jakarta.activation.FileTypeMap getDefaultFileTypeMap()
meth public static void setDefaultFileTypeMap(jakarta.activation.FileTypeMap)
supr java.lang.Object
hfds defaultMap,map

CLSS public jakarta.activation.MailcapCommandMap
cons public <init>()
cons public <init>(java.io.InputStream)
cons public <init>(java.lang.String) throws java.io.IOException
meth public jakarta.activation.CommandInfo getCommand(java.lang.String,java.lang.String)
meth public jakarta.activation.CommandInfo[] getAllCommands(java.lang.String)
meth public jakarta.activation.CommandInfo[] getPreferredCommands(java.lang.String)
meth public jakarta.activation.DataContentHandler createDataContentHandler(java.lang.String)
meth public java.lang.String[] getMimeTypes()
meth public java.lang.String[] getNativeCommands(java.lang.String)
meth public void addMailcap(java.lang.String)
supr jakarta.activation.CommandMap
hfds DB,PROG,confDir

CLSS public jakarta.activation.MimeType
cons public <init>()
cons public <init>(java.lang.String) throws jakarta.activation.MimeTypeParseException
cons public <init>(java.lang.String,java.lang.String) throws jakarta.activation.MimeTypeParseException
intf java.io.Externalizable
meth public boolean match(jakarta.activation.MimeType)
meth public boolean match(java.lang.String) throws jakarta.activation.MimeTypeParseException
meth public jakarta.activation.MimeTypeParameterList getParameters()
meth public java.lang.String getBaseType()
meth public java.lang.String getParameter(java.lang.String)
meth public java.lang.String getPrimaryType()
meth public java.lang.String getSubType()
meth public java.lang.String toString()
meth public void readExternal(java.io.ObjectInput) throws java.io.IOException,java.lang.ClassNotFoundException
meth public void removeParameter(java.lang.String)
meth public void setParameter(java.lang.String,java.lang.String)
meth public void setPrimaryType(java.lang.String) throws jakarta.activation.MimeTypeParseException
meth public void setSubType(java.lang.String) throws jakarta.activation.MimeTypeParseException
meth public void writeExternal(java.io.ObjectOutput) throws java.io.IOException
supr java.lang.Object
hfds TSPECIALS,parameters,primaryType,serialVersionUID,subType

CLSS public jakarta.activation.MimeTypeParameterList
cons public <init>()
cons public <init>(java.lang.String) throws jakarta.activation.MimeTypeParseException
meth protected void parse(java.lang.String) throws jakarta.activation.MimeTypeParseException
meth public boolean isEmpty()
meth public int size()
meth public java.lang.String get(java.lang.String)
meth public java.lang.String toString()
meth public java.util.Enumeration getNames()
meth public void remove(java.lang.String)
meth public void set(java.lang.String,java.lang.String)
supr java.lang.Object
hfds TSPECIALS,parameters

CLSS public jakarta.activation.MimeTypeParseException
cons public <init>()
cons public <init>(java.lang.String)
supr java.lang.Exception
hfds serialVersionUID

CLSS public jakarta.activation.MimetypesFileTypeMap
cons public <init>()
cons public <init>(java.io.InputStream)
cons public <init>(java.lang.String) throws java.io.IOException
meth public java.lang.String getContentType(java.io.File)
meth public java.lang.String getContentType(java.lang.String)
meth public void addMimeTypes(java.lang.String)
supr jakarta.activation.FileTypeMap
hfds DB,PROG,confDir,defaultType

CLSS public jakarta.activation.URLDataSource
cons public <init>(java.net.URL)
intf jakarta.activation.DataSource
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public java.net.URL getURL()
supr java.lang.Object
hfds url,url_conn

CLSS public jakarta.activation.UnsupportedDataTypeException
cons public <init>()
cons public <init>(java.lang.String)
supr java.io.IOException
hfds serialVersionUID

CLSS public abstract interface java.io.Externalizable
intf java.io.Serializable
meth public abstract void readExternal(java.io.ObjectInput) throws java.io.IOException,java.lang.ClassNotFoundException
meth public abstract void writeExternal(java.io.ObjectOutput) throws java.io.IOException

CLSS public java.io.IOException
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr java.lang.Exception
hfds serialVersionUID

CLSS public abstract interface java.io.Serializable

CLSS public java.lang.Exception
cons protected <init>(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr java.lang.Throwable
hfds serialVersionUID

CLSS public java.lang.Object
cons public <init>()
meth protected java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected void finalize() throws java.lang.Throwable
meth public boolean equals(java.lang.Object)
meth public final java.lang.Class<?> getClass()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait() throws java.lang.InterruptedException
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public int hashCode()
meth public java.lang.String toString()

CLSS public java.lang.Throwable
cons protected <init>(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
intf java.io.Serializable
meth public final java.lang.Throwable[] getSuppressed()
meth public final void addSuppressed(java.lang.Throwable)
meth public java.lang.StackTraceElement[] getStackTrace()
meth public java.lang.String getLocalizedMessage()
meth public java.lang.String getMessage()
meth public java.lang.String toString()
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Throwable getCause()
meth public java.lang.Throwable initCause(java.lang.Throwable)
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public void setStackTrace(java.lang.StackTraceElement[])
supr java.lang.Object
hfds CAUSE_CAPTION,EMPTY_THROWABLE_ARRAY,NULL_CAUSE_MESSAGE,SELF_SUPPRESSION_MESSAGE,SUPPRESSED_CAPTION,SUPPRESSED_SENTINEL,UNASSIGNED_STACK,backtrace,cause,detailMessage,serialVersionUID,stackTrace,suppressedExceptions
hcls PrintStreamOrWriter,SentinelHolder,WrappedPrintStream,WrappedPrintWriter

