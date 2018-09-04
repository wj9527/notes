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

------------------------
字符的输入/输出			|
------------------------
	# 单个字符
		char ch;
		scanf("%c",&ch);
		printf("%c",ch);

		char ch = getchar();
		printf("%c",ch);
	
	# 多个
		char name[100];
		scanf("%s",name);
		printf("input = %s,size = %d\n",name,sizeof(name));
		//123(输入)
		//input = 123,size = 100
		
		* 遇到空格截断
			char name[100];
			scanf("%s",name);
			printf("%s",name);
			//Hello World
			//Hello( World没有被输出,因为遇到了空格就被截断了, World被放在了缓冲区)
		

		


