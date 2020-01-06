---------------------
联合主键
---------------------
	@IdClass
		Class value();
		
		* 标识在实体类上. 指定id属性是外部的类的联合主键(复合主键类)
		* 作为复合主键类必须要有几个条件
			1. 必须实现 Serializable
			2. 必须有 public 无参构造器
			3. 必须覆写 hashCode equals 方法(EntityManger通过find方法来查找Entity时,是根据equals的返回值来判断的)
		
		// 使用用户 user_id 和 title 作为联合主键
		public class UserBlogKey implements Serializable {
			private Integer userId; 
			private String title;	
			// 忽略构造方法和getter/setter/equals/hashCode
		}

		@Entity
		@Table(name = "user_blog")
		@IdClass(value = UserBlogKey.class) // 指定联合主键类
		public class UserBlog {
			// 指定联合主键对象中的字段名称
			@Id
			@Column(name = "user_id", nullable = false)
			private Integer userId;
			// 指定联合主键对象中的字段名称
			@Id
			@Column(name = "title", nullable = false)
			private String title;
			
			// 其他的字段
			@Column(name = "name")
			private String name;  

			// 忽略getter/setter
		}

		CREATE TABLE `user_blog` (
		  `title` varchar(255) NOT NULL,
		  `user_id` int(11) NOT NULL,
		  `id` int(11) NOT NULL,
		  `name` varchar(255) DEFAULT NULL,
		  PRIMARY KEY (`title`,`user_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
		
		interface UserBlogRepository extends JpaRepository<UserBlog, UserBlogKey>