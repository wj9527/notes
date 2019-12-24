----------------------
Repository			  |
----------------------
	# 核心的 Repository
		Repository		// 标识接口，spring会加载
			|-CrudRepository // 基本的CRUD
				|PagingAndSortingRepository // 排序和分页查询
			|-QueryByExampleExecutor // 可以根据Example条件查询
					|-JpaRepository  // 继承分页,条件,crud的接口
		JpaSpecificationExecutor
	
----------------------
CrudRepository		  |
----------------------

public interface CrudRepository<T, ID> extends Repository<T, ID> {
	<S extends T> S save(S entity);
		* 保存和修改, 都是使用 sava 方法
		* 区别就是看实体entity是否有主键, 有则是更新, 无则是创建

	<S extends T> Iterable<S> saveAll(Iterable<S> entities);
		* 持久化, 如果是自增id, id的值会回写到对象中

	Optional<T> findById(ID id);
		* 根据ID检索

	boolean existsById(ID id);
		* 根据ID判断是否存在

	Iterable<T> findAll();
	Iterable<T> findAllById(Iterable<ID> ids);
		* 检索所有或者根据ID检索一批记录

	long count();
		* 获取记录的总数量


	void deleteById(ID id);
	void delete(T entity);
	void deleteAll(Iterable<? extends T> entities);
	
		* 执行删除的时候,会先根据ID检索记录
		* 如果记录不存在(如果执行的是:deleteById,会抛出异常 EmptyResultDataAccessException ),先插入,再删除
		* 如果记录已经存在,直接删除, 删除操作都是根据ID来删除


	void deleteAll();
		* 直接删除表
	
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
