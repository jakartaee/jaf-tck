#Signature file v4.1
#Version 

CLSS public java.awt.datatransfer.DataFlavor
cons public <init>()
cons public <init>(java.lang.Class<?>,java.lang.String)
cons public <init>(java.lang.String) throws java.lang.ClassNotFoundException
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.ClassLoader) throws java.lang.ClassNotFoundException
fld public final static java.awt.datatransfer.DataFlavor imageFlavor
fld public final static java.awt.datatransfer.DataFlavor javaFileListFlavor
fld public final static java.awt.datatransfer.DataFlavor plainTextFlavor
 anno 0 java.lang.Deprecated(boolean forRemoval=false, java.lang.String since="")
fld public final static java.awt.datatransfer.DataFlavor stringFlavor
fld public final static java.lang.String javaJVMLocalObjectMimeType = "application/x-java-jvm-local-objectref"
fld public final static java.lang.String javaRemoteObjectMimeType = "application/x-java-remote-object"
fld public final static java.lang.String javaSerializedObjectMimeType = "application/x-java-serialized-object"
fld public static java.awt.datatransfer.DataFlavor allHtmlFlavor
fld public static java.awt.datatransfer.DataFlavor fragmentHtmlFlavor
fld public static java.awt.datatransfer.DataFlavor selectionHtmlFlavor
intf java.io.Externalizable
intf java.lang.Cloneable
meth protected final static java.lang.Class<?> tryToLoadClass(java.lang.String,java.lang.ClassLoader) throws java.lang.ClassNotFoundException
meth protected java.lang.String normalizeMimeType(java.lang.String)
 anno 0 java.lang.Deprecated()
meth protected java.lang.String normalizeMimeTypeParameter(java.lang.String,java.lang.String)
 anno 0 java.lang.Deprecated()
meth public boolean equals(java.awt.datatransfer.DataFlavor)
meth public boolean equals(java.lang.Object)
meth public boolean equals(java.lang.String)
 anno 0 java.lang.Deprecated(boolean forRemoval=false, java.lang.String since="")
meth public boolean isFlavorJavaFileListType()
meth public boolean isFlavorRemoteObjectType()
meth public boolean isFlavorSerializedObjectType()
meth public boolean isFlavorTextType()
meth public boolean isMimeTypeEqual(java.lang.String)
meth public boolean isMimeTypeSerializedObject()
meth public boolean isRepresentationClassByteBuffer()
meth public boolean isRepresentationClassCharBuffer()
meth public boolean isRepresentationClassInputStream()
meth public boolean isRepresentationClassReader()
meth public boolean isRepresentationClassRemote()
meth public boolean isRepresentationClassSerializable()
meth public boolean match(java.awt.datatransfer.DataFlavor)
meth public final boolean isMimeTypeEqual(java.awt.datatransfer.DataFlavor)
meth public final java.lang.Class<?> getDefaultRepresentationClass()
meth public final java.lang.String getDefaultRepresentationClassAsString()
meth public final static java.awt.datatransfer.DataFlavor getTextPlainUnicodeFlavor()
meth public final static java.awt.datatransfer.DataFlavor selectBestTextFlavor(java.awt.datatransfer.DataFlavor[])
meth public int hashCode()
meth public java.io.Reader getReaderForText(java.awt.datatransfer.Transferable) throws java.awt.datatransfer.UnsupportedFlavorException,java.io.IOException
meth public java.lang.Class<?> getRepresentationClass()
meth public java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth public java.lang.String getHumanPresentableName()
meth public java.lang.String getMimeType()
meth public java.lang.String getParameter(java.lang.String)
meth public java.lang.String getPrimaryType()
meth public java.lang.String getSubType()
meth public java.lang.String toString()
meth public void readExternal(java.io.ObjectInput) throws java.io.IOException,java.lang.ClassNotFoundException
meth public void setHumanPresentableName(java.lang.String)
meth public void writeExternal(java.io.ObjectOutput) throws java.io.IOException
supr java.lang.Object
hfds atom,humanPresentableName,ioInputStreamClass,mimeType,representationClass,serialVersionUID,textFlavorComparator
hcls TextFlavorComparator

CLSS public abstract interface java.awt.datatransfer.Transferable
meth public abstract boolean isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
meth public abstract java.awt.datatransfer.DataFlavor[] getTransferDataFlavors()
meth public abstract java.lang.Object getTransferData(java.awt.datatransfer.DataFlavor) throws java.awt.datatransfer.UnsupportedFlavorException,java.io.IOException

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

