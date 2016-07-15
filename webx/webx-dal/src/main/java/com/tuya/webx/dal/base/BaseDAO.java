
package com.tuya.webx.dal.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseDAO<T> extends SqlSessionDaoSupport {

    protected static Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


    protected List<T> queryForList(String statementName, Object parameterObject) {
        List<T> resultList = this.getSqlSession().selectList(statementName, parameterObject);
        return resultList;
    }


    protected List<T> queryList(String statementName, Object parameterObject) {
        List<T> resultList = this.getSqlSession().selectList(statementName, parameterObject);
        return resultList;
    }

    @SuppressWarnings("unchecked")
    protected T queryForObject(String statementName, Object parameterObject) {
        T returnObject = (T) this.getSqlSession().selectOne(statementName, parameterObject);
        return returnObject;
    }


    protected int executeUpdate(String statementName, Object parameterObject) throws DataAccessException {

        int updateRows = 0;
        updateRows = this.getSqlSession().update(statementName, parameterObject);
        return updateRows;
    }

    protected int queryCount(String statementName, Object parameterObject) {
        Object obj = this.getSqlSession().selectOne(statementName, parameterObject);
        return (obj != null) ? Integer.valueOf(obj.toString()) : 0;
    }

    protected Object executeInsert(String statementName, Object parameterObject) {
        Object back = null;
        back = this.getSqlSession().insert(statementName, parameterObject);
        return back;
    }

    protected int insert(String statementName, Object parameterObject) {
        int primaryKey = this.getSqlSession().insert(statementName, parameterObject);
        return primaryKey;
    }


    protected int executeDelete(String statementName, Object parameterObject) throws DataAccessException {
        int updateRows = 0;
        updateRows = this.getSqlSession().delete(statementName, parameterObject);
        return updateRows;
    }

    protected int executeDelete(String statementName) throws DataAccessException {
        int updateRows = 0;
        updateRows = this.getSqlSession().delete(statementName);
        return updateRows;
    }

    public String wrapNamespace(String name) {
        return this.getClass().getCanonicalName() + "." + name;
    }

}
