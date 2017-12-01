----------------------------
vue-组件					|
----------------------------
	# 个组件本质上是一个拥有预定义选项的一个 Vue 实例
	# 所有的 Vue 组件同时也都是 Vue 的实例,所以可接受相同的选项对象 (除了一些根级特有的选项) 并提供相同的生命周期钩子。
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
	
	# 对于自定义标签的命名 Vue.js 不强制遵循 W3C 规则 (小写，并且包含一个短杠),尽管这被认为是最佳实践

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
		
			* key尽量以字符串形式命名,因为有横线
				components:{
					'my-el':{
						template:'<h1>neibu</h1>'
					}
				}
				

----------------------------
vue-DOM模版解析注意			|
----------------------------
	# 当使用 DOM 作为模板时 (例如,使用 el 选项来把 Vue 实例挂载到一个已有内容的元素上),你会受到 HTML 本身的一些限制
	#  Vue 只有在浏览器解析,规范化模板之后才能获取其内容
	# 尤其要注意,像 <ul>,<ol>,<table>,<select> 这样的元素里允许包含的元素有限制
	# 而另一些像 <option> 这样的元素只能出现在某些特定元素的内部
		
		Vue.component('el-tr',{
            template:`<tr><td>Hello</td></tr>`,
        });

		<div id="app">
            <table>
                <el-tr></el-tr>
            </table>
        </div>

		<div id="app">
            <table>
                <tr is="el-tr"></tr>
            </table>
        </div>
	
	# 一些HTML元素内部,只能出现指定的标签,如果使用自定义的标签(组件)有可能会渲染失败,
	# so,用　is="组件" 来完成vue的渲染

	# 使用来自以下来源之一的字符串模板,则没有这些限制
		<script type="text/x-template">
		JavaScript 内联模板字符串
		.vue 组件
	
	# 所以请'尽可能使用字符串模板',少使用DOM模版
		

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

		* 组件要用啥数据?先在 props 中声明
	
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
		
		* '把父级组件的值绑定到子组件上'
	
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
		* demo
			//绑定参数到父级的names对象
			 <el-hello v-bind="names"></el-hello>

			 Vue.component('el-hello',{
				props:['name1','name2'],
				template:'<span>Hello,{{  name1 }},{{ name2 }}</span>',
			});

			var app = new Vue({
				el:'#app',
				data:{
					//定义父级names对象
					names:{
						name1:'Kevin',
						name2:'Litch'
					}
				}
			});

----------------------------
字面量语法 vs 动态语法		|
----------------------------
	<comp some-prop="name"></comp>
		* 没有用 v-bind,这个name只是一个字符串name

	<comp v-bind:some-prop="name"></comp>
		* 使用 v-bind,这个name是一个变量,要从父级组件中寻找获得值


----------------------------
单向数据流					|
----------------------------
	# 父组件的属性变化时,将传导给子组件,但是反过来不会,怕子组件无意改变了父级组件的值
	# 每次父组件更新时,子组件的所有 prop 都会更新为最新值
	# 这意味着你不应该在子组件内部改变 prop,如果你这么做了,Vue 会在控制台给出警告
	# 在两种情况下,我们很容易忍不住想去修改 prop 中数据
		1,Prop 作为初始值传入后,子组件想把它当作局部数据来用
		2,Prop 作为原始数据传入,由子组件处理成其它数据输出
	
	# 对以上两种情况,正确的应对方式是
		1,定义一个局部变量，并用 prop 的值初始化它：
			props: ['initialCounter'],
			data: function () {
				return { counter: this.initialCounter }
			}
			* 定义一个新的值,来作为预初始值(该值会由父组件传值)
			* 然后需要修改的时候,就修改这个新的值


		2,定义一个计算属性，处理 prop 的值并返回
			props: ['size'],
			computed: {
				normalizedSize: function () {
					return this.size.trim().toLowerCase()
				}
			}
			* 以计算属性的形式来处理数据输出
	
	
	# 注意在 JavaScript 中对象和数组是引用类型,指向同一个内存空间
	# '如果 prop 是一个对象或数组,在子组件内部改变它会影响父组件的状态'

