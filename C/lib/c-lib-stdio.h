--------------------------------
stdio							|
--------------------------------

EOF
	* -1,表示文件结尾

stdin
	* 标准输入流指针
stdout
	* 标准输出流指针
stderr
	* 标准错误流指针


printf()
	* 用于标注输出(打印)
	* 支持字符串占位符,必须严格按照占位符的数据类型传递参数
		printf("Hello %d,%d",5,6);

sprintf(char dst*, const char *, ...)
	* 把 字符串格式化后,写入到dst中
		int x = 10;
		char y = 'H';
		char str[] = "Java";
		char dst[100];
		sprintf(dst,"x = %d,y = %c,buf = %s",x,y,str);
		printf("dst = %s\n",dst);	//dst = x = 10,y = H,buf = Java

puts()
	* 函数只用来输出字符串,不能使用占位符,自动添加换行
	* 里面的参数可以直接是字符串或者是存放字符串的字符数组名
	* 作用与 printf("%s\n",s);的作用形同

scanf()
	* 获取屏幕输入,跟 printf 一样,也可以使用格式字符串和参数列表
	* scanf 在读取的时候,会跳过所有非空白符前面的空白符
	* printf函数使用便利,常量和表达式,scanf函数使用变量的指针
		int var1;
		int var2;
		scanf("%d %d",&var1,&var2);
		printf("%d %d",var1,var2);

	* 当用户通过scanf输入字符时,编译器默认先把内容放在缓冲区
	* scanf自动在缓冲区读取内容

sscanf (const char *, const char *, ...)
	* 使用预定义的字符串,来作为标准输入数据填充模版值
		//定义一个"输入的字符串"
		char dst[] = "1 2 3";
		//定义变量
		int a,b,c;
		//使用 cccanf 把 输入的字符,赋值给变量
		sscanf(dst,"%d %d %d",&a,&b,&c);
		//成功赋值
		printf("a=%d,b=%d,c=%d\n",a,b,c);	//a=1,b=2,c=3

	* 提取整形变量是最方便的
		char inputs[] = "a=10,b=20";
		int a , b;
		sscanf(inputs,"a=%d,b=%d",&a,&b);
		printf("a=%d,b=%d\n",a,b);  //a=10,b=20

	* 提取字符串,默认以空格分割
		char temp[] = "abc def 123";
		char str1[4],str2[4],str3[4];
		sscanf(temp,"%s %s %s",str1,str2,str3);
		printf("str1=%s,str2=%s,str3=%s",str1,str2,str3);//str1=abc,str2=def,str3=123

getchar()
	* 读取下一个字符串输入,并且返回,它只处理字符
	* 等同于
		char ch;
		scanf("%c",&ch);

putchar()
	* 该函数的作用就是,打印该函数的参数,它只处理字符
	* 等同于
		char ch = '1';
		printf("%d",ch)

gets(char *s)
	* 从标准输入读取字符,并保存到s指定的内存空间直到出现换行符或者文件结尾为止
	* 返回读取成功的字符串,如果读取失败返回 NULL
	* 它与scanf的区别,它允许输入的数据有空格
	* 它与scanf都无法知道输入字符串的大小,必须遇到换行符或者读取到文件的结尾才接收输入因此容易导致数组越界(缓冲区溢出)
	* 该api已经废弃,不建议使用( warning: the `gets' function is dangerous and should not be used.)

fgets(char *s,int size,FILE *stream)
	* 参数
		s: 字符串
		size:指定读取最大字符串的长度(默认 -1,不限制)
		stram:文件指针,'如果读取键盘输入的字符串,固定为stdin'
	* 从stream指定的文件内读入字符,保存到s指定的内存空间,直到出现换行字符,读取到文件结尾,或是已经读取了size - 1 个字符为止
	* 会自动在最后加上 '\0' 标识,会把换行符也一起读取进去
	* s最多只能装 length - 1个字符,因为必须要留一个给字符串结束符 '\0',如果输入内容大于了 size 或者 sizeof(s) 那么超出部分会被截断
	* 读取成功返回读取到的字符串,读取到文件末尾或者出错,返回 NULL
	* 无法读取中文???
	* 读取键盘输入demo
		char buf[100];
		fgets(buf,sizeof(buf),stdin);
		printf("你输入的是:%s\n",buf);

puts(const char *s)
	* 标准设备输出s字符串,会自动添加换行符 \n
	* 成功返回非负数,失败返回 -1


fputs(const char *str,FILE *stream)
	* 把 str 字符写入到stream指定的文件中,字符串结束符 '\0' 不写入文件
	* 无法输出中文???
	





	