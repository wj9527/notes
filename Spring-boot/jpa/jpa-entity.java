------------------
entity
------------------
	Persistable
		ID getId();
		boolean isNew();
			
	EntityInformation
		boolean isNew(T entity);

		@Nullable
		ID getId(T entity);
		Class<ID> getIdType();

		ID getRequiredId(T entity)
		Class<T> getJavaType();
	

		