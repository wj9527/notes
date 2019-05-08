--------------------------------
文档							|
--------------------------------
	# 文档的key都是字符串

	# 文档中不能重复名称的 key

	# 文档必须要有一个 _id 属性
		* 该属性值可以是任何数据类型,如果没有设置,那么系统会自动插入,默认值是 ObjectId 对象
		* 每个集合中,文档的	_id 不能重复

	# 单个文档的最大值为: 54122 kb 
		* 可以通过Object的bsonsize方法查看db的文档大小约束

		Object.bsonsize(db.doc);

--------------------------------
基本命令						|
--------------------------------
	db.[colelction].insert([document])
		* 往指定的colelction插入一条或者多条记录
		* 如果是多条记录,那么参数应该是一个包含了多个document的数组
		* 如果该集合不在该数据库中, MongoDB 会自动创建该集合并插入文档
	
	db.[colelction].insertOne([document]) 
		* 插入一条记录,参数是一个document
	
	db.[colelction].insertMany([document...]) 
		* 插入多条记录,参数是一个document数组
			db.collection.insertMany([{"b": 3}, {'c': 4}])
		
		* 如果其中的一个文档插入失败(_id重复之类的异常),那么从该异常文档,及其以后的文档都会插入失败
		* 批量插入的最大消息体积默认为 48MB,如果超过该值,则会切割为多个 48MB的消息的批量插入
	


	db.[colelction].save([document]) 
		* 如果不指定 _id 字段 save() 方法类似于 insert() 方法
		* 如果指定 _id 字段,则会更新该 _id 的数据而不是插入
	

	db.[colelction].find()
		* 查看指定collection中已插入文档
	
	db.[colelction].findOne()
		* 查看指定collection中已插入文档
		* 仅仅检索一个结果
	
	db.[colelction].remove([document]);
		* 删除记录,使用一个文档参数作为条件,仅仅删除符合条件的记录
		* 如果给一个空对象({}),则删除所有的记录
		* 返回删除的数量
			{ "nRemoved" : 2 }
		* 删除操作不可逆,千万要慎重
		* 该api其实已经过时了
		* remove() 执行删除后,并不会释放空间,需要 执行 db.repairDatabase()  来回收磁盘空间
	
	db.[colelction].deleteOne()
		* 删除符合条件的第一个文档
	
	db.[colelction].deleteMany()
		* 删除符合条件的所有文档
	
		

