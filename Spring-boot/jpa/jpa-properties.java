
# 相关的配置类
	JpaProperties (spring.jpa)
	SpringDataWebProperties (spring.data.web)
	HibernateProperties	(spring.jpa.hibernate)

# SpringData根JPA的相关配置
spring.data.jpa.repositories.bootstrap-mode=default
	* 枚举
		DEFAULT(默认)
		DEFERRED
		LAZY
spring.data.jpa.repositories.enabled=true
	

# JPA的配置
spring.jpa.database=
spring.jpa.database-platform=
spring.jpa.generate-ddl=false
spring.jpa.open-in-view=false
spring.jpa.show-sql=false
spring.jpa.properties.*
spring.jpa.mapping-resources=

spring.jpa.hibernate.ddl-auto=
	* 枚举
		create				不管表是否存在, 每次启动都会重新建表(会导致数据丢失)
		create-drop			启动的时候创建表, 程序退出(SessionFactory关闭)的时候删除表
		none				不进行任何操作
		update				如果数据表不存在则创建, 在实体对象被修改后,下次启动重新修改表结构(不会删除已经存在的数据)
		validate			启动的时候验证数据表的结构, 

spring.jpa.hibernate.naming.implicit-strategy=
spring.jpa.hibernate.naming.physical-strategy=
spring.jpa.hibernate.use-new-id-generator-mappings=

