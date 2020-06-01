package service;

import domain.User;

public interface IUserService extends IBaseService{
    User findById(String u_id);
}
