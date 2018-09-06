------------------------
字符串常量				|
------------------------
	# C語言中没有字符串,使用的是字符数组
	# 字符串是内存中一段连续的char空间,以'\0'(数字0)结尾
	# 字符串一定是字符数组,字符数组不一定是字符串
		//字符数组
		char name[5] = {'K','e','v','i','n'};
		//字符串
		char name[10] = {'K','e','v','i','n','\0'};
		//字符串
		char name[10] = {'K','e','v','i','n',0};
		//字符串
		char a1[10] = {'a','b','c'};(因为后面的角标默认值为0,0就是结束符)

		* 字符数组以'\0'结尾,那么这个字符数组就是字符串
	
	# '%s'遇到结束符便结束
		    char a1[] = {'a','b','c','\0','d','e','f'};
			printf("%s",a1);        //abc
	
	# 最常用的初始化
		char string[] = "你好啊";
		
		* 字符串的结尾,编译器会自动添加结束符

			char string[] = "你好";
			printf("%s size=%d",string,sizeof(string));
			//你好 size=7  
			//UTF8编码一个汉字占3个字节,最后还有一个隐藏的0,一个字节表示结束符
		
		* 就算是自己主动添加结束符,编译器还是会帮你添加一个
			const char names[] = "Hello\0";
			printf("string = %s,size = %d",names,sizeof(names));
			//string = Hello,size = 7
		
		* 遇到 \0 便结束
			char string[] = "Hello\0c";
			printf("%s size=%d",string,sizeof(string));
			//Hello size=8
			//遇到\0结束,所以只打印了Hello,最后自动添加了\0结束符,所以也会占一个字节
		
		* \0后别跟数字,可能刚好组成一个转义字符
			char string[] = "\0123Hello";
			printf("%s size=%d",string,sizeof(string));
			//
			//3Hello size=8
			// '\012' 被转义成了一个换行符(012是一个八进制,在ascii中表示换行)
		
		* 如果限定字符长度,一定要给结束符留一个位置
			char string[6] = "Hello";
			printf("%s size=%d",string,sizeof(string));
			//Hello size=5
			//如果[]小于6,会有乱码,因为没有留给结束符位置
	
	# 获取字符串的长度(字节大小)
		* 使用 strlen(const char *s) 函数
		* 计算并返回字符串s的长度,不包含结束符 '\0',size_t 类型
		* 仅仅计算第一个文件分隔符以前的字符串长度
	
	# 字符串的copy
		* 使用 strcpy(char *dst,char *src) 函数
		* 如果src的内容长度大于dst的,那么会产生数据溢出
		* 只会拷贝第一个'\0'前的数据
			char name[11] = "KevinBlandy";
			char cp_name[11];

			strcpy(cp_name,name);
			printf("%s\n",cp_name);		//KevinBlandy
		
		* strncpy(char *dst,char *src,int size) 
		* 把 src 的数据拷贝到 dst,size参数可以限制copy的长度
		* 建议只复制 sizeof(dst) - 1 个元素到目标,因为要留一个位置给'\0'
		* 如果 size 超过了 sizeof(dst) - 1,那么会发生溢出
			char str[] = "123456";
			char dst[5];
			strncpy(dst,str,sizeof(dst) - 1);
			printf("%s",dst);       //123

		* 如果 '\0' 在拷贝的范围之内,那么'\0'以后的数据全部会被丢弃
			char str[] = "Hello\0Java";
			char dst[100];

			strncpy(dst,str,sizeof(str));

			printf("dst = %s\n",dst);                       //Hello
			printf("dst = %s\n",dst + strlen("Hello") + 1); //
			

	
------------------------
字符的输入/输出			|
------------------------
	# scanf 和 printf
		char ch;
		scanf("%c",&ch);
		printf("%c",ch);

		char name[100];
		char buf[100] = {0};
		scanf("%s",buf);
		printf("你是输入的是:%s\n",buf);

		* 默认以空格为分割,仅仅读取空格符前面的数据,后面的字符会被添加到缓冲区
		* 'scanf() 这种方式输入不会有越界检查,不安全'
		* scanf 会自动对输入的字符添加 '\0'
	
	# getchar() / putchar()
		* 专门用于处理单个字符的函数
		* 一次性的读取/输出单个字符,效率比较高,因为专门处理的就是字符
			char ch;
			scanf("%c",&ch)

			char ch = '1';
			printf("%d",ch)

	
	# 使用 gets / fgets 读取字符串
		gets(char *s)
			* 已经废弃了,从标准输入读取字符,并保存到s指定的内存空间直到出现换行符或者文件结尾为止
			* 可能会发生数组越界的情况,因为不知道用户输入的内容大小,不建议使用
		
		fgets(char *s,int size,FILE stream)
			* 参数
				s: 字符串
				size:指定读取最大字符串的长度(默认 -1,不限制)
				stram:文件指针,'如果读取键盘输入的字符串,固定为stdin'
			* 从stream指定的文件内读入字符,保存到s指定的内存空间,直到出现换行字符,读取到文件结尾,或是已经读取了size - 1 个字符为止
			* 会自动在最后加上 '\0' 标识,会把换行符也一起读取进去
			* s最多只能装 length - 1个字符,因为必须要留一个给字符串结束符 '\0',如果输入内容大于了 size 或者 sizeof(s) 那么超出部分会被截断
			* 读取成功返回读取到的字符串,读取到文件末尾或者出错,返回 NULL
			* 读取键盘输入demo
				char buf[100];
				fgets(buf,sizeof(buf),stdin);
				printf("你输入的是:%s\n",buf);

	# 使用 puts / fputs 来输出字符串
		puts(const char *s)
			* 标准设备输出s字符串,会自动添加换行符 \n
			* 成功返回非负数,失败返回 -1

		fputs(const char *str,FILE *stream)
			* 把 str 字符写入到stream指定的文件中,字符串结束符 '\0' 不写入文件
			* 成功返回 0,失败返回 -1
			* 可以把stream替换为 stdout,使str被输出到屏幕
		

		

	
		

		
	

