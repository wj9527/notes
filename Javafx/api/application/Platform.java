----------------------
Platform
----------------------
	# Platform 一个静态工具类, 平台!

	# 静态方法
		void runLater(Runnable runnable)
		boolean isFxApplicationThread()
		void exit()
			* 退出应用

		ReadOnlyBooleanProperty accessibilityActiveProperty()
		boolean isAccessibilityActive()
		boolean isImplicitExit()
		boolean isSupported(ConditionalFeature feature)
		void setImplicitExit(boolean implicitExit)