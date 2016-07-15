package com.tuya.webx.dal.dao.impl;

import com.tuya.webx.dal.base.BaseDAO;
import com.tuya.webx.dal.dao.TUserDAO;
import com.tuya.webx.dal.entity.TUserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangzhongxing on 16/6/17.
 */
@Repository("UserDAOImpl")
public class TUserDAOImpl extends BaseDAO<TUserDO> implements TUserDAO {

    @Override
    public TUserDO getByUserId(Integer userId) {
        return this.queryForObject(wrapNamespace("getByUserId"), userId);
    }

    @Override
    public TUserDO getByUsername(String username) {
        return this.queryForObject(wrapNamespace("getByUsername"), username);
    }

    @Override
    public List<TUserDO> getAllUsers() {
        return this.queryForList(wrapNamespace("getAllUsers"), null);
    }

    @Override
    public int addUser(TUserDO user) {
        return this.insert(wrapNamespace("addUser"), user);
    }

    @Override
    public int updateUserByUserId(TUserDO user) {
        return this.executeUpdate(wrapNamespace("updateUserByUserId"), user);
    }

    @Override
    public int deleteUserByUserId(Integer userId) {
        return this.executeDelete(wrapNamespace("updateUserByUserId"), userId);
    }
}
