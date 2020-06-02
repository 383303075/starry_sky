package dao;


import domain.User;

import java.util.Map;

public interface IUserDao extends IBaseDao{
    User findById(String u_id);
}
