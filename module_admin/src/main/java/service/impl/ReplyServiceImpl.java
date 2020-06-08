package service.impl;

import dao.IBaseDao;
import dao.IReplyDao;
import domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IReplyService;


@Service
public class ReplyServiceImpl extends BaseServiceImpl implements IReplyService {
    @Autowired
    IReplyDao replyDao;
    @Override
    public Reply findById(String r_id) {
        return replyDao.findById(r_id);
    }

    @Override
    public IBaseDao getDao() {
        return replyDao;
    }
}
