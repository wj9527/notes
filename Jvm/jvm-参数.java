-verbose:gc -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
-verbose:class -XX:+TraceClassLoading -XX:+TraceClassUnloading
-verbose:class -XX:+TraceClassLoading -XX:+TraceClassUnloading

-Xms
-Xmx
-Xnoclassgc

-verbose:gc
-verbose:class

-XX:PermSize
-XX:MetaspaceSize
-XX:MaxPermSize
-XX:MaxMetaspaceSize
-XX:MaxDirectMemorySize
-XX:MinMetaspaceFreeRatio
-XX:MaxMetaspaceFreeRatio

-XX:+HeapDumpOnOutOfMemoryError
-XX:+PrintGCDetails
-XX:+TraceClassLoading
-XX:+TraceClassUnLoading
