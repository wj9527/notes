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
				//定义一个临时值 counter,在组件中使用它,修改它不会带来警告
				return { counter: this.initialCounter }
			}
			* 定义一个新的值,来作为预初始值(该值会由父组件传值)
			* 然后需要修改的时候,就修改这个新的值


		2,定义一个计算属性，处理 prop 的值并返回
			props: ['size'],
			computed: {
				//子组件通过父级的值,进行计算后,返回计算属性给子组件使用
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
                //父级组件定义函数,该函数会被子组件通知调用
                incrementTotal: function () {
                    this.total += 1
                }
            }
        });
			
		* 子组件已经和它外部完全解耦了,它所做的只是报告自己的内部事件,因为父组件可能会关心这些事件
		* 请注意这一点很重要

----------------------------		
给组件绑定原生事件			|
----------------------------
	# 有时候,你可能想在某个组件的根元素上监听一个原生事件,可以使用 v-on 的修饰符 .native
		<my-component v-on:click.native="doTheThing"></my-component>

----------------------------
.sync 修饰符				|
----------------------------
	# 2.3.0+

	# 在一些情况下,我们可能会需要对一个 prop 进行"双向绑定",事实上,这正是 Vue 1.x 中的 .sync 修饰符所提供的功能
	# 当一个子组件改变了一个带 .sync 的 prop 的值时,这个变化也会同步到父组件中所绑定的值
	# 这很方便,但也会导致问题,因为它破坏了单向数据流
	# 由于子组件改变 prop 的代码和普通的状态改动代码毫无区别,当光看子组件的代码时,你完全不知道它何时悄悄地改变了父组件的状态
	# 这在 debug 复杂结构的应用时会带来很高的维护成本
	
	# 上面所说的正是我们在 2.0 中移除 .sync 的理由。但是在 2.0 发布之后的实际应用中,我们发现 .sync 还是有其适用之处,比如在开发可复用的组件库时
	# 我们需要做的只是让子组件改变父组件状态的代码更容易被区分
	# 从 2.3.0 起我们重新引入了 .sync 修饰符,但是这次它只是作为一个编译时的语法糖存在,它会被扩展为一个自动更新父组件属性的 v-on 监听器

		<comp :foo.sync="bar"></comp>

		* 会被扩展为
			<comp :foo="bar" @update:foo="val => bar = val"></comp>

		当子组件需要更新 foo 的值时，它需要显式地触发一个更新事件
		this.$emit('update:foo', newValue)
	
	# demo
		 <div id="app">
            {{ count }}<br/>
            <el-demo v-bind:num.sync="count"></el-demo>
            <!--
                //带有 .sync 后缀的属性,会自动渲染出一个通知事件
                    @update:[属性名称]="val => count = val" //箭头函数,修改绑定父级组件的(count)值
                <el-demo v-bind:num.sync="count" v-on:update:num="val => count = val"></el-demo>
            -->
        </div>

		Vue.component('el-demo',{
            props:['num'],
            template:'<button @click="add">{{ temp }}</button>',
            data:function () {
                //子父组件数据单向,我们需要修改,所以用一个中间值过度
                return {temp:this.num}
            },
            methods:{
                //仅仅修改临时值
                add : function(){
                    this.temp += 1;
                    //显示调用方法,修改父级组件的值
                    this.$emit('update:num',this.temp);
                }
            }
        });

        var app = new Vue({
            el:'#app',
            data:{
                count:1
            },
        });

	# 通俗理解
		1,在使用组件的时候, v-bin 绑定属性,是来自于父级
		2,如果在绑定时候,添加 .sync 后缀,则会在组件上渲染出一个通知函数,该函数会主动的去修改父级组件的值,达到子父数据同步的效果
		3,需要手动的在某个方法里面调用这个通知函数
			this.$emit('update:[当前组件属性名称]',newVlue);


