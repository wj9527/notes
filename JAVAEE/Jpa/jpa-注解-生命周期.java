---------------------
生命周期相关的注解
---------------------
	@PrePersist
	@PostPersist

		* 在save前后后调用
	
	@PreUpdate
	@PostUpdate 
		* 在修改前后调用
	
	@PreRemove 
	@PostRemove 
		* 在删除前后调用

	@EntityListeners
		* 指定外部周期的实现类
	
	@PostLoad
		* entity被映射(find, load....)之后调用
