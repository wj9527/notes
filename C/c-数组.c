--------------------------------
数组							|
--------------------------------
	# 存储一系列相同的值
		* 数组在内存中是连续存储的,所有成员在内存中的地址是连续的
	
	# 数组属于构造数据类型
		* 一个数组可以分解为多个数组,这些数组元素可以是基本数据类型或者构造类型
		* 按照数据元素的不同,数组可以分为:数值数组,字符数组,指针数组,结构数组等类别

	# 使用下标来访问数据的元素,下标从0开始
		* 使用数组最好不要越界
		* 编译器不会去检查数组下标是否正确
	# 数组定义
		int nums[10];

		* 如果使用变量来定义数组长度,那么尽量使用常量(部分编译器差异会导致编译失败)
			int len = 10;
			int arr[len];	//在不同的编译环境可能会编译失败
		* 使用 #define 或者 const 都可以

	# 可以使用表达式,常量,变量来初始化数组成员
		int arr[10];
		arr[0] = 1;
		arr[1] = 1 + 1;
		int num = 4;
		arr[2] = num;
	
	# 数组的初始化
		* 直接初始化所有值
			int arr[5] = {1,2,3,4,5};

		* 部分初始化,未初始化部分为0
			int arr[5] = {1,2,3}	//角标3,4为0

		* 数组全部初始化为0(可以省略括号中的0)
			int arr[5] = {0}

		* 如果直接初始化所有值,那么可以不用声明数组长度
			int arr[] = {1,2,3,4,5}
		
		* 指定初始化,在{},可以使用[index]来初始化指定角标的值
			int arr[3] = { [0]=0, 1, [2]=2 };

			int arr[] = {1,[99]=10};
				* 这个数组会有100元素,编译器会把数组的大小初始化为足够装下初始化的值

		* 局部未初始化的数组,默认值是随机数
		* 直接初始化时,如果成员数量与数组长度不一致会异常

	# 数组名,就是首元素的地址
		//数组
		int arr[10] = {1,2,3,4,5,6,7,8,9,10};
		//指向首元素的指针
		int *p = NULL;
		//首元素地址
		p = arr;	//p = &arr[0];
		for(int x = 0 ;x < 10 ;x++){
			//arr[x] = *(p + x) = *(arr + x)
			printf("%d %d %d\n",arr[x],*(p + x),*(arr + x));
		}
	
	
	# 数组名称是常量,不能修改
		int arr[4];
		arr = 10; //error

		* 有点类似于 : int const * p;

	
	# 计算数组长度
		//5个长度的int类型数组
		int arr[5] = {1,2,3,4,5};
		//计算出int的字节大小
		size_t int_size = sizeof(int);
		//计算出数组的字节大小
		size_t arr_size = sizeof(arr);
		//总大小 / 元素的大小,获得数组的长度
		int arr_length = arr_size / int_size;

		printf("int大小=%d,数组大小=%lu,数组长度=%d\n",int_size,arr_size,arr_length);//int大小=4,数组大小=20,数组长度=5

		printf("数组长度=%d\n",sizeof(arr) / sizeof(arr[0]));						//数组长度=5

	# 数组与指针的区别
		* 数组的 sizeof 运算返回的是数组占用内存的大小,指针的运算返回的是指针的大小
		* 数组的[x]操作相当于指针 *(p + x) 操作
		* 通过不断改变指针指向来遍历数据,比直接修改数组索引遍历满
			* 因为不断改变指针的值,需要指针重新运算,不断的修改内存
		
		* 指针可以进行 + / - 运算,数组不能
		* 数组是常量,指针是变量(除了被 const 修饰的)
	
	# arr 和 &arr
		int arr[] = { 1, 2, 3, 4, 5 };	//sizeof(arr) = 20
		printf("%p %p\n", arr, &arr);	//0x0028FF2C 0x0028FF2C
		printf("%p\n", arr + 1);	// + 4 = 0x0028FF30
		printf("%p\n", &arr + 1);	// + 20 = 0x0028FF40
		//arr[x] = *(arr + x)

		* &arr 表示了一个数组的指针,它的+-操作都会以数组的大小为单位进行
		* arr 表示执行了数组头元素的指针,它的 +- 操作都会以数组元素的大小为单位进行

