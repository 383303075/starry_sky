package service.impl;

import dao.IBaseDao;
import dao.ICommentDao;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ICommentService;

import java.util.Map;

@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {
    @Autowired
    ICommentDao commentDao;
    @Override
    public IBaseDao getDao() {
        return commentDao;
    }

    @Override
    public Comment findById(String c_id) {
        return commentDao.findById(c_id);
    }
}
