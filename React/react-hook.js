--------------------------------
Hook
--------------------------------
	# React 16.8 的新增特性。可以在不编写 class 的情况下使用 state 以及其他的 React 特性
	
	# Hook 不能在 class 组件中使用 
		* 不使用 class 也能使用 React。（不推荐把你已有的组件全部重写，但是可以在新组件里开始使用 Hook。）

--------------------------------
State Hook
--------------------------------

	const [count, setCount] = React.useState(0); // [0, function(fiber, queue, action)){.....}]
		
	
	# 通过唯一的参数, 设置一个初始化值, 可以是任意数据
	# useState 会返回一对值: 初始值一个更新它的函数
	# 可以在一个组件中多次使用 State Hook
		* 多次调用 useState 的时候，能保证每次渲染时它们的调用顺序是不变的

	#  state 只在组件首次渲染的时候被创建
		* 在下一次重新渲染时，useState 返回给我们当前的 state



--------------------------------
Effect Hook
--------------------------------
	// 相当于 componentDidMount 和 componentDidUpdate:
	React.useEffect(() => {
		// 使用浏览器的 API 更新页面标题
		document.title = `You clicked ${count} times`;
	});

	
	# 它跟 class 组件中的 componentDidMount、componentDidUpdate 和 componentWillUnmount 具有相同的用途，只不过被合并成了一个 API