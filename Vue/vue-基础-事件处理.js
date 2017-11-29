----------------------------
简单的监听代码执行			|
----------------------------
	# 用 v-on 指令监听 DOM 事件,并在触发时'运行一些 JavaScript 代码'
		<span v-on:click="num += 1">{{ num }}</span>
		var app = new Vue({
			el:'#app',
			data:{
				num:0
			},
		});
	
----------------------------
事件处理方法				|
----------------------------
	# 许多时候,处理方法更为复杂,v-on 还可以接收一个需要调用方法的名称
		<span v-on:click="add">{{ num }}</span>
		var app = new Vue({
			el:'#app',
			data:{
				num:0
			},
			methods:{
				add:function(event){
					console.log(event);
					this.num += 1;
				}
			}
		});
	
	# 内联处理器中的方法
		* 除了直接绑定到一个方法,也可以在内联 JavaScript 语句中调用方法
			<span v-on:click="add(5)">{{ num }}</span>

			var app = new Vue({
				el:'#app',
				data:{
					num:0
				},
				methods:{
					add:function(newNum){
						this.num += newNum;
					}
				}
			});
		* 由自己完成调用,而且可以传递参数

	# 有时也需要在内联语句处理器中访问原始的 DOM 事件
	# 可以用特殊变量 $event 把它传入方法
		<span v-on:click="add(5,$event)">{{ num }}</span>

			var app = new Vue({
				el:'#app',
				data:{
					num:0
				},
				methods:{
					add:function(newNum,event){
						console.log(event);
						this.num += newNum;
					}
				}
			});

----------------------------
事件修饰符					|
----------------------------
	# 在事件处理程序中调用 event.preventDefault() 或 event.stopPropagation() 是非常常见的需求
	# 尽管我们可以在方法中轻松实现这点,但更好的方式是
		* '方法只有纯粹的数据逻辑,而不是去处理 DOM 事件细节'
		
		* preventDefault()	
			* 通知浏览器不要执行与事件关联的默认动作。
			* 例如:form里面的submit input,点击就会执行提交

		* stopPropagation()	
			* 阻止事件流的产生
			* 仅仅执行当前的事件,不执行父级元素的事件
			* 冒泡,捕捉形的事件流都可以阻止
			* 一般只考虑冒泡(先执行小的,再执行大的)
		
	# 为了解决这个问题,Vue.js 为 v-on 提供了事件修饰符
	# 之前提过'修饰符是由点开头的指令后缀来表示的'
		.stop
		.prevent
		.capture
		.self
		.once
			* 2.1.4新增,表示该事件仅仅只执行一次
	
	# demo
		<!-- 阻止单击事件继续传播 -->
		<a v-on:click.stop="doThis"></a>

		<!-- 提交事件不再重载页面 -->
		<form v-on:submit.prevent="onSubmit"></form>

		<!-- 修饰符可以串联 -->
		<a v-on:click.stop.prevent="doThat"></a>

		<!-- 只有修饰符 -->
		<form v-on:submit.prevent></form>

		<!-- 添加事件监听器时使用事件捕获模式 -->

		<!-- 即内部元素触发的事件先在此处处理，然后才交由内部元素自身进行处理 -->
		<div v-on:click.capture="doThis">...</div>
		<!-- 只当在 event.target 是当前元素自身时触发处理函数 -->
		<!-- 即事件不是从内部元素触发的 -->
		<div v-on:click.self="doThat">...</div>
	
	# 使用修饰符时,顺序很重要,相应的代码会以同样的顺序产生
		@click.prevent.self 会阻止所有的点击
		@click.self.prevent 只会阻止对元素自身的点击
	

----------------------------
按键修饰符					|
----------------------------
	# 在监听键盘事件时,我们经常需要检查常见的键值
	# Vue 允许为 v-on 在监听键盘事件时添加按键修饰符
		<!-- 只有在 `keyCode` 是 13 时调用 `vm.submit()` -->
		<input v-on:keyup.13="submit">

	# 记住所有的 keyCode 比较困难,所以 Vue 为最常用的按键提供了别名

		<input v-on:keyup.enter="submit">
		<input @keyup.enter="submit">		//缩写语法 

		* 全部的按键别名：
			.enter
			.tab
			.delete (捕获"删除"和"退格"键)
			.esc
			.space
			.up
			.down
			.left
			.right

	# 可以通过全局 config.keyCodes 对象自定义按键修饰符别名

		// 可以使用 'v-on:keyup.f1'
		Vue.config.keyCodes.f1 = 112

----------------------------
自动匹配按键修饰符			|
----------------------------
	# 2.5.0 新增
	# 可直接将 KeyboardEvent.key 暴露的任意有效按键名转换为 kebab-case(中划线风格) 来作为修饰符

		<input @keyup.page-down="onPageDown">

		* 处理函数仅在 $event.key === 'PageDown' 时被调用
		* keyup.[按键名称]

	# 有一些按键 (.esc 以及所有的方向键) 在 IE9 中有不同的 key 值, 如果你想支持 IE9,它们的内置别名应该是首选


----------------------------
系统修饰键					|
----------------------------
	# 2.1.0 新增
	# 可以用如下修饰符来实现仅在按下相应按键时才触发鼠标或键盘事件的监听器。
		.ctrl
		.alt
		.shift
		.meta

	# 在 Mac 系统键盘上,meta 对应 command 键 (⌘)
	# 在 Windows 系统键盘 meta 对应 Windows 徽标键 (⊞)
	# 在 Sun 操作系统键盘上，meta 对应实心宝石键 (◆)
	# 在其他特定键盘上,其在 MIT 和 Lisp 机器的键盘,以及其后继产品,比如 Knight 键盘,space-cadet 键盘,meta 被标记为"META"
	# 在 Symbolics 键盘上,meta 被标记为"META"或者"Meta"。
	# 例如

		<!-- Alt + C -->
		<input @keyup.alt.67="clear">

		<!-- Ctrl + Click -->
		<div @click.ctrl="doSomething">Do something</div>

	# 请注意'修饰键'与常规按键不同,在'和 keyup 事件一起用'时,'事件触发时修饰键必须处于按下状态'
		* 换句话说,只有在按住 ctrl 的情况下释放其它按键,才能触发keyup.ctrl,而单单释放 ctrl 也不会触发事件


----------------------------
.exact 修饰符				|
----------------------------
	# 2.5.0 新增
	# .exact 修饰符应与其他系统修饰符组合使用,以指示处理程序只在精确匹配该按键组合时触发
		<!-- 即使 Alt 或 Shift 被一同按下时也会触发 -->
		<button @click.ctrl="onClick">A</button>

		* 只是监听了 ctrl,但是同时摁下了其他键也会触发
		* 我先摁了其他键,再摁钮ctrl也会被触发

		<!-- 仅在只有 Ctrl 被按下的时候触发 -->
		<button @click.ctrl.exact="onCtrlClick">A</button>

		* 仅仅只有在 ctrl 被单独摁下的时候触发

------------------------
鼠标按钮修饰符			|
------------------------
	# 2.1.0 新增
		.left
		.right
		.middle

		* 这些修饰符会限制处理函数仅响应特定的鼠标按钮
	
	
