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
				
	# 在 v-for 块中，我们拥有对父作用域属性的完全访问权限
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
	