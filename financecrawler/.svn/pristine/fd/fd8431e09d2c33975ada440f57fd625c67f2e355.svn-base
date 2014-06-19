package cn.whu.zl.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.GenericDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional(propagation = Propagation.REQUIRED)
public class GenericJPADao<T extends Serializable, PK extends Serializable>
		implements GenericDao<T, PK> {
	@PersistenceUnit(unitName = "emf")
	private EntityManagerFactory emf;

	@PersistenceContext(unitName = "emf")
	protected EntityManager em;

	// 实体类类型(由构造方法自动赋值)
	private Class<T> entityClass;

	// 构造方法，根据实例类自动获取实体类类型

	public GenericJPADao() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}

		
	}

	// -------------------- 基本检索、增加、修改、删除操作 --------------------

	// 根据主键获取实体。如果没有相应的实体，返回 null。
	@Override
	public T get(PK id) {
		return (T) em.find(entityClass, id);
	}

	// 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	@Override
	public T getWithLock(PK id, LockModeType lock) {
		T t = (T) em.find(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}

	// loadAllWithLock() ?

	// 更新实体
	@Override
	@Transactional
	public void update(T entity) {
		em.merge(entity);
		em.clear();
		updateFlag = true;
	}

	// 存储实体到数据库
	@Override
	@Transactional
	public void save(T entity) {
		em.persist(entity);
		em.clear();
		updateFlag = true;
	}

	// saveWithLock()？

	// 增加或更新实体
	@Override
	@Transactional
	public void saveOrUpdate(T entity) {
		if (em.contains(entity) || isExists) {
			this.update(entity);
		} else
			this.save(entity);
		em.clear();
		updateFlag = true;
	}

	public boolean isContainEntity(T entity) {
		return false;
	}

	// 删除指定的实体
	@Override
	@Transactional
	public void delete(T entity) {
		em.remove(entity);
		em.clear();
		updateFlag = true;
	}

	// 根据主键删除指定实体
	@Override
	@Transactional
	public void deleteByKey(PK id) {
		T entity;
		if ((entity = this.get(id)) != null)
			this.delete(entity);
		em.clear();
		updateFlag = true;
	}

	// -------------------------------- Others --------------------------------

	// 加锁指定的实体
	@Override
	public void lock(T entity, LockModeType lock) {
		em.lock(entity, lock);
	}

	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush() {
		em.flush();
		updateFlag = true;
	}

	public void refresh(T entity) {
		em.refresh(entity);
	}

	public void refreshList(T[] entities) {
		for (T entity : entities)
			em.refresh(entity);
	}

	public List<T> getAllList(int recordNo, int pageSize) {
		Query q = em.createQuery("select e from " + entityClass.getSimpleName()+" e",entityClass);
		q.setFirstResult(recordNo);
		q.setMaxResults(pageSize);
		return q.getResultList();
	}
	
	public Long getSizes() {
		if(updateFlag){
			sizes =(Long) em.createQuery("select count(e) from " + entityClass.getSimpleName() + " e").getResultList().get(0);
			updateFlag = false;
		}
		return sizes;
	}

	protected boolean isExists = false;
	/*
	 * @PostLoad public void setEmType(){ em.setFlushMode(FlushModeType.COMMIT);
	 * }
	 */
	private Long sizes;
	
	private boolean updateFlag = true;
}
