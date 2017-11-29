---------------------------
vue-实例简介				|
---------------------------
	var app = new Vue({
		el:"#app",
		data:{			//会被映射为vue实例的属性对象

		},
		methods:{

		},
		computed:{		//计算属性
			
		},
		filters:{		//过滤器
			
		},
		watch:{			//监视器

		},
	});
	
	# 创建一个vue实例对象,需要传递一个配置对象含数据,模板,挂载元素,方法,生命周期钩子等选项

	# 可以扩展 Vue 构造器,从而用预定义选项创建可复用的组件构造器
		var MyComponent = Vue.extend({
			// 扩展选项
		})
		// 所有的 `MyComponent` 实例都将以预定义的扩展选项被创建
		var myComponentInstance = new MyComponent()
	
---------------------------
vue-数据与方法				|
---------------------------
	# vue实例创建的时候,会把 data 下的所有数据都绑定在 vue 实例上
	# data 数据的更新,当这些属性的值发生改变时,视图将会产生"响应",即匹配更新为新的值
		// 我们的数据对象
		var data = { a: 1 }
		// 该对象被加入到一个 Vue 实例中
		var vm = new Vue({
			data: data
		})

		// 他们引用相同的对象！
		vm.a === data.a // => true

		// 设置属性也会影响到原始数据
		vm.a = 2
		data.a // ==> 2

		// ... 反之亦然
		data.a = 3
		vm.a // ==> 3
	# 只有当实例被创建时 data 中存在的属性是响应式的
	# 也就是说如果你添加一个新的属性
		vm.b = 'hi'
	# 那么对 b 的改动将不会触发任何视图的更新
	# 如果你知道你会在晚些时候需要一个属性,但是一开始它为空或不存在,那么你仅需要设置一些初始值
		data: {
			newTodoText: '',
			visitCount: 0,
			hideCompletedTodos: false,
			todos: [],
			error: null
		}

	# 除了 data 属性,Vue 实例暴露了一些有用的实例属性与方法,它们都有前缀 $ 以便与用户定义的属性区分
		var data = { a: 1 }
		var vm = new Vue({
			el: '#example',
			data: data
		})

		vm.$data === data // => true

		vm.$el === document.getElementById('example') // => true

		// $watch 是一个实例方法
		vm.$watch('a', function (newValue, oldValue) {
			// 这个回调将在 `vm.a` 改变后调用
		})

	# 注意,不要在实例属性或者回调函数中(如 vm.$watch('a', newVal => this.myMethod()))使用箭头函数
		* 因为箭头函数绑定父上下文,所以 this 不会像预想的一样是 Vue 实例,而是 this.myMethod 未被定义
		* 参考java里面 lambda 中的 this 执行会被改变
	

---------------------------
vue-生命周期				|
---------------------------
	#  每个 Vue 实例在被创建之前都要经过一系列的初始化过程例如
		实例需要配置数据观测(data observer)
		编译模版
		挂载实例到 DOM 
		然后在数据变化时更新 DOM 
	# 在这个过程中,实例也会调用一些 生命周期钩子 ,这就给我们提供了执行自定义逻辑的机会
	# 例如，created 这个钩子在实例被创建之后被调用
		var vm = new Vue({
			data: {
				a: 1
			},
			created: function () {
				// this 指向 vm 实例
				console.log('a is: ' + this.a)
			}
		})
	# 一些其它的钩子,在实例生命周期的不同阶段调用如
		mounted
		updated 
		destroyed 
	# 钩子的 this 指向调用它的 Vue 实例
	# 一些用户可能会问 Vue.js 是否有"控制器"的概念?答案是,没有.组件的自定义逻辑可以分布在这些钩子中

---------------------------
vue-过滤器					|
---------------------------

