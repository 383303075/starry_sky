package service;

import domain.User;

import java.util.Map;

public interface IUserService extends IBaseService{
    User findById(String u_id);
    User findByName(String u_name);
}
