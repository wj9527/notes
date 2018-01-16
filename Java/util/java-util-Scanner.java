--------------------
Scanner				|
--------------------
	# 扫描器
	# 我用得最多的就是读取屏幕的输入(会阻塞线程)
		* 还可以用于读取文件,字符等等....
	# 构造 & 方法
		Scanner scanner = new Scanner(System.in);

		scanner.nextInt();			
		scanner.next();
		scanner.nextLine();
	
	
	# jdk1.5以前读取屏幕输入
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();

		