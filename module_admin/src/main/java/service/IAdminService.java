package service;

import dao.IBaseDao;
import domain.Admin;

public interface IAdminService extends IBaseService {
    Admin findById(String a_id);
    Admin findByName(String a_name);
}
