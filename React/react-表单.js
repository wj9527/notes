--------------------------
React 表单
--------------------------
	# 受控组件
		* 除了 <input type="file" /> 以外, 表单组件都是受控组件
	
		* 需要把 value 绑定到组件的state/props
		* value的修改, 需要通过事件方法, 去修改 state/props 来达到
	
	# select 标签
		* React 并不会使用 selected 属性, 而是在根 select 标签上使用 value 属性
		* 如果是多选框的话, 可以用数组
			<select multiple={true} value={['B', 'C']}>
	
	# 指定了 value, 但输入仍可编辑
		* 可能是意外地将 value 设置为 undefined 或 null
			ReactDOM.render(<input value={null} />, mountNode);
	