----------------------------
Prop 验证					|
----------------------------
	# 我们可以为组件的 prop 指定验证规则,如果传入的数据不符合要求,Vue 会发出警告
	# 这对于开发给他人使用的组件非常有用
	# 要指定验证规则,需要用对象的形式来定义 prop,而不能用字符串数组
	 Vue.component('el-hello',{
            props:{
                //name属性必须是字符串
                name:'String',
                //age属性必须是数字
                age:'Number',
                phone:['String','Number'],
                //pas属性必须是Number,而且该属性值是必须的
                pass:{
                    type:Number,
                    required:true,
                },
                gender:{
                    default:'男'
                },
                skill:{
                    default:function () {
                        return ['Java','Python']
                    }
                },
                hobby:{
                    //对hobby值进行校验
                    validator:function(value){
                        return value == 'code'
                    }
                },
            },
           ... ...
        });
		* type 也可以是一个自定义构造器函数，使用 instanceof 检测
		* 当 prop 验证失败,Vue 会抛出警告 (如果使用的是开发版本)
		
		* 注意 prop 会在组件实例创建之前进行校验,所以在 default 或 validator 函数里,诸如 data,computed 或 methods 等实例属性还无法使用

	# type 可以是下面原生构造器：
		String
		Number
		Boolean
		Function
		Object
		Array
		Symbol


----------------------------
非 Prop 特性				|
----------------------------
	# 意思就是不用定义 prop,直接可以传递参数
	# 为组件定义明确的 prop 是推荐的传参方式,组件的作者却并不总能预见到组件被使用的场景
	# 所以,'组件可以接收任意传入的特性',这些特性都会被'添加到组件的根元素上'
	# demo
		<el-hello name="KevinBlandy" age="23"></el-hello>

		Vue.component('el-hello',{
		props:['name'],
		template:`
			<div>
				<span>{{  name }}</span>
			</div>`,
		});
		
		* 组件没有定义 age 属性,所以age属性会被绑定到根元素<div>上
			<div age="23">
				<span>KevinBlandy</span>
			</div>
		

----------------------------
替换/合并现有的特性			|
----------------------------
	# 假设这是 bs-date-input 的模板
		<input type="date" class="form-control">
		* 为了给该日期选择器插件增加一个特殊的主题,我们可能需要增加一个特殊的 class
			<bs-date-input
				data-3d-date-picker="true"
				class="date-picker-theme-dark"
			></bs-date-input>
		* 在这个例子当中,我们定义了两个不同的 class 值
			form-control，来自组件自身的模板
			date-picker-theme-dark，来自父组件

		* 对于多数特性来说,传递给组件的值会覆盖组件本身设定的值,即例如传递 type="large" 将会覆盖 type="date" 且有可能破坏该组件
		* 所幸我们对待 class 和 style 特性会更聪明一些,这两个特性的值都会做合并 (merge) 操作,让最终生成的值为：form-control date-picker-theme-dark
	
	# demo
		Vue.component('el-hello',{
			props:['name'],
			template:'<div class="c1">{{ name }}</div>',
		});
			* 定义组件的时候,定义了一个 class

		<el-hello name="KevinBlandy" clas="c2"></el-hello>
			* 使用组件时,定义了一个 class 
			* 该class属性会出现在模版根标签上

		<div class="c1" clas="c2">KevinBlandy</div>
			* 最终渲染,这俩属性并存,不会覆盖
	
	
----------------------------
自定义事件					|
----------------------------
	# 父级组件传值给子组件,子组件跟父级组件之间的通信就是靠'自定义事件'
	# 使用 v-on 绑定自定义事件
		* 每个 Vue 实例都实现了事件接口
			$on(eventName)		监听事件
			$emit(eventName)	触发事件
		* Vue 的事件系统与浏览器的 EventTarget API 有所不同
		* 尽管它们的运行起来类似,但是 $on 和 $emit 并不是addEventListener 和 dispatchEvent 的别名
	# 另外,'父组件可以在使用子组件的地方直接用 v-on 来监听子组件触发的事件'
	# 不能用 $on 侦听子组件释放的事件,而必须在模板里直接用 v-on 绑定
	

		<div id="app">
            <p>{{ total }}</p>
            <!-- 绑定子组件的报告事件('increment'),到父级组件方法,该事件触发的时候,调用父级方法:incrementTotal -->
            <button-counter v-on:increment="incrementTotal"></button-counter>
            <button-counter v-on:increment="incrementTotal"></button-counter>
        </div>

		Vue.component('button-counter',{
            //点击触发当前组件定义的事件 incrementCounter
            template:'<button v-on:click="incrementCounter">{{ counter }}</button>',
            data: function () {
                return {
                    counter: 0
                }
            },
            methods: {
                //在触发当前组件事件的时候,使用 this.$emit('报告事件') 触发一个报告事件(increment)
                incrementCounter: function () {
                    this.counter += 1
					//触发子组件的 increment 时间,会调用父级组件的方法进行通知
                    this.$emit('increment')
                }
            },
        });

        var app = new Vue({
            el:'#app',
            data: {
                total: 0
            },
            methods: {
                //父级组件定义的事件,其实就是一个函数而已
                incrementTotal: function () {
                    this.total += 1
                }
            }
        });
			
		* 子组件已经和它外部完全解耦了,它所做的只是报告自己的内部事件,因为父组件可能会关心这些事件
		* 请注意这一点很重要