CLSS public abstract interface java.lang.Cloneable

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
 anno 0 java.lang.Deprecated(boolean forRemoval=false, java.lang.String since="9")
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

CLSS public javax.activation.ActivationDataFlavor
cons public <init>(java.lang.Class,java.lang.String)
cons public <init>(java.lang.Class,java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
meth protected java.lang.String normalizeMimeType(java.lang.String)
meth protected java.lang.String normalizeMimeTypeParameter(java.lang.String,java.lang.String)
meth public boolean equals(java.awt.datatransfer.DataFlavor)
meth public boolean isMimeTypeEqual(java.lang.String)
meth public java.lang.Class getRepresentationClass()
meth public java.lang.String getHumanPresentableName()
meth public java.lang.String getMimeType()
meth public void setHumanPresentableName(java.lang.String)
supr java.awt.datatransfer.DataFlavor
hfds humanPresentableName,mimeObject,mimeType,representationClass

CLSS public javax.activation.CommandInfo
cons public <init>(java.lang.String,java.lang.String)
meth public java.lang.Object getCommandObject(javax.activation.DataHandler,java.lang.ClassLoader) throws java.io.IOException,java.lang.ClassNotFoundException
meth public java.lang.String getCommandClass()
meth public java.lang.String getCommandName()
supr java.lang.Object
hfds className,verb

CLSS public abstract javax.activation.CommandMap
cons public <init>()
meth public abstract javax.activation.CommandInfo getCommand(java.lang.String,java.lang.String)
meth public abstract javax.activation.CommandInfo[] getAllCommands(java.lang.String)
meth public abstract javax.activation.CommandInfo[] getPreferredCommands(java.lang.String)
meth public abstract javax.activation.DataContentHandler createDataContentHandler(java.lang.String)
meth public java.lang.String[] getMimeTypes()
meth public javax.activation.CommandInfo getCommand(java.lang.String,java.lang.String,javax.activation.DataSource)
meth public javax.activation.CommandInfo[] getAllCommands(java.lang.String,javax.activation.DataSource)
meth public javax.activation.CommandInfo[] getPreferredCommands(java.lang.String,javax.activation.DataSource)
meth public javax.activation.DataContentHandler createDataContentHandler(java.lang.String,javax.activation.DataSource)
meth public static javax.activation.CommandMap getDefaultCommandMap()
meth public static void setDefaultCommandMap(javax.activation.CommandMap)
supr java.lang.Object
hfds defaultCommandMap,map

CLSS public abstract interface javax.activation.CommandObject
meth public abstract void setCommandContext(java.lang.String,javax.activation.DataHandler) throws java.io.IOException

CLSS public abstract interface javax.activation.DataContentHandler
meth public abstract java.awt.datatransfer.DataFlavor[] getTransferDataFlavors()
meth public abstract java.lang.Object getContent(javax.activation.DataSource) throws java.io.IOException
meth public abstract java.lang.Object getTransferData(java.awt.datatransfer.DataFlavor,javax.activation.DataSource) throws java.awt.datatransfer.UnsupportedFlavorException,java.io.IOException
meth public abstract void writeTo(java.lang.Object,java.lang.String,java.io.OutputStream) throws java.io.IOException

CLSS public abstract interface javax.activation.DataContentHandlerFactory
meth public abstract javax.activation.DataContentHandler createDataContentHandler(java.lang.String)

CLSS public javax.activation.DataHandler
cons public <init>(java.lang.Object,java.lang.String)
cons public <init>(java.net.URL)
cons public <init>(javax.activation.DataSource)
intf java.awt.datatransfer.Transferable
meth public boolean isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
meth public java.awt.datatransfer.DataFlavor[] getTransferDataFlavors()
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.Object getBean(javax.activation.CommandInfo)
meth public java.lang.Object getContent() throws java.io.IOException
meth public java.lang.Object getTransferData(java.awt.datatransfer.DataFlavor) throws java.awt.datatransfer.UnsupportedFlavorException,java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public javax.activation.CommandInfo getCommand(java.lang.String)
meth public javax.activation.CommandInfo[] getAllCommands()
meth public javax.activation.CommandInfo[] getPreferredCommands()
meth public javax.activation.DataSource getDataSource()
meth public static void setDataContentHandlerFactory(javax.activation.DataContentHandlerFactory)
meth public void setCommandMap(javax.activation.CommandMap)
meth public void writeTo(java.io.OutputStream) throws java.io.IOException
supr java.lang.Object
hfds currentCommandMap,dataContentHandler,dataSource,emptyFlavors,factory,factoryDCH,objDataSource,object,objectMimeType,oldFactory,shortType,transferFlavors

CLSS public abstract interface javax.activation.DataSource
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException
meth public abstract java.io.OutputStream getOutputStream() throws java.io.IOException
meth public abstract java.lang.String getContentType()
meth public abstract java.lang.String getName()

CLSS public javax.activation.FileDataSource
cons public <init>(java.io.File)
cons public <init>(java.lang.String)
intf javax.activation.DataSource
meth public java.io.File getFile()
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public void setFileTypeMap(javax.activation.FileTypeMap)
supr java.lang.Object
hfds _file,typeMap

CLSS public abstract javax.activation.FileTypeMap
cons public <init>()
meth public abstract java.lang.String getContentType(java.io.File)
meth public abstract java.lang.String getContentType(java.lang.String)
meth public static javax.activation.FileTypeMap getDefaultFileTypeMap()
meth public static void setDefaultFileTypeMap(javax.activation.FileTypeMap)
supr java.lang.Object
hfds defaultMap,map

CLSS public javax.activation.MailcapCommandMap
cons public <init>()
cons public <init>(java.io.InputStream)
cons public <init>(java.lang.String) throws java.io.IOException
meth public java.lang.String[] getMimeTypes()
meth public java.lang.String[] getNativeCommands(java.lang.String)
meth public javax.activation.CommandInfo getCommand(java.lang.String,java.lang.String)
meth public javax.activation.CommandInfo[] getAllCommands(java.lang.String)
meth public javax.activation.CommandInfo[] getPreferredCommands(java.lang.String)
meth public javax.activation.DataContentHandler createDataContentHandler(java.lang.String)
meth public void addMailcap(java.lang.String)
supr javax.activation.CommandMap
hfds DB,PROG

CLSS public javax.activation.MimeType
cons public <init>()
cons public <init>(java.lang.String) throws javax.activation.MimeTypeParseException
cons public <init>(java.lang.String,java.lang.String) throws javax.activation.MimeTypeParseException
intf java.io.Externalizable
meth public boolean match(java.lang.String) throws javax.activation.MimeTypeParseException
meth public boolean match(javax.activation.MimeType)
meth public java.lang.String getBaseType()
meth public java.lang.String getParameter(java.lang.String)
meth public java.lang.String getPrimaryType()
meth public java.lang.String getSubType()
meth public java.lang.String toString()
meth public javax.activation.MimeTypeParameterList getParameters()
meth public void readExternal(java.io.ObjectInput) throws java.io.IOException,java.lang.ClassNotFoundException
meth public void removeParameter(java.lang.String)
meth public void setParameter(java.lang.String,java.lang.String)
meth public void setPrimaryType(java.lang.String) throws javax.activation.MimeTypeParseException
meth public void setSubType(java.lang.String) throws javax.activation.MimeTypeParseException
meth public void writeExternal(java.io.ObjectOutput) throws java.io.IOException
supr java.lang.Object
hfds TSPECIALS,parameters,primaryType,subType

CLSS public javax.activation.MimeTypeParameterList
cons public <init>()
cons public <init>(java.lang.String) throws javax.activation.MimeTypeParseException
meth protected void parse(java.lang.String) throws javax.activation.MimeTypeParseException
meth public boolean isEmpty()
meth public int size()
meth public java.lang.String get(java.lang.String)
meth public java.lang.String toString()
meth public java.util.Enumeration getNames()
meth public void remove(java.lang.String)
meth public void set(java.lang.String,java.lang.String)
supr java.lang.Object
hfds TSPECIALS,parameters

CLSS public javax.activation.MimeTypeParseException
cons public <init>()
cons public <init>(java.lang.String)
supr java.lang.Exception

CLSS public javax.activation.MimetypesFileTypeMap
cons public <init>()
cons public <init>(java.io.InputStream)
cons public <init>(java.lang.String) throws java.io.IOException
meth public java.lang.String getContentType(java.io.File)
meth public java.lang.String getContentType(java.lang.String)
meth public void addMimeTypes(java.lang.String)
supr javax.activation.FileTypeMap
hfds DB,PROG,defaultType

CLSS public javax.activation.URLDataSource
cons public <init>(java.net.URL)
intf javax.activation.DataSource
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public java.net.URL getURL()
supr java.lang.Object
hfds url,url_conn

CLSS public javax.activation.UnsupportedDataTypeException
cons public <init>()
cons public <init>(java.lang.String)
supr java.io.IOException

