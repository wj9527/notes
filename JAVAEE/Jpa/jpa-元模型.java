--------------------------
元模型
--------------------------
	# 主要用于描述实体类的属性

	# 规范元模型类的属性全部是 static 和 public 的
	
	# 实体类
		@Entity
		@Table
		public class Employee{  
			private int id;   
			private String name;
			private int age;
			@OneToMany
			private List<Address> addresses;
		}
	
	# 实体类的元模型
		import javax.annotation.Generated;
		import javax.persistence.metamodel.SingularAttribute;
		import javax.persistence.metamodel.ListAttribute;
		import javax.persistence.metamodel.StaticMetamodel;

		@StaticMetamodel(Employee.class)
		public class EmployeeMetamodel {     
			public static volatile SingularAttribute<Employee, Integer> id;   
			public static volatile SingularAttribute<Employee, Integer> age;   
			public static volatile SingularAttribute<Employee, String> name;    
			public static volatile ListAttribute<Employee, Address> addresses;
		}
		
		SingularAttribute
			* 普通单属性
		CollectionAttribute
		MapAttribute
		SetAttribute
		ListAttribute
			* 一对多/多对多属性
