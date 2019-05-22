--------------------------
jvm参数					  |
--------------------------

--------------------------
jvm参数	统计			  |
--------------------------

-verbose:gc -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
-verbose:class -XX:+TraceClassLoading -XX:+TraceClassUnloading

-Xmn
-Xss
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
	* 打印GC日志详情
-XX:+PrintGCTimeStamps  
	* 打印GC时间戳

-XX:+TraceClassLoading
	* 打印类加载信息

-XX:+TraceClassUnLoading
	* 打印类卸载 信息










-XX:ParallelGCThreads
	* 设置 ParNew 收集器的收集线程数量

-XX:MaxGCPauseMillis
	* 置最大垃圾收集停顿时间, 它的值是一个大于 0 的整数
	* 收集器在工作时, 会调整 Java 堆大小或者其他一些参数,尽可能地把停顿时间控制在 MaxGCPauseMillis 以内
	* 停顿时间的缩短, 是牺牲了吞吐量(以前10s一次100ms的GC, 现在5s一次70ms的GC)和新生代空间(对体积小的内存收集比较快)换来的, 这也导致GC发生得更加的频繁
	* 过小的话, GC停顿时间确实下降了,　但是吞吐量也下降了

-XX:GCTimeRatio
	* 设置吞吐量大小, 它的值是一个大于 0 小于 100 之间的整数
	* 可以理解为: 垃圾收集时间占总时间的比例
	* 默认 GCTimeRatio 的值为 99, 那么系统将花费不超过 1 / (1 + 99) = 1% 的时间用于垃圾收集



-XX:SurvivorRatio
-XX:PretenureSizeThreshold
-XX:HandlePromotionFailure

-XX:+UseConMarkSweepGC
	* 使用Concurrent Mark Sweep(CMS)作为老年代收集器
	* 如果使用该参数, 默认就会使用: ParNew 作为新生代的收集器

-XX:+UseParNewGC
	* 强制系统使用 ParNew 作为新生代的收集器

-XX:+UseAdaptiveSizePolicy
	* 打开自适应 GC 策略, 在这种模式下, 其他的一些属性不需要自己去设置, 参数会被自动调整, 以达到在堆大小, 吞吐量和停顿时间之间的平衡点
		-Xmn(新生代大小)
		-XX:+SuivivorRatio(Eden和Survivor区的比例)
		-XX:+PretenureSizeThreshold(晋升老年代对象年龄)
	* 使用自适应GC策略, 只需要把基本的内存数据设置好,例如堆内存大小值
	* 然后仅仅关注/设置最大停顿时间:-XX:MaxGCPauseMillis 
	* 或者给JVM设置一个最大吞吐量 -XX:GCTimeRatio 的优化目标, 具体的工作细节就由jvm完成


-XX:+SerialGC
-XX:+UseParallelGC
-XX:+UseParallelOldGC
-XX:+UseG1GC