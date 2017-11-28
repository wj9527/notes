----------------------------
vue-组件					|
----------------------------
	# 个组件本质上是一个拥有预定义选项的一个 Vue 实例
	# 向vue注册一个全局组件
		// 定义名为 todo-item 的新组件
		Vue.component('todo-item', {
			template: '<li>这是个待办项</li>'
		})

	# 构建模版组件
		<ol>
			<!-- 创建一个 todo-item 组件的实例 -->
			<todo-item></todo-item>
		</ol>
	

----------------------------
vue-全局注册				|
----------------------------
	Vue.component('todo-item', {
		template: 'hello'
	})
	* 对于自定义标签的命名 Vue.js 不强制遵循 W3C 规则 
	* 组件在注册之后,便可以作为自定义元素 <todo-item></todo-item> 在一个实例的模板中使用
	* 注意确保在初始化根实例之前注册组件


----------------------------
vue-局部注册				|
----------------------------
	# 不必把每个组件都注册到全局,可以通过某个 Vue 实例/组件的实例选项:'components' 注册仅在其作用域中可用的组件

		var Child = {
			template: '<div>A custom component!</div>'
		}

		new Vue({
			// ...
			components: {
				// <my-component> 将只在父组件模板中可用
				'my-component': Child
			}
		})
		* 这种封装也适用于其它可注册的 Vue 功能,比如指令
		* 通过组件/实例的 components 属性来注册局部的组件,该属性对象的key就是组件名称,value是一个对象,就是组件属性值
			components: {
				'组件名称': {
					template:'组件...N多属性'
				}
			}
		

----------------------------
vue-DOM模版解析注意			|
----------------------------
	...没读懂

----------------------------
vue-data 必须是函数			|
----------------------------
	# 构造 Vue 实例时传入的各种选项大多数都可以在组件里使用,只有一个例外:data 必须是函数
	# 实际上,如果这么做:
		Vue.component('my-component', {
			template: '<span>{{ message }}</span>',
			data: {
				message: 'hello'
			}
		})
		* Vue 会停止运行,并在控制台发出警告.告诉你在组件实例中 data 必须是一个函数
		* The "data" option should be a function that returns a per-instance value in component definitions.

	# 代码解释
		 <div id="app">
            <my-btn ></my-btn>
            <my-btn ></my-btn>
            <my-btn ></my-btn>
        </div>

		//预定义
        var num = {
            "value" : 1
        }

        Vue.component('my-btn',{
            template:'<button v-on:click="value += 1">{{ value }}</button>',
            data:function(){
                //每个组件返回的都是不同的对象,点击摁钮,只会增加自己按钮上的值
                return {
                    "value" : 1
                };
            },
            data:function(){
                //直接返回预定义对象,则每个组件都会使用同一个对象,点击摁钮,所有摁钮都会增加自己摁钮上的值
                return num;
            },
        });

        new Vue({
            el:'#app',
        });

----------------------------
vue-组件组合使用			|
----------------------------
	
	# 组件设计初衷就是要配合使用的,最常见的就是形成父子组件的关系
	# 组件 A 在它的模板中使用了组件 B,它们之间必然需要相互通信:
		* 父组件可能要给子组件下发数据
		* 子组件则可能要将它内部发生的事情告知父组件
		* 然而,通过一个良好定义的接口来尽可能将父子组件解耦也是很重要的
		* 这保证了每个组件的代码可以在相对隔离的环境中书写和理解,从而提高了其可维护性和复用性
	# 在 Vue 中,父子组件的关系可以总结为 
		* prop 向下传递	父组件通过 prop 给子组件下发数据
		* 事件向上传递	子组件通过事件给父组件发送消息


----------------------------
vue-组件-prop				|
----------------------------
	# 组件实例的作用域是孤立的
	# 这意味着不能 (也不应该) 在子组件的模板内直接引用父组件的数据
	# '父组件的数据需要通过 prop 才能下发到子组件中'
	# '子组件要显式地用 props 选项声明它预期的数据'

	
		<div id="app">
			<child message="hello!"></child>
		</div>

		Vue.component('child', {
			// 声明 props
			props: ['message'],
			// 就像 data 一样，prop 也可以在模板中使用
			// 同样也可以在 vm 实例中通过 this.message 来使用
			template: '<span>{{ message }}</span>'
		})
		new Vue({el:'#app'});
	
	# 命名注意
		* HTML 特性是不区分大小写的,所以,当使用的不是字符串模板时,camelCase (驼峰式命名) 的 prop 需要转换为相对应的 kebab-case (短横线分隔式命名)

			Vue.component('child', {
				// 在 JavaScript 中使用 驼峰
				props: ['myMessage'],
				template: '<span>{{ myMessage }}</span>'
			})
			<!-- 在 HTML 中使用 下划线 -->
			<child my-message="hello!"></child>
		
		* 如果使用字符串模板,则没有这些限制
	
	# 动态 prop
		* 与绑定到任何普通的 HTML 特性相类似,以用 v-bind 来动态地将 prop 绑定到父组件的数据
		* 每当父组件的数据变化时,该变化也会传导给子组件
			<div id="app">
				<input v-model="parentMsg"/><br/>
				//绑定 child 组件的 my-message 到父级组件的 parentMsg
				<child v-bind:my-message="parentMsg"></child>
			</div>
			Vue.component('child',{
				props:['myMessage'],
				template: '<span>{{ myMessage }}</span>'
			});
			var app = new Vue({
				el:'#app',
				data:{
					parentMsg:'',
				}
			});
		* 可以使用 v-bind 的缩写语法
			<child :my-message="parentMsg"></child>
	
	# 把一个对象的所有属性作为 prop 进行传递,可以使用不带任何参数的 v-bind
		* 直接把一个对象的所有属性作为prop
			todo: {
				text: 'Learn Vue',
				isComplete: false
			}
			<todo-item v-bind="todo"></todo-item>

		* 以上写法等同于
			<todo-item
				v-bind:text="todo.text"
				v-bind:is-complete="todo.isComplete">
			</todo-item>
		
		* 对象的key就是bind:的属性名称,如果是驼峰,会转换为中划风格的命名