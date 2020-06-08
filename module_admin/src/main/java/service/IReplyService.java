package service;

import domain.Reply;

public interface IReplyService extends IBaseService{
    Reply findById(String r_id);
}
