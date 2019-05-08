-----------------
find 检索		 |
-----------------
	# 检索集合中的子集
		* 第一个参数是一个文档对象,表示检索复合条件的记录,如果参数是空,或者空对象,那么则是检索所有
			db.[collection].find({}) = db.[collection].find();
	
		* 第二个参数,表示需要检索的文档字段

			db.[collection].find({},{'id':1}); // 仅仅检索文档中的id字段,默认情况下 '_id' 字段总是返回的
		
			* {'id':1} ,表示要检索id字段,如果它的值是0,则表示不检索id字段
		

		
-----------------
find 查询条件	 |
-----------------
	$lt		(<)
	$lte	(<=)
	$gt		(>)
	$gte	(>=)
	$ne		(!= )
	$eq		(==)
		* 基本的判断
			db.[collection].find({
				'id':{			//// 仅仅检索id大于1,小于10的记录
					'$lt':10,
					'$gt':1
				}
			});	
		
		* 可以用在日期上,new Date()
	

	$in
		* 匹配多个值
			db.users.find({
				'id':{
					'$in':[1,3]		// 检索id为 1 和 3 的记录
				}
			})
		
		* $in 的值也可以是不同的数据类型: '$in':[13,'Helo'] ,只要是匹配的都被会检索出来
	
	$nin
		* 跟 $in 相反,它匹配出所有不存在的急
			db.users.find({
				'id':{
					'$nin':[1,3]		// 检索id不为 1 和 3 的记录
				}
			})
	
	$or
		* 表示多个条件为或的逻辑
			db.users.find({
				'$or':[{
					'id':5					//检索id = 5
				},{							// 或者
					'id':{	
						'$in':[1,3]			// id = 1 或者 3的文档
					}
				}]
			});
	
	$mod
		* 对指定的字段进行取模计算
		* 它的值是一个数组,第0个元素是取模的数值,第1个元素是取模的结果
			db.users.find({
				'id':{
					'$mod':[3,1]		// 检索 id % 3 == 1 的所有记录
				}
			});
	
	$not
		* 表示对结果取反
			db.users.find({
				'id':{
					'$not':{
						'$in':[1,3]		// 检索id 不等于 1,3 的文档记录
					}
				}
			});
	

		

	$exists
		* 使用它来处理 null 只
			db.users.find({
				'foo':null		// 检索 foo 字段为 null的记录
			});

			* 如果文档不存在 foo 字段,也会被认为是符合条件
		
		* 使用 $exists 判断指定的字段是否存在
			db.users.find({
				'foo':{		
					'$eq':null,			// 检索 foo 字段为 null的记录
					'$exists':true		// 并且该字段是存在于文档中的
				}
			});
	

	# 正则检索
		* 条件可以是合法的正则表达式
		* 使用JS的正则语法
			db.users.find({
				'name':/\d{1}/		// 检索name字段是数字,并且只有一位的记录
			});
	
	
	# 对数组的查询
		* 数组的过滤条件默认跟in其实是一样的
			db.users.insertMany([{
				'id':1,
				'hobby':['Java','Python','Javascript']
			},{
				'id':2,
				'hobby':['Java','Ruby','Cplusplus']
			}]);

			db.users.find({
				'hobby':'Java'		// 检索 hoby 数组中,存在 Java 元素的文档
			});

			db.users.find({
				'hobby':['Java','Ruby','Cplusplus']  // 检索 hoby 数组中,必须存在 Java Ruby Cplusplus 元素的文档,一个不能多,一个不能少
			});

			* 而且检索条件中元素的声明顺序和文档的顺序必须一致
			
	
		$all
			* 必须匹配列表中的元帅
				db.users.find({
					'hobby':{
						'$all':['Java','Ruby','Cplusplus']		// // 检索 hoby 数组中,必须存在 Java Ruby Cplusplus 元素的文档(如果有多的,也会被检索出来)
					}
				});
			* 与顺序无关 $all 声明的顺序跟文档[]中元素的顺序可以不一样
		
		* 使用下标匹配
			db.users.find({
				'hobby.0':'Java'		// 匹配出 hobby 数组第一个元素是 Java 的结果
			});
		
		$size
			* 用于匹配指定长度的数组
				db.users.find({
					'hobby':{
						'$size':3 // 匹配出 hobby 数组长度 = 3的记录
					}
				});

			* $size 并不支持 $lt/$ne 之类的操作,可以考虑在文档中维护一个字段(size),该字段(size)的值就是数组字段的长度,通过对size字段进行判断,达到过滤的效果

		
		$slice
			* 它应该存在于 find() 的第二个函数,用于限制返回文档中数组元素的个数
				db.users.find({},{'hobby':{
					'$slice':-1		// 仅仅返回的文档中 hobby 数组的最后一个元素
				}});

				* 正数,表示头几个
				* 负数,表示后几个
			
			* 还支持使用区间,使用[] 表示开始的角标以及个数
				db.users.find({},{'hobby':{
					'$slice':[2,3]		// 仅仅返回的文档中 hobby 数组中,从2角标开始一共3个元素
				}})
		
	$elemMatch
			



	$where
		* 这个是利用了js代码执行检索判断
		* 应该严格限制这种检索方式
			db.users.find({
				'$where':function(){
					
				}
			});

			* 如果该函数 返回 true,则表示该文档符合规则,返回 false 则表示不符合	
		
		* 贼慢,不建议使用
	



			

		
		
-----------------
find 游标		 |
-----------------
	# 使用一个变量接收 find() 的检索结果集
		* 执行 find() 时并不会去检索数据,而是当开始迭代的时候,才会去检索记录

	# 返回值本质上就是一个迭代器
		var cursor = db.users.find({});

		while (cursor.hasNext()){
			var document = cursor.next();
			print(document.id);
		}

		hasNext()
			* 判断是否还有下一个
		next()
			* 获取下一个
	
	# 也可以使用 forEach 去遍历
		var cursor = db.users.find({});
		cursor.forEach(function(document){
			print(document.id);
		});
	
	# 支持一系列的方法链
		sort();
			* 排序
			* 参数是一个对象,key就是排序的字段,value是数值
			* 正数是升序排序,负数是逆序排序,可以同时存在多个排序策略
				sort({'name':1,'age':-1});  // 按照名称升序,按照年龄逆序

		skip();
			* 丢弃前面的几个记录

		limit();
			* 限制结果数量上限



		var cursor = db.users.find({}).sort({'id':1}).limit(10).skip(1);

	
