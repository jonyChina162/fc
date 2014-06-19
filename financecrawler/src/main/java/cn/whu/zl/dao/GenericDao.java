package cn.whu.zl.dao;

import java.io.Serializable;

import javax.persistence.LockModeType;

public interface GenericDao<T extends Serializable, PK extends Serializable> {
    // -------------------- 基本检索、增加、修改、删除操作 --------------------

    // 根据主键获取实体。如果没有相应的实体，返回 null。
    public T get(PK id);

    // 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
    public T getWithLock(PK id, LockModeType lock);

    // 更新实体
    public void update(T entity);


    // 存储实体到数据库
    public void save(T entity);

    // saveWithLock()

    // 增加或更新实体
    public void saveOrUpdate(T entity);


    // 删除指定的实体
    public void delete(T entity);



    // 根据主键删除指定实体
    public void deleteByKey(PK id);


    // -------------------------------- Others --------------------------------

    // 加锁指定的实体
    public void lock(T entity, LockModeType lockMode);



    // 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
    public void flush();

}
