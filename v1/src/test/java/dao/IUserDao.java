package dao;

import po.User;

public interface IUserDao {
    User queryUserInfoById(Long uId);
}
