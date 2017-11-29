----------------------------
列表渲染					|
----------------------------
	# 使用 v-for 进行列表渲染
		<div id="app">
            <ul>
                <li v-for="skill in skills">{{ skill.name }} - {{ skill.level }}</li>
            </ul>
        </div>

		 var app = new Vue({
            el:'#app',
            data:{
                skills:[{
                    name:'java',
                    level:1
                },{
                    name:'python',
                    level:2
                }]
            },
		 });
				
	# 在 v-for 块中,我们拥有对父作用域属性的完全访问权限
		<li v-for="skill, in skills">
			{{ skill.name }} - {{ parentMessage }}		//parentMessage是vue实例中data的属性,可以在循环体中进行访问
		</li>
		
	# v-for 还支持一个可选的第二个参数为当前项的索引
		<li v-for="(skill,i) in skills">
			{{ skill.name }} - {{ i }}		
		</li>
		* 下标是从0开始,可以自己进行 +1 操作  {{ i+1 }}

	# 也可以用 of 替代 in 作为分隔符,因为它是最接近 JavaScript 迭代器的语法
		<div v-for="item of items"></div>
	
	# 一个对象的 v-for
		<li v-for="item in kevin">{{ item }}</li>

		var app = new Vue({
			el:'#app',
			data:{
				kevin:{
					name:'KevinBlandy',
					age:23
				}
			},
		});

		* 会把对象里面的所有值,迭代出来
			KevinBlandy
			23
		
		* 也可以提供'第二个的参数'为键名
			 <li v-for="item,key in kevin">{{  key }} - {{ item }}</li>
				 name-KevinBlandy
				 age-23
			
		* 也可以'提供第三个参数',作为下标
			<li v-for="item,key,index in kevin">{{  key }} - {{ item }}</li>
		
		* 在遍历对象时,是按 Object.keys() 的结果遍历,但是不能保证它的结果在不同的 JavaScript 引擎下是一致的

----------------------------
列表渲染-key				|
----------------------------
	# 当 Vue.js 用 v-for 正在'更新已渲染过的元素列表'时,它默认用'就地复用'策略
	# 如果数据项的顺序被改变,Vue 将不会移动 DOM 元素来匹配数据项的顺序, 而是简单复用此处每个元素,并且确保它在特定索引下显示已被渲染过的每个元素
		* 这个类似 Vue 1.x 的 track-by="$index" 
	# 这个默认的模式是高效的,但是只适用于不依赖子组件状态或临时 DOM 状态 (例如:表单输入值) 的列表渲染输出
	# 为了给 Vue 一个提示,以便它能跟踪每个节点的身份,从而重用和重新排序现有元素,你需要为每项提供一个唯一 key 属性
	# 理想的 key 值是每项都有的且唯一的 id
		* 这个特殊的属性相当于 Vue 1.x 的 track-by ,但它的工作方式类似于一个属性,所以你需要用 v-bind 来绑定动态值
		<div v-for="item in items" v-bind:key="item.id">
			<!-- 内容 -->
		</div>
	# 建议尽可能在使用 v-for 时提供 key,除非遍历输出的 DOM 内容非常简单,或者是刻意依赖默认行为以获取性能上的提升
	# 因为它是 Vue 识别节点的一个通用机制,key 并不与 v-for 特别关联,key 还具有其他用途,我们将在后面的指南中看到其他用途
	# 没大懂

----------------------------
列表渲染-数组更新检测		|
----------------------------
	# Vue 包含一组'监视数组的变化方法',所以它们也将会触发视图更新

		push()
		pop()
		shift()
		unshift()
		splice()
		sort()
		reverse()
	
		* 如果[]执行了以上的方法更新了数据,那么对应的视图也会更新
		* 官方称之为'变异方法'


	# 替换数组
		* 变异方法会改变原来的数组,但是部分方法是直接返回一个新的[]
			filter()
			concat()
			slice()

		* 当使用'非变异方法'时,可以用新数组替换旧数组
			app.items = app.items.filter(function(item){return item == '西瓜' || item == '南瓜'})
	
		
		* 你可能认为这将导致 Vue 丢弃现有 DOM 并重新渲染整个列表,幸运的是,事实并非如此
		* Vue 为了使得 DOM 元素得到最大范围的重用而实现了一些智能的,启发式的方法
		* 所以用一个含有相同元素的数组去替换原来的数组是非常高效的操作
	
	# 注意,由于 JavaScript 的限制,Vue 不能检测以下变动的数组
		1,利用索引直接设置一个项时
			vm.items[indexOfItem] = newValue
		2,修改数组的长度时
			vm.items.length = newLength
		
		* 为了解决第一类问题,以下两种方式都可以实现和 vm.items[indexOfItem] = newValue 相同的效果,同时也将触发状态更新

			// Vue.set
			Vue.set(app.items, indexOfItem, newValue)
			* Vue的静态方法.set(),第一个参数就是实例要修改的数组,第二个参数就是下标,第三个参数就是要更换的值

			// Array.prototype.splice
			app.items.splice(indexOfItem, 1, newValue)
			* 利用Array的原型方法splice()


		* 为了解决第二类问题，你可以使用 splice
			app.items.splice(newLength)
		
		* splice() ,在当前数组中添加或删除元素
			* 参数
				index	
					> 必需。规定从何处添加/删除元素。该参数是开始插入和(或)删除的数组元素的下标，必须是数字。
				howmany	
					> 必需。规定应该删除多少元素。必须是数字，但可以是 "0"。如果未规定此参数，则删除从 index 开始到原数组结尾的所有元素。
				item1, ..., itemX	
					> 可选。要添加到数组的新元素
			* index 决定了从哪里开始删除元素,howmany 决定了要删除多少个元素,如果有一个或者多个 item ,就会被添加到删除的空缺处,没有就无视
			* 如果 item 个数超出删除的长度,则,原来数组的其他元素后移
			* Demo
				var fruits = ["Banana", "Orange", "Apple", "Mango"];
				fruits.splice(2,1,"Lemon","Kiwi");	//Banana,Orange,Lemon,Kiwi,Mango