--------------------------------
二维数组						|
--------------------------------
	# 二维数组的定义

		int arr[2][4];	//2个元素的数组,里面每个元素都是4个长度的数组

		* 内存是没有多维的这种概念,多维数组其实就是特殊的一维数组
		* 特殊的一维数组里面的元素,又是数组元素
	
	# 二维数组的初始化
		* 直接初始化
			int arr[2][3] = {{1,2,3},{4,5,6}};

			* 没有被初始化的元素为0
				int arr[][2] = {{1}}	//只有arr[0][0] = 1,其它的都是 0
					
		
		* 匹配模式的初始化
			int arr[2][3] = {1,2,3,4,5,6}
			
			* 先把123放入了第0个元素的0,1,2角标,再把456放入了第1个元素的0,1,2角标
			* 反正就是挨着放,结果跟 {{1,2,3},{4,5,6}} 是一样的,就是省略了里面的大括号而已
		
		* 初始化所有元素
			int arr[2][3] = {0}
			
			* 所有元素都初始化为0
			
		* 如果是直接初始化的方式(上面两张方式),那么第一个[](一维数组)可以不用手动的声明元素个数
			int arr[][3] = {1,2,3,4,5,6}

			* 一共有6个元素,每个2维数组的长度是3 , 6 / 3 = 2,所以就计算出了一维数组的长度

			* 没有初始化的元素为0
				int arr[][3] = {1,2,3,4,5};
				[0][0]=1
				[0][1]=2
				[0][2]=3
				[1][0]=4
				[1][1]=5
				[1][2]=0	//没有初始化的元素为0
			
	
	# 数组是常量,不能修改
		int arr[5][10];
		arr = 10; //error
	
	# 二维数组的长度计算
		* 原理分析
			int arr[5][10];
			sizeof(arr)			//10 * 5 * 4 = 200
			sizeof(arr[0]);		//10 * 4 = 40;
			sizeof(arr[0][0]);	//4
		
		* 计算代码
			int arr[][5] = {{1,2,3},{4,5,6}};

			//1,先计算出单个元素的字节大小
			int item_size = sizeof(arr[0][0]);
			//2,计算出二维数组的大小
			int two_dimensional_size = sizeof(arr[0]);
			//3,计算出一维数组的大小
			int one_dimensional_size = sizeof(arr);

			//二维数组的长度 = 二维数组的大小 / 单个元素的大小
			int two_dimensional_length = two_dimensional_size / item_size;
			//一维数组的长度 = 一维数组的大小 / 二维数组的大小
			int one_dimensional_length = one_dimensional_size / two_dimensional_size;

			printf("一维数组长度:%d,二维数组长度:%d",one_dimensional_length,two_dimensional_length);

			return EXIT_SUCCESS;

	# 数组名,就是首元素[0][0]的地址
		int arr[][5] = {{1,2,3},{4,5,6}};
		printf("%p,%p",arr,&arr[0][0]);		//0061FF08,0061FF08


	# 二维数组与指针
		int (*p)[2];
			* p是一个 int 指针,指向了一个具有2个 int 元素的数组 -> int arr[1][2]	-> [[1,2]]

		int (*p)[2][2]
			* p是一个 int 指针,执行了一个具有2个 int[2] 元素的数组 -> int arr[1][2][2]	-> [[[1,2],[3,4]]]

		int *p[2];
			* p是一个数组,里面有俩元素,都是 int 类型的指针

--------------------------------
传递数据给函数					|
--------------------------------
	# 想要在函数中传递一个一维数组作为参数,必须以下面三种方式来声明函数形式参数
	# 这三种声明方式的结果是一样的,因为每种方式都会告诉编译器将要接收一个整型指针
	# 同样地,也可以传递一个多维数组作为形式参数

	# 方式 1
		* 形式参数是一个指针
			void func(int *param){
			}
	
	# 方式 2
		* 形式参数是一个已定义大小的数组
			void myFunction(int param[10]){
			}
	
	# 方式 3
		* 形式参数是一个未定义大小的数组：

			void myFunction(int param[]){
			}

--------------------------------
匿名函数						|
--------------------------------
	# 也叫做复合字面量
	# 字面量
		(int[6]) { 1, 2, 3, 4, 5, 6 }

		* 6可以省略,编译器会自动推算
	
	# 字面量可以赋值给指针
		int *p;
		p = (int[6]) { 1, 2, 3, 4, 5, 6 };
	

	# 一维数组
		void print_arr(int *, size_t);

		int main(int argc, char **argv) {
			print_arr((int[6] ) { 1, 2, 3, 4, 5, 6 } , 6);
			return EXIT_SUCCESS;
		}

		void print_arr(int *p, size_t size) {
			while (size > 0) {
				printf("%d\n", p[--size]);
			}
		}

	# 二维数组
		void print_arr(int arr[][2], size_t);

		int main(int argc, char **argv) {
			int (*p)[2];

			p = (int[][2] ) { { 1, 2 }, { 3, 4 } };		//多维数组的字面量,需要声明第二纬数组的长度,因为实质上它是一个指针,需要二维长度来计算指针的运算

			print_arr(p, 2);

			return EXIT_SUCCESS;
		}

		void print_arr(int arr[][2], size_t size) {
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < 2; y++) {
					printf("%d\n", arr[x][y]);
				}
			}
		}

--------------------------------
vla变长数组						|
--------------------------------
	... 玄学的东西,很多编译器不支持

