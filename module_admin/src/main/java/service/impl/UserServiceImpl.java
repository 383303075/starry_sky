package service.impl;

import dao.IBaseDao;
import dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public IBaseDao getDao() {
        return userDao;
    }
}