--------------------------------
更新							|
--------------------------------
	
	# 更新的函数,需要两个必须的参数,以及一个非必须的 config 配置项
		* 一个是负责过滤的条件,如果它是一个空对象,表示匹配所有文档
		* 一个是要更新的操作,会彻底替换掉符合条件的记录(并不是更新非空的字段)

		db.[colelction].update([query], [update],   {
			upsert: true,
			multi: false,
			writeConcern: <document>
		})
	
		query
			* update的查询条件,类似sql update查询内where后面的
		
		update
			* update的对象和一些更新的操作符(如$,$inc...)等,也可以理解为sql update查询内后面的SET
		
		* config的配置选项
			upsert
				* 可选,这个参数的意思是,如果不存在update的记录,是否插入objNew,true 为插入
				* 该操作而且是原子性的,不会存在数据不一致的问题
				* 默认是 false 不插入
					
			
			multi
				* 仅仅更新符合条件的第一个,还是更新所有
				* 它如果是 true,只能用在修改器 $ 修改操作上
			
			writeConcern
				* 抛出异常的级别
		
		* 更新操作是一个原子操作,并发执行更新的时候,谁的请求先到达,谁就先执行,后面的更新会覆盖前面的更新
	
		
	# 修改器,modifter
		* 通常文档只有一部分会更新(并不是更新所有的字段)
		* 修改器的其实就是一系列的指令,使用 $ 开头
		* 指令的表达式,大都可以使用对象导航的方式来判断,或者操作
			* 对象导航
				{'sub':{'name':'KevinBlandy'}}			sub.name

			* 数组导航(使用下标)
				{'sub':[{'name':'KevinBlandy'}]}		sub.0.name

		$set
			* 用来设置一个字段的值,如果该字段不存在,则创建
			* 它还可以修改value的数据类型,原始的类型是字符串,可以被修改为[],{}.....
				db.users.update({'id':'1'},{
						'$set':{
							'name':'new New',			// 设置name属性
							'foo':'new field'			// 设置foo属性
						}
					},{
						multi:true						// 修改多行
				});
			
			* 它支持使用对象导航的形式去修改记录
				db.users.insert({
					'id':1,
					'name':'KevinBlandy',
					'skill':{
						'foo':'foo',
						'bar':'bar'
					}
				});

				db.users.update({'id':1},{
					'$set':{
						'skill.foo': 'New Foo Value' // 修改文档的 skill属性值的foo属性值
					}
				});
		
		$unset
			* 它用来删除文档的field
				db.users.update({'id':'1'},{
						'$unset':{
							'foo': 1			// 删除所有文档的 foo 属性
						}
					},{
						multi:true
				});

		
		$inc
			* 对数值属性进行加减,如果记录不存在,则会创建(值就会设置为要修改的值)
			* 它只能用在 数值 类型的字段上,如果是其他的数据类型会操作失败
			* 该指令的值也只能是数值,不能是其他的
				db.users.insert({
					'id':2,
					'number':1,
				});

				db.users.update({'id':2,},{
					'$inc':{
						'number': 1		// 对文档的number属性 + 1
					}
				});
		
		$push
			* 数组修改器,可以往数组里面 push 元素
			* 如果数组field不存在,就新创建,再push
				db.users.insert({
					'id':3,
					'skills':['Java','Python'],
				});

				db.users.update({'id':3,},{
					'$push':{
						'skills': 'Javascript'
					}
				});
			* 该指令一次性,只能push一条记录,如果你的value是数组,则会把整个数组当作一个value push到文档的目标属性值数组里面
			* 如果需要一次性插入多个元素,可以配合 $each 指令
					db.users.update({'id':3,},{
						'$push':{
							'skills':{
								'$each':['Javascript', 'Ruby', 'C++']		// 使用 each 指令,往 skills里面push多个元素
							}
						}
					});
		
			* 可以使用 $slice 限制文档数组字段的长度
			* $slice 必须是一个负整数,表示仅仅保留最后的几个数据
				db.users.update({'id':3,},{
					'$push':{
						'skills':{
							'$each':['Javascript', 'Ruby', 'C++'],		// 插入3个元素
							'$slice': -2							// 仅仅保留最后两个,所以, skills 字段最后的结果就是 ['Ruby', 'C++']
						}
					}
				});
			
			* 可以使用 $ort 完成排序,配合 $slice指令,就可以对数组先排序,再限制长度
				db.users.update({'id':3,},{
					'$push':{
						'skills':{
							'$each':['Javascript', 'Ruby', 'C++'],
							'$slice': -1,							// 仅仅保留最后的一个元素
							'$sort':{'rating': -1}					// 先按照排序策略进行排序
						}
					}
				});


		$ne
			* 该指令可以判断指定的元素,是否在指定的数组中存在
			* 它适合放在update指令的过滤条件对象中
				db.users.insertMany([{
					'skills':['Java','Python'],
				},{
					'skills':['Java','Ruby'],
				}]);

				db.users.update({'skills':{
						'$ne':'Ruby'				//过滤条件: Ruby不存在于skill字段中的文档
					}},{
					'$push':{
						'skills':'Ruby'				// push Ruby 到 skills字段中
					}
				});
		
		$addToSet
			* 把数组[]当作Set使用的指令
				db.users.update({},{
					'$addToSet':{
						'skills':'Ruby'		//如果文档的skills里面不存在Ruby,就push,如果存在,不做任何处理
					}
				});
		
			* 配合 $each 指令,可以 addSet 多个值
				db.users.update({},{
					'$addToSet':{
						'skills':{
							'$each':['Java','C++','Javascript']		// skills字段里面如果不存在,就会添加,存在就啥也不作
						}
					}
				},{
					multi:true
				});

		
		$pop
			* 该指令用于从数组中弹出一个元素,可以是从头,也可以从尾
				db.users.update({},{
					'$pop':{
						'skills': -1		// -1 表示从头开始删除(0), 1 表示从尾部开始删除(length - 1)
					}
				},{
					multi:true
				});

		$pull
			* 该指令用于删除数组中的指定元素
			* 它会把匹配到的数组元素都从数组里面删除
				db.users.update({},{
					'$pull':{
						'skills': 'Ruby'		// 删除skills字段中的Ruby元素
					}
				},{
					multi:true
				});
		

								
		
		$
			* 定位操作符,可以通过该指令找到数组元素中匹配的元素
				db.users.insertMany([{
					'_id':1,
					'name':'KevinBlandy',
					'skills':[{
						'lang':'Java',
						'proficiency':10
					},{
						'lang':'Python',
						'proficiency':8
					},{
						'lang':'Javascript',
						'proficiency':8
					},{
						'lang':'C',
						'proficiency':5
					}],
				}]);

				db.users.update({'skills.lang':'C'},{		// (1)
					'$inc':{
						'skills.$.proficiency': 1			// (2)
					}
				},{
					multi:true
				});

				(1) 从 'skills' 的数组里面,匹配出 'lang' 属性值为 'C' 的元素,并且记录它的下标为 '$'
				(2) 'skills.$.proficiency', 对'skills'数组里面指定元素的 'proficiency' 值进行加1操作, '$' 就是条件中检索到的下标
		

		$setOnInsert
		
--------------------------------
FindAndModify					|
--------------------------------