--------------------------------
使用自定义事件的表单输入组件 !!!|
--------------------------------
	# 自定义事件可以用来创建自定义的表单输入组件,使用 v-model 来进行数据双向绑定
		<input v-model="something">

		* 这不过是以下示例的语法糖
		<input
			v-bind:value="something"
			v-on:input="something = $event.target.value">
		
		* 所以在组件中使用时,它相当于下面的简写
			<custom-input
				v-bind:value="something"
				v-on:input="something = arguments[0]">
			</custom-input>

	# 要让组件的 v-model 生效,它应该 (从 2.2.0 起是可配置的)
		* 接受一个 value prop
		* 在有新的值时触发 input 事件并将新值作为参数

	# v-model 指令语法糖
		
		<div id="app">
            {{ name }}<br/>
            <input :value="name" @input="name = $event.target.value"/>
        </div>
		<!--
			语法糖
			<input v-model="name"/>
		-->

		var app = new Vue({
            el:'#app',
            data:{
                name:''
            }
        });

	# demo
		<div id="app">
            {{ value }}<br/>
            <el-input v-model="value"></el-input>
        </div>

		Vue.component('el-input',{
            //定义 value
            props:['value'],
            template:`
                        <input
                            :value="value"
                            @input="updateValue($event.target.value);"		
                            />
                    `,
            methods:{
                //值发生改变
                updateValue:function(newValue){
                    //改变值传递给父级组件
                    this.$emit('input', newValue);
                },
            }

        });

        var app = new Vue({
            el:'#app',
            data:{
                value:''
            }
        });
	

--------------------------------
自定义组件的 v-model		!!!	|
--------------------------------
	# 2.2.0 新增
	# 默认情况下,一个组件的 v-model 会使用 value prop 和 input 事件
	# 但是诸如单选框,复选框之类的输入类型可能把 value 用作了别的目的
	# model 选项可以避免这样的冲突
		
		Vue.component('my-checkbox', {
			model: {
				prop: 'checked',
				event: 'change'
			},
			props: {
				checked: Boolean,
				// 这样就允许拿 `value` 这个 prop 做其它事了
				value: String
			},
			// ...
		})
		<my-checkbox v-model="foo" value="some value"></my-checkbox>
		
		* 上述代码等价于
		<my-checkbox
			:checked="foo"
			@change="val => { foo = val }"
			value="some value">
		</my-checkbox>

		* 注意你仍然需要显式声明 checked 这个 prop

--------------------------------
非子父组件通信					|
--------------------------------
	# 有时候，非父子关系的两个组件之间也需要通信
	# 在简单的场景下,可以使用一个空的 Vue 实例作为事件总线
		var bus = new Vue()

		// 在一个组件中创建的钩子中监听事件
		bus.$on('id-selected', function (id) {

		})

		//在另一个组件中,触发监听事件
		bus.$emit('id-selected', 1)

		

		* '在复杂的情况下，我们应该考虑使用专门的状态管理模式'

