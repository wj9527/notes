----------------------------
表单输入绑定				|
----------------------------
	# v-model 指令在表单控件元素上创建双向数据绑定
	# 它会根据控件类型自动选取正确的方法来更新元素,尽管有些神奇,但 v-model 本质上不过是语法糖
	# 它负责监听用户的输入事件以更新数据,并特别处理一些极端的例子
	
	# v-model 会忽略所有表单元素的 value,checked,selected 特性的初始值
	# 它会选择 Vue 实例数据来作为具体的值,你应该通过 JavaScript 在组件的 data 选项中声明初始值
		<input type="text" v-model="num" value="哈哈哈"/>
		* input框实际的值会是 num 值,而不是 哈哈哈

	# 对于要求 IME (如中文,日语,韩语等) (IME 意为"输入法")的语言,你会发现 v-model 不会在 ime 输入中得到更新,如果你也想实现更新,请使用 input 事件

----------------------------
选框						|
----------------------------
	# 单个勾选框,逻辑值
		{{  checked }}		false/true
        <input type="checkbox" v-model="checked"/>
		* 单个框,逻辑值,要么选中,要么没选中
	
	# 多个勾选框
		<div id="app">
            {{  skills }} <br/>
            Java	<input type="checkbox" v-model="skills" value="Java" name="skill"/>
            Python	<input type="checkbox" v-model="skills" value="Python" name="skill"/>
            Go		<input type="checkbox" v-model="skills" value="Go" name="skill"/>
        </div>

		var app = new Vue({
			el:'#app',
			data:{
				skills:[],
			},
		});
		* 多个勾选框，绑定到同一个数组
		* 数组里面有的值,就是选中的
	
	# 单选摁钮
		<div id="app">
            {{  genders }} <br/>
            男<input type="radio" v-model="genders" value="boy" name="gender"/>
            女<input type="radio" v-model="genders" value="girl" name="gender"/>
        </div>

        var app = new Vue({
            el:'#app',
            data:{
                genders:'',
            },
        });
		* 单个属性控制值

----------------------------
选择列表					|
----------------------------
	# 单选项绑定
		<div id="app">
            {{  skill }} <br/>
            <select v-model="skill">
                <option value="java">java</option>
                <option value="python">python</option>
                <option value="go">go</option>
            </select>
        </div>

        var app = new Vue({
            el:'#app',
            data:{
                skill:'java',
            },
        });
		
		* 绑定到单个值
		* 如果 v-model 表达初始的值不匹配任何的选项,<select> 元素就会以"未选中"的状态渲染
		* 在 iOS 中,这会使用户无法选择第一个选项,因为这样的情况下,iOS 不会引发 change 事件
		* 因此,像以上提供 disabled 选项是建议的做法
	
	# 多选项绑定
		<div id="app">
            {{  skills }} <br/>
            <select v-model="skills" multiple="multiple">
                <option value="java">java</option>
                <option value="python">python</option>
                <option value="go">go</option>
            </select>
        </div>

        var app = new Vue({
            el:'#app',
            data:{
                skills:['java'],
            },
        });

		* 绑定到数组
	
----------------------------
值绑定						|
----------------------------
	# '没大搞懂这个存在的意义... ...'
	# 对于单选按钮,勾选框及选择列表选项,v-model 绑定的 value 通常是静态字符串 (对于勾选框是逻辑值)
		<!-- 当选中时，`picked` 为字符串 "a" -->
		<input type="radio" v-model="picked" value="a">

		<!-- `toggle` 为 true 或 false -->
		<input type="checkbox" v-model="toggle">

		<!-- 当选中时，`selected` 为字符串 "abc" -->
		<select v-model="selected">
			<option value="abc">ABC</option>
		</select>

	# 但是有时我们想绑定 value 到 Vue 实例的一个动态属性上
	# 这时可以用 v-bind 实现,并且这个属性的值可以不是字符串
		
	
	# 复选框
		<input
			type="checkbox"

			v-model="toggle"

			v-bind:true-value="a"			//如果选中-true,绑定值到变量 ａ
			v-bind:false-value="b"			//如果未选中-false,绑定值到变量 b
		>
		// 当选中时
		vm.toggle === vm.a
		// 当没有选中时
		vm.toggle === vm.b
			
	# 单选摁钮
		<input type="radio" v-model="pick" v-bind:value="a">
		// 当选中时
		vm.pick === vm.a
	
	# 选择列表的选项
		<select v-model="selected">
			<!-- 内联对象字面量 -->
			<option v-bind:value="{ number: 123 }">123</option>
		</select>
		// 当选中时
		typeof vm.selected // => 'object'
		vm.selected.number // => 123


----------------------------
修饰符						|
----------------------------
	# .lazy
		* 在默认情况下,v-model 在 input 事件中同步输入框的值与数据 (除了 上述 IME 部分)
		* 你可以添加一个修饰符 lazy ,从而转变为在 change 事件中同步
			<!-- 在 "change" 而不是 "input" 事件中更新 -->
			<input v-model.lazy="msg" >

	# .number
		* 如果想自动将用户的输入值转为 Number 类型 (如果原值的转换结果为 NaN 则返回原值)
		* 可以添加一个修饰符 number 给 v-model 来处理输入值

		<input v-model.number="age" type="number">
		* 这通常很有用，因为在 type="number" 时 HTML 中输入的值也总是会返回字符串类型

	# .trim
		* 如果要自动过滤用户输入的首尾空格,可以添加 trim 修饰符到 v-model 上过滤输入
		<input v-model.trim="msg">

----------------------------
v-model 与组件				|
----------------------------