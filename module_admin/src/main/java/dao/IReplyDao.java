package dao;

import domain.Reply;

public interface IReplyDao extends IBaseDao {
    Reply findById(String r_id);
}
