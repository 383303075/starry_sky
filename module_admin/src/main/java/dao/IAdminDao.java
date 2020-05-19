package dao;

import domain.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDao extends IBaseDao{
    Admin findById(String name);
}
