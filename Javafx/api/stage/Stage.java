---------------------
stage				 |
---------------------
	# 继承自 Window 组件

	# 构造函数
		Stage()
		Stage(@Default("javafx.stage.StageStyle.DECORATED") StageStyle style)
	
	# 实例方法
		boolean isAlwaysOnTop()
		void setAlwaysOnTop(boolean value)
			* 窗体是否一直处于最前面

		ReadOnlyBooleanProperty alwaysOnTopProperty()
		void close()
			* 关闭窗口

		ObjectProperty<String> fullScreenExitHintProperty()
		ObjectProperty<KeyCombination> fullScreenExitKeyProperty()
		ReadOnlyBooleanProperty fullScreenProperty()
		String getFullScreenExitHint()
		KeyCombination getFullScreenExitKeyCombination()
		double getMaxHeight()
		double getMaxWidth()
		double getMinHeight()
		double getMinWidth()
		Modality getModality()
		Window getOwner()
		StageStyle getStyle()
		
		void initModality(Modality modality)
			* 设置模态, 枚举
				NONE
				WINDOW_MODAL
				APPLICATION_MODAL  
				


		void initOwner(Window owner)

		void initStyle(StageStyle style)
			* 设置窗体的类型, 枚举
				DECORATED(默认)
				UNDECORATED(纯透明的窗体)
				TRANSPARENT(纯透明的窗体)
				UTILITY(无最大化, 缩小摁钮, 只有关闭)
				UNIFIED(无边框)

		
		boolean isMaximized()
		
		DoubleProperty maxHeightProperty()
		ReadOnlyBooleanProperty maximizedProperty()
		DoubleProperty maxWidthProperty()
		DoubleProperty minHeightProperty()
		DoubleProperty minWidthProperty()
		BooleanProperty resizableProperty()
		
		void setFullScreenExitHint(String value)
		void setFullScreenExitKeyCombination(KeyCombination keyCombination)
		
		void setFullScreen(boolean value)
		boolean isFullScreen()
			* 设置/读取是否全屏,(摁ESC才会退出那种)
			* 需要先设置了 Scene 才会生效
		
		void setMaximized(boolean value)
			* 设置是否是最大化(全屏)
		
		void setMaxHeight(double value)
		void setMaxWidth(double value)
		void setMinHeight(double value)
		void setMinWidth(double value)
			* 设置/读取最大最小的宽高

		void setResizable(boolean value)
		boolean isResizable()
			* 设置/读取是否允许拉伸

		void setScene(Scene value)

		String getTitle()
		void setTitle(String value)
			* 设置/读取标题
		
		ObservableList<Image> getIcons()
		ReadOnlyBooleanProperty iconifiedProperty()
		void setIconified(boolean value)
		boolean isIconified()
			* icon的设置


		void show()
		void showAndWait()
		StringProperty titleProperty()
		void toBack()
		void toFront() 