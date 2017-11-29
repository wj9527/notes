-----------------------------
class与style绑定			 |
-----------------------------
	* 如果值是个json对象
		* key就是class属性值,是否渲染取决于value的bool值
	* 如果值是个预定义对象
		* 该预定义对象应该是 data 里面的对象
		* key就是class属性值,是否渲染取决于value的bool值
	* 如果值是个数组
		* 则多个class属性值,就是数组中的变量值



-----------------------------
绑定HTML Class-对象语法		 |
-----------------------------
	# v-bind:class 可以传值一个对象,以此动态的切换class

		<div v-bind:class="{active:isActive}"></div>

		data:{
			isActive:true
		}

		* class 的 active 的更新将取决于数据属性 isActive 是否为 true
		* 如果 data 里面 isActive == true,则渲染 <div class="active"></div>
		* 可以一次性传递多个值
			<div v-bind:class="{active:isActive,error:isError}"></div>
	
		* 也可以与原始的class属性并存
			<div class="demo" v-bind:class="{active:isActive}"></div>

			<div class="demo active"></div>
		
		* 也可以直接把 data 里面的对象绑定给 v-bind:
			<div v-bind:class="clasObj"></div>

			data:{
				classObj:{
					active:true,
					danger:false
				}
			}

			* 对象的 key 就是class属性值,value是bool值,true/false觉得是否渲染
		
		* 也可以使用强大的计算属性
			<div v-bind:class="clasObj"></div>

			computed:{
				clasObj:function(){
					return {
						active : false,
						error:false,
					};
				}
			}

			* 计算属性返回一个obj,该obj的key作为class属性值,value决定是否要渲染属性

		
	
-----------------------------
绑定HTML Class-数组语法		 |
-----------------------------
	# 可以把一个数组传递给 v-bind:class ,以应用一个 class 列表

		<div v-bind:class="[activeClass, errorClass]"></div>

		data:{
			activeClass: 'active',
			errorClass: 'text-danger'
		}

		* 渲染结果
			<div class="active text-danger"></div>
		* 把data里面的value作为class的属性值
	
	# 使用三元表达式

		<div v-bind:class="[isActive ? activeClass : '', errorClass]">

		* 在 data:{isActive:true}的时候,才会渲染 activeClass 值到属性 class
	
	# 多个带条件的class,可以在数组语法中使用对象语法
		<div v-bind:class="[{ active: isActive }, errorClass]">

		* 在 data:{isActive:true}的时候,才会渲染 active 值到属性 class

-----------------------------
在组件上使用				 |
-----------------------------
	# 组件的class属性渲染
		Vue.component('my-component', {
			//组件定义声明了俩属性
			template: '<p class="foo bar">Hi</p>'
		})

		//使用组件的时候,添加了俩属性
		<my-component class="baz boo"></my-component>

		* 最终渲染结果
			<p class="foo bar baz boo">Hi</p>
	
	# 组件也适用于绑定HTML class
		<my-component v-bind:class="{ active: isActive }"></my-component>

		data:{
			isActive:true
		}
		
		* isActive == true 的时候,渲染 active 属性值到class

-----------------------------
绑定内联样式				 |
-----------------------------

		

		
