----------------------------
vue-组件					|
----------------------------
	# 个组件本质上是一个拥有预定义选项的一个 Vue 实例
	# 向vue注册一个组件
		// 定义名为 todo-item 的新组件
		Vue.component('todo-item', {
			template: '<li>这是个待办项</li>'
		})

	# 构建模版组件
		<ol>
			<!-- 创建一个 todo-item 组件的实例 -->
			<todo-item></todo-item>
		</ol>
	


