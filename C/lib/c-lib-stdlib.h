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

fclose()
fopen()
freopen()
fflush()

