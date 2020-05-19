package service.impl;

import dao.IAdminDao;
import dao.IBaseDao;
import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import service.IAdminService;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements IAdminService{

    @Autowired
    IAdminDao adminDao;


    @Override
    public IBaseDao getDao() {
        return adminDao;
    }

    @Override
    public Admin findById(String id) {
        return adminDao.findById(id);
    }
}
