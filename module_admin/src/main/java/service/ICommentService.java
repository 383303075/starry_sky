package service;

import domain.Comment;

public interface ICommentService extends IBaseService {
    Comment findById(String c_id);
}
