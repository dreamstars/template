package com.tuya.webx.dal.dao;

import com.tuya.webx.dal.entity.TUserDO;

import java.util.List;

/**
 * Created by wangzhongxing on 16/6/17.
 */
public interface TUserDAO {

    public TUserDO getByUserId(Integer userId);

    public TUserDO getByUsername(String username);

    public List<TUserDO> getAllUsers();

    public int addUser(TUserDO user);

    public int updateUserByUserId(TUserDO user);

    public int deleteUserByUserId(Integer userId);

}