--------------------------------
使用插槽分发内容				|
--------------------------------
	# 在使用组件时，我们常常要像这样组合它们
		<app>
			<app-header></app-header>
			<app-footer></app-footer>
		</app>

		* 注意两点

			1,<app> 组件不知道它会收到什么内容。这是由使用 <app> 的父组件决定的。
			2,<app> 组件很可能有它自己的模板。
			
			* 为了让组件可以组合,我们需要一种方式来混合父组件的内容与子组件自己的模板
			* 这个过程被称为内容分发 (即 Angular 用户熟知的"transclusion")
			* Vue.js 实现了一个内容分发 API,参照了当前 Web Components 规范草案,使用特殊的 <slot> 元素作为原始内容的插槽
	
	
	# 编译作用域
		* 假定模版
			<child-component>
				{{ message }}
			</child-component>
		* message 应该是绑定到父组件的数据
		* 组件作用域简单地说是
			父组件模板的内容在'父组件作用域内编译'
			子组件模板的内容在'子组件作用域内编译'
		* demo
			<!-- 无效 -->
			<child-component v-show="someChildProperty"></child-component>
				* 使用模版的时候,是在父级的作用域被编译,在父级作用域找不到 someChildProperty


			Vue.component('child-component', {
				// 有效，因为是在正确的作用域内
				template: '<div v-show="someChildProperty">Child</div>',
				data: function () {
					return {
						someChildProperty: true
					}
				}
			})
	
	# 单个插槽
		# 除非子组件模板包含至少一个 <slot> 插口,否则父组件的内容将会被丢弃
			* 子组件没有写 slot,那么父级组件在子组件标签里面写的东西全部被丢弃
		# 当子组件模板只有一个没有属性的插槽时,父组件传入的整个内容片段将插入到插槽所在的 DOM 位置,并替换掉插槽标签本身
			* 子组件只有一个没有属性的solt,父级组件在子组件标签写的内容就会替换它
		# 最初在 <slot> 标签中的任何内容都被视为备用内容,备用内容在子组件的作用域内编译,并且只有在宿主元素为空,且没有要插入的内容时才显示备用内容
			* 在子组件的slot中定义的内容,如果父级组件没有覆写,则会作为默认值被渲染
		
			* 定义子模版:my-component>
			<div>
				<h2>我是子组件的标题</h2>
				<slot>
					只有在没有要分发的内容时才会显示。
				</slot>
			</div>

			* 定义父模版
			* 在父级模版中使用子模版,并且覆写掉子模版中 slot 内容
			<div>
				<h1>我是父组件的标题</h1>
				<my-component>
					<p>这是一些初始内容</p>			//在这里覆写子组件的内容
					<p>这是更多的初始内容</p>
				</my-component>
			</div>

			* 渲染结果：
			<div>
				<h1>我是父组件的标题</h1>
				<div>
					<h2>我是子组件的标题</h2>
					<p>这是一些初始内容</p>
					<p>这是更多的初始内容</p>
				</div>
			</div>

	# 具名插槽
		# <slot> 元素可以用一个特殊的特性 name 来进一步配置如何分发内容
		# 多个插槽可以有不同的名字,具名插槽将匹配内容片段中有对应 slot 特性的元素
		# 仍然可以有一个匿名插槽,它是默认插槽,作为找不到匹配的内容片段的备用插槽
		# 如果没有默认插槽,找不到匹配的内容片段将被抛弃

		# 例如,假定我们有一个 app-layout 组件，它的模板为
			<div class="container">
				<header>
					<slot name="header"></slot>		//定义一个名为 header solt
				</header>
				<main>
					<slot></slot>					//定义默认的 solt
				</main>
				<footer>	
					<slot name="footer"></slot>		//定义一个名为 footer solt
				</footer>
			</div>
		# 父组件模板

			<app-layout>
				<h1 slot="header">这里可能是一个页面标题</h1>		//填充了 header solt
				<p>主要内容的一个段落。</p>							//没有指定填充的solt,则填充默认的
				<p>另一个主要段落。</p>								//没有指定填充的solt,则填充默认的
				<p slot="footer">这里有一些联系信息</p>				//填充了 footer solt
				<p>又一个主要段落</p>								//没有指定填充的solt,则填充默认的
			</app-layout>

		# 渲染结果为
			<div class="container">
				<header>
					<h1>这里可能是一个页面标题</h1>
				</header>
				<main>
					<p>主要内容的一个段落。</p>
					<p>另一个主要段落。</p>
					<p>又一个主要段落</p>
				</main>
				<footer>
					<p>这里有一些联系信息</p>
				</footer>
			</div>
		
		# 作用域插槽
			* 2.1.0 新增
			* 作用域插槽是一种特殊类型的插槽,用作一个 (能被传递数据的) 可重用模板,来代替已经渲染好的元素
			* 在子组件中,只需将数据传递到插槽,就像你将 prop 传递给组件一样
				<div class="child">
					<slot text="hello from child"></slot>
				</div>
			
			* 在父级中,具有特殊特性 slot-scope 的 <template> 元素必须存在,表示它是作用域插槽的模板
			* slot-scope 的值将被用作一个临时变量名,此变量接收从子组件传递过来的 prop 对象
				<div class="parent">
					<child>
						<template slot-scope="props">
							<span>hello from parent</span>
							<span>{{ props.text }}</span>
						</template>
					</child>
				</div>

			* 如果我们渲染上述模板,得到的输出会是
				<div class="parent">
					<div class="child">
						<span>hello from parent</span>
						<span>hello from child</span>
					</div>
				</div>

		# 在 2.5.0+，slot-scope 能被用在任意元素或组件中而不再局限于 <template>
		# 作用域插槽更典型的用例是在列表组件中，允许使用者自定义如何渲染列表的每一项
		<my-awesome-list :items="items">
		  <!-- 作用域插槽也可以是具名的 -->
		  <li
			slot="item"
			slot-scope="props"
			class="my-fancy-item">
			{{ props.text }}
		  </li>
		</my-awesome-list>
		列表组件的模板：
		<ul>
		  <slot name="item"
			v-for="item in items"
			:text="item.text">
			<!-- 这里写入备用内容 -->
		  </slot>
		</ul>
		解构

		slot-scope 的值实际上是一个可以出现在函数签名参数位置的合法的 JavaScript 表达式。这意味着在受支持的环境 (单文件组件或现代浏览器) 中，您还可以在表达式中使用 ES2015 解构：
		<child>
		  <span slot-scope="{ text }">{{ text }}</span>
		</child>