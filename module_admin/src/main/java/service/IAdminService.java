package service;

import dao.IBaseDao;
import domain.Admin;

public interface IAdminService extends IBaseService {
    Admin findById(String id);
}
