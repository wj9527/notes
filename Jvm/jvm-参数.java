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
-XX:ParallelGCThreads
	* 设置 ParNew 收集器的收集线程数量

-XX:+HeapDumpOnOutOfMemoryError
-XX:+PrintGCDetails
-XX:+TraceClassLoading
-XX:+TraceClassUnLoading


-XX:SurvivorRatio
-XX:PretenureSizeThreshold
-XX:HandlePromotionFailure

-XX:+UseConMarkSweepGC
	* 使用Concurrent Mark Sweep(CMS)作为老年代收集器
	* 如果使用该参数, 默认就会使用: ParNew 作为新生代的收集器

-XX:+UseParNewGC
	* 强制系统使用 ParNew 作为新生代的收集器

