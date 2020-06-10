package service.impl;

import dao.IAdminDao;
import dao.IBaseDao;
import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import service.IAdminService;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements IAdminService{

    @Autowired
    IAdminDao adminDao;


    @Override
    public IBaseDao getDao() {
        return adminDao;
    }

    @Override
    public Admin findById(String a_id) {
        return adminDao.findById(a_id);
    }

    @Override
    public Admin findByName(String a_name) {
        return adminDao.findByName(a_name);
    }
}
