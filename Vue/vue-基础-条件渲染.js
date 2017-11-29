----------------------------
条件渲染					|
----------------------------
	# 在vue里面使用 v-if 指令实现判断
		//如果 ok 为true,则该标签会被加载到dom
		<h1 v-if="ok">Yes</h1>

		data:{
			ok:true
		}
	
	# 也可以添加 else/v-else-if

		<h1 v-if="ok">Yes</h1>
		<h1 v-else>No</h1>

		<h1 v-if="name == 'KevinBlandy'">KenBlandy</h1>
		<h1 v-else-if="name == 'Litch'">Litch</h1>
		<h1 v-else>Coco</h1>
			
		* v-else 元素必须紧跟在带 v-if 或者 v-else-if 的元素的后面
		* 否则它将不会被识别
	
	# if 条件渲染分组
		* if 指令只能用于一个标签上,如果是想控制一堆标签就需要 <template>

		<template v-if="ok">
			<h1>Title</h1>
			<p>Paragraph 1</p>
			<p>Paragraph 2</p>
		</template>

		* 最终渲染的结果里面没有 template
	
----------------------------
用 key 管理可复用的元素		|
----------------------------
	# Vue会尽可能的高效的渲染元素,通常会复用已有的元素,而不是从头开始渲染
		<div id="app">
            <template v-if="loginType === 'username'">
                <label>Username</label>
                <input placeholder="Enter your username">			//input
            </template>
            <template v-else>
                <label>Email</label>
                <input placeholder="Enter your email address">		//复用上面的input
            </template>
            <button @click="change">切换</button>
        </div>

		 var app = new Vue({
            el:'#app',
            data:{
                loginType:'username'
            },
            methods:{
                change:function(){
                    if(this.loginType == 'username'){
                        this.loginType ='email'
                    } else{
                        this.loginType = 'username'
                    }
                }
            }
        });

		* 点击摁钮切换 loginType 将不会清除用户已经输入的内容,'因为两个模板使用了相同的元素',<input> 不会被替换掉——仅仅是替换了它的 placeholder
		* Vue 为你提供了一种方式来表达 "这两个元素是完全独立的,不要复用它们",只需添加一个具有唯一值的 key 属性即可	
			<input placeholder="Enter your email address" key="email-input">
			<input placeholder="Enter your username" key="username-input">

			* 注意,<label> 元素仍然会被高效地复用,因为它们没有添加 key 属性

----------------------------
v-show						|
----------------------------
	# 另一个用于根据条件展示元素的选项是 v-show 指令
		<h1 v-show="ok">Hello!</h1>

		* 不同的是带有 v-show 的元素始终会被渲染并保留在 DOM 中
		* v-show 只是简单地切换元素的 CSS 属性 display
		* v-show 不支持 <template> 元素,也不支持 v-else
	
	# v-if vs v-show
		* v-if 是"真正"的条件渲染,因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建
		* v-if 也是惰性的:如果在初始渲染时条件为假,则什么也不做——直到条件第一次变为真时,才会开始渲染条件块
		* v-show 不管初始条件是什么,元素总是会被渲染,并且只是简单地基于 CSS 进行切换
		* v-if 有更高的切换开销,而 v-show 有更高的初始渲染开销
		* 如果需要非常'频繁地切换',则使用 'v-show' 较好,如果在运行时'条件很少改变',则使用 'v-if' 较好
	

	# v-if 与 v-for 一起使用
		* 当 v-if 与 v-for 一起使用时 v-for 具有比 v-if 更高的优先级

	