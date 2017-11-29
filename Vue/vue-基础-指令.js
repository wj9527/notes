------------------------
Vue-指令				|
------------------------
	v-if
	v-else
	v-show
	v-for	
	v-on
	v-bind
	v-model	
	v-once
	v-html 


------------------------
v-if					|
------------------------
	# 用于判断
	# 被该标签标识的节点,只有在该值为true的时候,才会显示
	# 总结
		1,if可以使用 methods 中的方法返回值,来作为判断依据
			<h3 v-if="test()"></h3>
		2,如果只是在if中声明了函数,并未执行,则认为是 true
			<h3 v-if="test"></h3>
		3,使用 data 中的数据来进行判断
			<h3 v-if="name == 'Kevin'">正确啦</h3>
		4,除了直接写字符串"true",以为,其他的字符串都认为是flase
			<h3 v-if="true"></h3>
		* 把字符串当作js来解析就对头了
	# 只有是正确的时候,这个节点才会被加载到DOM

------------------------
v-else					|
------------------------
	
------------------------
v-show					|
------------------------
	# 跟 if 差不多
	# 不同的是,不管正确是否,该节点都会加载,如果 if 表达式结果为:false,会给当前Dom设置:display:none


------------------------
v-for					|
------------------------
	# 用于迭代操作
	# 循环体的特殊属性
		1,角标
			<tr v-for="(item,index) in users">
				<td>{{index}}{{item.name}}</td>
			</tr>
			* item后的第一个参数,就是下标,是从0开始
			* 如果需要从1开始,可以在表达式中进行加1操作{{index+1}}
	# 总结
		1,可以直接迭代 vue 实例的 data 属性中的属性
			<tr v-for="item in users">
				<td>{{item.name}}</td>
			</tr>
			* 如果目标是字符串,则会把字符串的每个属性都当作元素来迭代
			* 如果目标是JSON对象,则会迭代该对象中的value值
			* 如果目标是JSON对象数组,则会把每个JSON对象序列化为字符串进行迭代
			* 如果目标不是可迭代对象,则不会有迭代效果

		2,也可以迭代 methods 方法的返回值
			<tr v-for="item in getUsers()">
				<td>{{item.name}}</td>
			</tr>

------------------------
v-on					|
------------------------
	# 绑定事件监听
	# 语法
		v-on:[事件]
	# demo
		 <div id="app">
            <p>{{message}}</p>
            <button v-on:click="reverseMessage">戳我</button>
        </div>

		var app = new Vue({
            el:'#app',
            data:{
                message:'KevinBlandy'
            },
            methods:{
                reverseMessage:function(){		//点击的时候,反转字符串
                    this.message = this.message.split('').reverse().join('');
                }
            }
        });


------------------------
v-bind					|
------------------------
	# 绑定 DOM 元素属性
	# 语法
		v-bind:[元素名称]
	# demo
		<div id="app">
            <span v-bind:class="cls">
                一些文字
            </span>
        </div>
		var app = new Vue({
            el:'#app',
            data:{
                cls:'bg_blue',
            }
        });

------------------------
v-model					|
------------------------
	# 现表单输入和应用状态之间的双向绑定
	# 语法
		v-model=""
		* 会把指定的变量和当前input框的值绑定
	# demo
		 <div id="app">
            <p>{{message}}</p>
            <input v-model="message" placeholder="请输入内容"/>
        </div>

		 var app = new Vue({
            el:'#app',
            data:{
                message:''
            }
        });
