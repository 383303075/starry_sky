package dao;


import domain.User;

public interface IUserDao extends IBaseDao{
    User findById(String u_id);
}
