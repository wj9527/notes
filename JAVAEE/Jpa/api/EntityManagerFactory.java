-----------------------
EntityManagerFactory
-----------------------
	# 实体管理器的工厂类
	# 抽象方法
		EntityManager createEntityManager();
		EntityManager createEntityManager(Map map);
		EntityManager createEntityManager(SynchronizationType synchronizationType);
		EntityManager createEntityManager(SynchronizationType synchronizationType, Map map);
		CriteriaBuilder getCriteriaBuilder();
		Metamodel getMetamodel();
		boolean isOpen();
		void close();
		Map<String, Object> getProperties();
		Cache getCache();
		PersistenceUnitUtil getPersistenceUnitUtil();
		void addNamedQuery(String name, Query query);
		<T> T unwrap(Class<T> cls);
		<T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph);
	
