package dao;

import domain.Admin;
import domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDao extends IBaseDao{
    Admin findById(String a_id);
    Admin findByName(String a_name);
}
