-----------------------
验证
-----------------------
	# 创建验证规则
		* 使用注解在对象的属性上进行标识

	# 验证步骤
		1. 使用 Validation 获取倒 ValidatorFactory
		2. 通过 ValidatorFactory 获取到 Validator
		3. 使用 Validator 对对象, 属性进行校验, 返回校验的结果
	

	

-----------------------
Object Graph验证
-----------------------
	# Object Graph是指对象的拓扑结构，比如对象的引用关系
		* Bean Validation支持Object Graph验证
	
	# 默认如果A对象引用B对象是不会对B对象进行校验的。需要在B对象的字段或者getter标识 @Valid 注解才行

