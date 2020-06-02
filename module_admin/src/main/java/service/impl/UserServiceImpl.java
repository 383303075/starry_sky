package service.impl;

import dao.IBaseDao;
import dao.IUserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IUserService;

import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public IBaseDao getDao() {
        return userDao;
    }

    @Override
    public User findById(String u_id) {
        return userDao.findById(u_id);
    }

}
