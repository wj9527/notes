--------------------------------
string							|
--------------------------------

strcpy(s1, s2);
	* 复制字符串 s2 到字符串 s1
		char name[11] = "KevinBlandy";
		char cp_name[11];

		strcpy(cp_name,name);
		printf("%s\n",cp_name);		//KevinBlandy

strcat(s1, s2)
	* 连接字符串 s2 到字符串 s1 的末尾
		char s1[11] = "Hello";
		char s2[11] = " C";
		strcat(s1,s2);
		printf("%s\n",s1);	//HelloC

strlen(s1)
	*  返回字符串的长度,仅仅计算到第一个分割符
		char s1[11] = "Hello";
		char s2[11] = "He\0llo";
		printf("%d %d\n",strlen(s1),strlen(s2));		//5 2

strcmp(s1,s2)
	* 字符串的比较
	* 如果 s1 和 s2 是相同的,则返回 0
	* 如果 s1<s2 则返回小于 0,如果 s1 > s2 则返回大于 0
		char s1[11] = "Hello";
		char s2[11] = "Hi";
		char s3[11] = "Hello";
		printf("%d %d %d\n",strcmp(s1,s3),strcmp(s1,s2),strcmp(s2,s1));	//0 -1 1

strchr(s1, ch);
	* 返回一个指针,指向字符串 s1 中字符 ch 的第一次出现的位置
	* 如果没找到返回0
		char s1[11] = "Hello";
		printf("%p\n",strchr(s1,'H'));		//28FF35

strstr(s1, s2);
	* 返回一个指针,指向字符串 s1 中字符串 s2 的第一次出现的位置
	* 如果没找到返回0
		char s1[11] = "Hello";
		char s2[11] = "ll";
		printf("%p",strstr(s1,s2));	//0028FF37