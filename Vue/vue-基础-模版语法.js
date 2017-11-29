----------------------------
模版语法					|
----------------------------
	# Vue.js 使用了基于 HTML 的模版语法,允许开发者声明式地将 DOM 绑定至底层 Vue 实例的数据
	# 所有 Vue.js 的模板都是合法的 HTML ,所以能被遵循规范的浏览器和 HTML 解析器解析
	# 在底层的实现上, Vue 将模板编译成虚拟 DOM 渲染函数,结合响应系统,在应用状态改变时, Vue 能够智能地计算出重新渲染组件的最小代价并应用到 DOM 操作上

	# 如果你熟悉虚拟 DOM 并且偏爱 JavaScript 的原始力量,你也可以不用模板，直接写渲染(render)函数,使用可选的 JSX 语法	


----------------------------
模版语法-插值				|
----------------------------
	# 文本
		* 数据绑定的常用语法:双大括号 {{  attr }}
			<span>Hello,{{ name }}</span>
		
		* 无论何时,绑定的数据对象上 {{ attr }} attr,属性发生了改变,插值处的内容都会更新
		* 通过使用 v-once 指令,你也能执行一次性地插值,当数据改变时,插值处的内容不会更新(但这会影响到该节点上所有的数据绑定)
			<span v-once>This will never change: {{ name }}</span>
	
	# 纯HTML
		* 双大括号会将数据解释为纯文本,而非 HTML 
		* 为了输出真正的 HTML ,你需要使用 v-html 指令来指定哪个属性是HTML
			<div v-html="rawHtml"></div>
		* 被插入的内容都会被当做 HTML (数据绑定会被忽略)
		* 注意,不能使用 v-html 来复合局部模板,因为 Vue 不是基于字符串的模板引擎
		* 组件更适合担任 UI 重用与复合的基本单元
		* 该指令使用不当会造成XSS攻击
	
	# 属性
		* 双括号赋值不能用在属性控制中
			<div class="{{ divclass }}">	//×

		* 使用 v-bind指令来动态的控制属性值

			v-bind:属性名称="变量"

			<div v-bind:class="name"></div>
			<div v-bind:id="name"></div>

		* 这对布尔值的属性也有效 (部分HTML标签属性是bool值)
			如果条件被求值为 false 的话该属性会被移除
			<button v-bind:disabled="someDynamicCondition">Button</button>

	
	# 使用javascript表达式
		* 迄今为止,在我们的模板中,我们一直都只绑定简单的属性键值
		* 但实际上,对于所有的数据绑定,Vue.js 都提供了完全的 JavaScript 表达式支持
			{{ number + 1 }}		//执行基本的运算

			{{ ok ? 'YES' : 'NO' }}	//三元运算

			{{ message.split('').reverse().join('') }}		//对象对象调用

			<div v-bind:id="'list-' + id"></div>			//在指令中进行基本的运算
	
		* 这些表达式会在所属 Vue 实例的数据作用域('注意,这些表达式的作用域是当前vue实例对象')下作为 JavaScript 被解析
		* 有个限制就是，每个绑定都只能包含单个表达式，所以下面的例子都不会生效。
		
			{{ var a = 1 }}		//这是语句,不是表达式

			{{ if (ok) { return message } }}		//逻辑流控制也不会生效,请使用三元表达式

		* 模板表达式都被放在沙盒中,只能访问全局变量的一个白名单,如 Math 和 Date (可以访问的全局变量有限)
		* 不应该在模板表达式中试图访问用户定义的全局变量

	# 过滤器
		* Vue.js 允许你自定义过滤器,被用作一些常见的文本格式化(经常用于文本格式化)
		* 过滤器应该被添加在 {{}} 插值的尾部，由"管道符"指示:
			{{ name | upper }}
			new Vue({
				el:'#app',
				data:{
					name:'KevinBlandy'
				},
				//定义一些列的过滤器
				filters:{
					upper:function(val){
						return val.toUpperCase();
					}
				},
			});
		* 过滤器函数总接受表达式的值作为第一个参数
		* Vue 2.x 中,过滤器只能在 {{}} 绑定中使用
		* 为了在指令绑定中实现同样的行为,你应该使用'计算属性'(该属性其实是一个函数,最终的结果值是由该函数计算后返回)
		* 过滤器可以串联(后一个的参数就是前一个过滤器的返回值)
			{{ message | filterA | filterB }}
		* 过滤器是 JavaScript 函数,因此可以接受参数(参数可以是常量,或者vue实例变量)
			{{ message | filterA('arg1', arg2) }}	//arg1会被传递给filterA的第二个形参
		
	# 指令
		* 指令(Directives)是带有 v- 前缀的特殊属性
		* 指令属性的值预期是单一 JavaScript 表达式(除了 v-for,之后再讨论)
		* 指令的职责就是当其表达式的值改变时相应地将某些行为应用到 DOM 
			<p v-if="seen">Now you see me</p>
			v-if 指令将根据表达式 seen 的值的真假来移除/插入 <p> 元素
	
	# 参数
		* 一些指令能接受一个"参数",在指令后以冒号指明
			v-bind 指令被用来响应地更新 HTML 属性,参数就是标签的属性
				<a v-bind:href="url"></a>
				在这里 href 是参数,告知 v-bind 指令将该元素的 href 属性与表达式 url 的值绑定(vue实例的url属性改变,当前a标签的url属性也会改变)

			v-on 指令,它用于监听 DOM 事件,参数就是监听的事件名
				<a v-on:click="doSomething">
				在这里参数是监听的事件名,后面会有更为详细的事件笔记
	
	# 修饰符
		* 修饰符(Modifiers)是以半角句号 . 指明的特殊后缀,用于指出一个指定应该以特殊方式绑定
			例如 .prevent 修饰符告诉 v-on 指令对于触发的事件调用: event.preventDefault()：
			<form v-on:submit.prevent="onSubmit"></form>

		* 之后当我们更深入地了解 v-on 与 v-model时,会看到更多修饰符的使用
		* 没看懂 TODO

	# 缩写
		* v- 前缀在模板中是作为一个标示 Vue 特殊属性的明显标识
		* Vue.js 为两个最为常用的指令提供了特别的缩写
			v-bind 缩写(直接省略掉 v-bind)
				<!-- 完整语法 -->
				<a v-bind:href="url"></a>

				<!-- 缩写 -->
				<a :href="url"></a>

			v-on 缩写(把 v-on 替换为 @click)
				<!-- 完整语法 -->
				<a v-on:click="doSomething"></a>

				<!-- 缩写 -->
				<a @click="doSomething"></a>
				* 它们看起来可能与普通的 HTML 略有不同,但 : 与 @ 对于属性名来说都是合法字符
				* 在所有支持 Vue.js 的浏览器都能被正确地解析
				* 而且,它们不会出现在最终渲染的标记,缩写语法是完全可选的,但随着你更深入地了解它们的作用,你会庆幸拥有它们



