-----------------------
respository抽象
-----------------------
	// 抽象一个基础接口
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
	import org.springframework.data.repository.NoRepositoryBean;

	@NoRepositoryBean
	public interface BaseRepository <T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor <T> {

	}

-----------------------
Service抽象
-----------------------
	// 抽象一个  BaseService
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

	public interface BaseService <T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor <T> {

	}

	
	// 抽象一个 AbstractService 基础实现类
	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.domain.Example;
	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.Pageable;
	import org.springframework.data.domain.Sort;
	import org.springframework.data.jpa.domain.Specification;
	import org.springframework.transaction.annotation.Transactional;

	import io.springboot.jpa.repository.BaseRepository;


	public class AbstractService <T> implements BaseService<T> {

		@Autowired
		protected BaseRepository<T> baseRepository;
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public List<T> findAll() {
			return this.baseRepository.findAll();
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public List<T> findAll(Sort sort) {
			return this.baseRepository.findAll(sort);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public List<T> findAllById(Iterable<Integer> ids) {
			return this.baseRepository.findAllById(ids);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public <S extends T> List<S> saveAll(Iterable<S> entities) {
			return this.baseRepository.saveAll(entities);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void flush() {
			this.baseRepository.flush();
		}

		@Transactional(rollbackFor = Throwable.class)
		public <S extends T> S saveAndFlush(S entity) {
			return this.baseRepository.saveAndFlush(entity);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void deleteInBatch(Iterable<T> entities) {
			this.baseRepository.deleteInBatch(entities);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void deleteAllInBatch() {
			this.baseRepository.deleteAllInBatch();
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public T getOne(Integer id) {
			return this.baseRepository.getOne(id);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> List<S> findAll(Example<S> example) {
			return this.baseRepository.findAll(example);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
			return this.baseRepository.findAll(example, sort);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public Page<T> findAll(Pageable pageable) {
			return this.baseRepository.findAll(pageable);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public <S extends T> S save(S entity) {
			return this.baseRepository.save(entity);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public Optional<T> findById(Integer id) {
			return this.baseRepository.findById(id);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public boolean existsById(Integer id) {
			return this.baseRepository.existsById(id);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public long count() {
			return this.baseRepository.count();
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void deleteById(Integer id) {
			this.baseRepository.deleteById(id);		
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void delete(T entity) {
			this.baseRepository.delete(entity);		
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void deleteAll(Iterable<? extends T> entities) {
			this.baseRepository.deleteAll(entities);
		}

		@Override
		@Transactional(rollbackFor = Throwable.class)
		public void deleteAll() {
			this.baseRepository.deleteAll();
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> Optional<S> findOne(Example<S> example) {
			return this.baseRepository.findOne(example);
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
			return this.baseRepository.findAll(example, pageable);
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> long count(Example<S> example) {
			return this.baseRepository.count(example);
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public <S extends T> boolean exists(Example<S> example) {
			return this.baseRepository.exists(example);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public Optional<T> findOne(Specification<T> spec) {
			return this.baseRepository.findOne(spec);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public List<T> findAll(Specification<T> spec) {
			return this.baseRepository.findAll(spec);
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public Page<T> findAll(Specification<T> spec, Pageable pageable) {
			return this.baseRepository.findAll(spec, pageable);
		}
		
		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public List<T> findAll(Specification<T> spec, Sort sort) {
			return this.baseRepository.findAll(spec, sort);
		}

		@Override
		@Transactional(readOnly = true, rollbackFor = Throwable.class)
		public long count(Specification<T> spec) {
			return this.baseRepository.count(spec);
		}
	}
