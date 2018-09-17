----------------------------
结构体						|
----------------------------
	# 数组允许定义可存储相同类型数据项的变量,结构是 C 编程中另一种用户自定义的可用的数据类型,它允许存储不同类型的数据项
	# 结构体的声明与定义和值的初始化
		struct Book {
			int id;
			char name[50];
			char *author;
			double price;
		};

		struct Book java,python;

		java.id = 1;	
		strcpy(java.name,"Java入门到精通");		//字符串数组可以使用copy
		java.author = "KevinBlandy";			//字符指针,可以直接赋值
		java.price = 15.6;

		printf("id=%d,name=%s,author=%s,price=%f",java.id,java.name,java.author,java.price);
		//id=1,name=Java入门到精通,author=KevinBlandy,price=15.600000
	
	# 结构作为函数参数
		struct Book {
			int id;
			char name[50];
			char *author;
			double price;
		};
		void func(struct Book book){
			printf("id=%d,name=%s,author=%s,price=%f\n",book.id,book.name,book.author,book.price);
		}
		int main(){
			struct Book java;
			java.id = 1;
			strcpy(java.name,"Java入门到精通");
			java.author = "KevinBlandy";
			java.price = 15.6;
			func(java);	//id=1,name=Java入门到精通,author=KevinBlandy,price=15.600000
			return EXIT_SUCCESS;
		}
	
	# 指向结构的指针
		struct Book {
			int id;
		};
		int main(){
			//初始化结构体
			struct Book java;
			//初始化属性值
			java.id = 15;

			//定义一个指向结构体的指针
			struct Book *p;
			//获取结构体的地址
			p = &java;
			
			//通过 -> 操作符来通过结构体指针访问结构体属性
			printf("id=%d\n",p -> id);
			return EXIT_SUCCESS;
		}

	
----------------------------
合法的结构体定义与声明		|
----------------------------
	# 同时定义结构体以及变量
		struct Book {
			int id;
			char name[50];
			char author[50];
			double price;
		} java,python;
		
	
	# 匿名结构体
		struct {
			int id;
			char name[50];
			char author[50];
			double price;
		} java,python;
	
	# 仅仅定义结构体
		struct Book {
			int id;
			char name[50];
			char author[50];
			double price;
		};

		* 声明该结构体的变量
			struct Book t1, t2[20],*t3;
 
	# 用typedef创建新类型
		typedef struct {
			int id;
			char name[50];
			char author[50];
			double price;
		} Book;

		* 现在可以用Book作为类型声明新的结构体变量
			Book t1, t2[20],*t3;
	


----------------------------
位域						|
----------------------------
	# 有些信息在存储时,并不需要占用一个完整的字节,而只需占几个或一个二进制位
	# 例如在存放一个开关量时,只有 0 和 1 两种状态,用 1 位二进位即可
	# 为了节省存储空间,并使处理简便,C 语言又提供了一种数据结构,称为"位域"或"位段"
	# 定义与赋值
		struct Bits {
			int a :8;				//a属性占了8bit
			unsigned int b :2;		//b属性占了2bit
			int c :6;				//c属性占了6bit
		} bits,*p;			

		bits.a = 2;			//初始化属性值

		p = &bits;			//获取地址
	
		//通过对象/指针访问值
		printf("%d %d\n",bits.a,p -> a);		//2 2

	
	# 一个位域必须存储在同一个字节中,不能跨两个字节
		* 如一个字节所剩空间不够存放另一位域时,应从下一单元起存放该位域,也可以有意使某位域从下一单元开始
		struct Bits {
			unsigned a :4; /* 占bit			*/
			unsigned   :4; /* 空域不使用	*/
			unsigned b :4; /* 从下一字节开始存放 */
			unsigned c :4;
		};
	
