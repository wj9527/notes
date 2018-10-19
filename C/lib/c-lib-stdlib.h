--------------------------------
stdlib							|
--------------------------------

FILE

NULL
	* null 

EXIT_SUCCESS = 0
	* 成功

EXIT_FAILURE = 1
	* 失败
	
exit()
	* 程序退出,参数为 int 值,表示程序的退出状态

system()
	* 执行系统命令
	* 返回值 int,该值就是程序的返回值,在不同的平台不一样

char *getenv (const char *);
	* 获取系统环境变量,如果不存在返回NULL
		char *p = getenv("JAVA_HOME");
		printf("%s",p);	//C:\Program Files\Java\jdk1.8.0_171

srand(unsigned int)
	* 设置随机数的种子

rand()
	* 该函数返回一个随机数 int
	* 如果种子一样,那么多次调用的结果也一样

atoi(const char *nptr);
	* 把字符串转换为整形,然后返回
	* 会跳过前面的空格,直到遇到数字或者正负号才开始转换,遇到非数字字符串或者\0就结束转换,并且返回转换结果

stof(const char *nptr);
	* 同上
	* 把字符串转换为float
	
atol(const char *nptr);
	* 同上
	* 把字符串转换为long

void *malloc (size_t)
	* 参数表示申请的空间是多少
	* 如果申请成功,返回的数据就是申请的堆空间的首元素地址(指针),申请失败,返回 NULL
	* 申请的堆空间,如果程序没有结束,那么不会释放,需要程序手动的释放
	* demo
		int *p = (int *) malloc(sizeof(int));
		*p = 15;
		printf("%d",p[0]);		//15

void free (void *);
	* 释放堆空间的内存,交还给操作系统
	* 同一块儿的堆内存,只能执行一次释放操作
	* 释放掉内存后,执行该内存的指针就是野指针了

fclose()
fopen()
freopen()
fflush()