----------------------------
列表渲染-对象更新检测		|
----------------------------
	# 还是由于 JavaScript 的限制,Vue 不能检测对象属性的添加或删除
		var vm = new Vue({
			data: {
				a: 1
			}
		})
		// `vm.a` 现在是响应式的
		vm.b = 2
		// `vm.b` 不是响应式的

	# 对于已经创建的实例,Vue 不能动态添加根级别的响应式属性
	# 可以使用 Vue.set(object, key, value) 方法向嵌套对象添加响应式属性
		var vm = new Vue({
			data: {
				userProfile: {
					name: 'Anika'
				}
			}
		})

		Vue.set(vm.userProfile, 'age', 27)
		* 以添加一个新的 age 属性到嵌套的 userProfile 对象

		this.$set(this.userProfile, 'age', 27)
		* 使用 vm.$set 实例方法,它只是全局 Vue.set 的别名

	# 有时你可能需要为已有对象'赋予多个新属性',比如使用 Object.assign() 或 _.extend()
	# 在这种情况下,你应该用两个对象的属性创建一个新的对象
	# 所以,如果你想添加新的响应式属性,不要像这样:
		Object.assign(this.userProfile, {
			age: 27,
			favoriteColor: 'Vue Green'
		});

	# 你应该这样做
		this.userProfile = Object.assign({}, this.userProfile, {
			age: 27,
			favoriteColor: 'Vue Green'
		});

	# Object.assign()
		* 第一个参数就是目标对象,也是最终返回的对象
		* 剩下的参数,都是被复制属性的对象,会把它们的属性复制到第一个参数后返回
		* '更多细节,用到再去查吧'
	
----------------------------
列表渲染-显示过滤/排序结果	|
----------------------------
	# 有时,想要显示一个数组的过滤或排序副本,而不实际改变或重置原始数据
	# 在这种情况下,可以创建返回过滤或排序数组的计算属性
		<li v-for="n in evenNumbers">{{ n }}</li>

		data: {
			numbers: [ 1, 2, 3, 4, 5 ]
		},

		computed: {
			//计算属性,迭代的是它的返回值
			evenNumbers: function () {
				return this.numbers.filter(function (number) {
					return number % 2 === 0
				})
			}
		}

	# 在计算属性不适用情况下 (例如,在嵌套 v-for 循环中) 可以使用一个 method 方法
			//先执行函数进行排序后迭代其返回值
			<li v-for="n in even(numbers)">{{ n }}</li>

			data: {
				numbers: [ 1, 2, 3, 4, 5 ]
			},

			methods: {
				even: function (numbers) {
					return numbers.filter(function (number) {
						return number % 2 === 0
					})
				}
			}

----------------------------
一段取值范围的 v-for		|
----------------------------
	# v-for 也可以取整数,在这种情况下,它将重复多次模板
		<div>
			<span v-for="n in 10"> {{ n }} </span>
		</div>
		* 结果很明白

----------------------------
v-for on a <template>		|
----------------------------
	# 类似于 v-if,你也可以利用带有 v-for 的 <template> 渲染多个元素
		<ul>
			<template v-for="item in items">
				<li>{{ item.msg }}</li>
				<li class="divider"></li>
			</template>
		</ul>

----------------------------
v-for with v-if				|
----------------------------
	# 当它们处于同一节点,v-for 的优先级比 v-if 更高
	# 这意味着 'v-if 将分别重复运行于每个 v-for 循环中'
	# 当你想为仅有的一些项渲染节点时,这种优先级的机制会十分有用如下

		<li v-for="todo in todos" v-if="!todo.isComplete">
			{{ todo }}
		</li>

		* 上面的代码只传递了未 complete 的 todos
		* 'if和for同时出现,那么if的存在就是为了判断循环项的每一个是否渲染'
	
	# 而如果目的是有条件地跳过循环的执行,那么可以将 v-if 置于外层元素 (或 <template>)上
		<ul v-if="todos.length">
			<li v-for="todo in todos">
				{{ todo }}
			</li>
		</ul>
		<p v-else>No todos left!</p>
	
----------------------------
一个组件的 v-for			|
----------------------------
	//TODO