package service.impl;

import dao.IBaseDao;
import dao.ICommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ICommentService;

@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {
    @Autowired
    ICommentDao commentDao;
    @Override
    public IBaseDao getDao() {
        return commentDao;
    }
}
