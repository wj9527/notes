------------------------
Selector				|
------------------------
	* 只要 ServerSocketChannel 或者 SocketChannel 向 Selector 注册了特定的事件
	  Selector 就会监控这些事件是否发生,SelectableChannel 的 register() 方法负责注册事件
	  该方法返回一个 SelectionKey 对象,该对象是用于跟踪这些被注册事件的句柄
	
	* 一个 Selector 会包含3种类型的 SelectionKey 集合
		all-keys
			* 当前所有向 Selector 注册 SelectionKey 集合
			* Selector 的 keys() 方法返回该集合
		
		slected-keys
			* 相关事件已经被 Selector 捕获,的 SelectionKey 集合
			* Selector 的 selectedKeys() 方法返回该集合
		
		cancelled-keys
			* 已经被取消的 SelectionKey 的集合
			* Selector 没有提供方法这种集合的方法
	
	* 当 SelectableChannel 执行 register() 方法时,该方法会新建一个 SelectionKey ,并把它加入到 Selector 的 all-keys 集合中
	
	* 如果关闭了 SelectionKey 对象关联的 Channel 对象,或者调用了 SelectionKey 对象的 cancel() 方法
	  这个 SelectionKey 对象就会被加入到 cancelled-key 集合中,表示这个 SelectionKey 已经被取消
	  程序执行下一次 Selector 的 select() 方法时,被取消的 SelectionKey 对象将从所有集合中删除
	
	* 在 Selector 执行 select() 方法的时候,如果与 SelectionKey 相关的事件发生了,这个 SelectionKey 就会被加入到:selected-keys 集合中
	  程序直接调用 selected-keys 集合的 remove() 方法,或者调用它的 Iterator 的remove() 方法都可以从 selected-keys 集合中删除一个 SelectionKey 对象
	  程序不允许,直接通过集合集合的 remove() 方法,删除 all-keys 集合中的 SelectionKey,如果程序视图这么做,会抛出异常:UnsupportedOperationException

------------------------
Selector-api			|
------------------------
	