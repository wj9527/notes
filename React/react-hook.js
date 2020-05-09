--------------------------------
Hook
--------------------------------
	# React 16.8 的新增特性。可以在不编写 class 的情况下使用 state 以及其他的 React 特性
	
	# Hook 不能在 class 组件中使用 
		* 不使用 class 也能使用 React。（不推荐把已有的组件全部重写，但是可以在新组件里开始使用 Hook。）

--------------------------------
State Hook
--------------------------------
	function App (props){
		const[count, setCount] = React.useState(0);  // [0, function(fiber, queue, action)){.....}]
		return (
			<div>
				<div>点击了： { count } 次 </div>
				<button onClick={ () => setCount(count + 1) }>点击我</button>
			</div>
		)
	}

	
	# 通过唯一的参数, 设置一个初始化值, 可以是任意数据
	# useState 会返回一对值: 初始值一个更新它的函数
	# 可以在一个组件中多次使用 State Hook
		* 多次调用 useState 的时候，能保证每次渲染时它们的调用顺序是不变的

	#  state 只在组件首次渲染的时候被创建
		* 在下一次重新渲染时，useState 返回给我们当前的 state
	
	
	

--------------------------------
Effect Hook
--------------------------------
	
	function App (props){
		const[count, setCount] = React.useState(0);
		React.useEffect(() => {    // 相当于 componentDidMount 和 componentDidUpdate:
			window.document.title = `点击了 ${count} 次`;
			return () => {
				console.log('我将会在组件被卸载的时候执行');
			}
		});
		return (
			<div>
				<div>点击了： { count } 次 </div>
				<button onClick={ () => setCount(count + 1) }>点击我</button>
			</div>
		)
	}
	
	# 它跟 class 组件中的 componentDidMount、componentDidUpdate 具有相同的用途，只不过被合并成了一个 API	
		* 它在第一次渲染之后和每次更新之后都会执行
		* 每次运行 effect 的同时，DOM 都已经更新完毕。

		* 与 componentDidMount 或 componentDidUpdate 不同，使用 useEffect 调度的 effect 不会阻塞浏览器更新屏幕，这让应用看起来响应更快
		* 大多数情况下，effect 不需要同步地执行。在个别情况下（例如测量布局），有单独的 useLayoutEffect Hook 供使用，其 API 与 useEffect 相同。
	
	# 如果 effect 返回一个函数，React 将会在执行清除操作时调用它。和 componentWillUnmount 具有相同的用途
		* 这是 effect 可选的清除机制。每个 effect 都可以返回一个清除函数。
		*  React 会在组件卸载的时候执行清除操作
			React.useEffect(() => {
				return () => {
					console.log('我将会在组件被卸载的时候执行');
				}
			});
		
		* 如果不需要清除逻辑，那么就不用返回这个函数
	





