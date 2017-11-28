----------------------------
计算属性					|
----------------------------
	# 在模板中绑定表达式是非常便利的,但是它们实际上只用于简单的操作
	# 在模板中放入太多的逻辑会让模板过重且难以维护
		<div id="example">
			{{ message.split('').reverse().join('') }}
		</div>
		* 整得太过于复杂
	
	# 任何复杂逻辑，你都应当使用计算属性
	# 通俗讲,所谓的计算属性,其实就是一个函数,该属性值就是通过该函数计算出来 return 的结果

----------------------------
计算属性-简单的属性			|
----------------------------
	<div id="app">
		<span>{{ reverseMessage }}</span>	<!--  ydnalBniveK -->
	</div>
	var app = new Vue({
		el:'#app',
		data:{
			message:'KevinBlandy'
		},
		computed:{
			//该属性是 message 属性的反转值
			// reverseMessage 就是一个计算属性
			reverseMessage:function(){
				return this.message.split('').reverse().join('');
			}
		}
	});

	console.log(app.reverseMessage);		//ydnalBniveK
	
	# '我们提供的函数将用作属性 app.reverseMessage 的 getter '

----------------------------
计算属性-计算属性缓存 & methods|
----------------------------
	# 还是反转的案例,我们也可以使用 methods 来完成

		{{ reverseMessage() }

		methods:{
			reverseMessage:function(){
				return this.message.split('').reverse().join('');
			}
		}
	# 计算属性是基于它的依赖缓存,计算属性只有在它的相关依赖发生改变时才会重新取值
	# 这就意味着只要 message 没有发生改变,'多次访问 reverseMessage '计算属性'会立即返回之前的计算结果',而'不必再次执行函数'
		computed: {
			now: function () {				//不是响应式依赖,多次使用 now 属性,最终返回的结果都是一样的
				return Date.now()
			}
		}
	# 计算属性只有在它的相关依赖发生改变时才会重新取值
	# '多次访问 reverseMessage()' 函数,不管message是否发生变化,'都会执行'一次函数
	# methods 每次调用都会进行运算后返回结果,computed会缓存结果,只有依赖改变,才会重新运算


----------------------------
计算属性-计算属性 & Watched Property|
----------------------------
	# Vue.js 提供了一个方法 $watch ,它用于观察 Vue 实例上的数据变动
	# 没看懂有啥意义这个......


----------------------------
计算属性-计算 setter		|
----------------------------
	# 计算属性默认只有 getter ,不过在需要时你也可以提供一个 setter 

	var app = new Vue({
		el:'#app',
		data:{
			name:'KevinBlandy',
		},
		computed:{
			reverseName:{
				//在使用 reverseName 的地方会执行该函数,
				get : function(){
					return this.name.split('').reverse().join('');
				},
				//在控制台执行  app.reverseName = '123456' 会执行该方法
				set : function(val){
					this.name = val;
					return val.split('').reverse().join('');
				},
			}
		}
	});

	* js的基础知识:属性寄存器 getter/setter


	
----------------------------
计算属性-观察 Watchers		|
----------------------------
	# 虽然计算属性在大多数情况下更合适,但有时也需要一个自定义的 watcher 
	# Vue 提供一个更通用的方法通过 watch 选项,来响应数据的变化
	# 当你想要在数据变化响应时,执行异步操作或昂贵操作时,这是很有用的
	# //TODO 2017年11月28日 22:02:14 睡觉,这个有点长,明天看