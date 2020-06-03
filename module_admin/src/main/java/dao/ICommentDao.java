package dao;

import domain.Comment;

public interface ICommentDao extends IBaseDao{
    Comment findById(String c_id);
}
