----------------------
CrudRepository		  |
----------------------

public interface CrudRepository<T, ID> extends Repository<T, ID> {
	// 保存实体
	<S extends T> S save(S entity);
	// 批量保存
	<S extends T> Iterable<S> saveAll(Iterable<S> entities);
	/**
		* 自增长的id会回写到对象
	**/

	// 根据id检索
	Optional<T> findById(ID id);

	// 根据id判断是否存在
	boolean existsById(ID id);

	// 检索所有
	Iterable<T> findAll();
	// 根据多个id检索
	Iterable<T> findAllById(Iterable<ID> ids);

	// 总记录数量
	long count();


	// 根据id 删除
	void deleteById(ID id);
	// 删除指定的实体(会根据id删除)
	void delete(T entity);
	// 删除所有(会根据id删除)
	void deleteAll(Iterable<? extends T> entities);
	/***
		* 执行删除的时候,会先根据ID检索记录
		* 如果记录不存在(如果执行的是:deleteById,会抛出异常 EmptyResultDataAccessException ),先插入,再删除
		* 如果记录已经存在,直接删除
	***/



	// 清空表
	void deleteAll();
}
----------------------
JpaRepository		  |
----------------------
	public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

		List<T> findAll();

		List<T> findAll(Sort sort);

		List<T> findAllById(Iterable<ID> ids);

		Iterable<T> findAll(Sort sort);

		Page<T> findAll(Pageable pageable);


		<S extends T> List<S> saveAll(Iterable<S> entities);

		void flush();

		<S extends T> S saveAndFlush(S entity);

		void deleteInBatch(Iterable<T> entities);

		void deleteAllInBatch();

		T getOne(ID id);

		@Override
		<S extends T> List<S> findAll(Example<S> example);
		@Override
		<S extends T> List<S> findAll(Example<S> example, Sort sort);

		<S extends T> Optional<S> findOne(Example<S> example);
		<S extends T> Page<S> findAll(Example<S> example, Pageable pageable);
		<S extends T> long count(Example<S> example);
		<S extends T> boolean exists(Example<S> example);
	}
