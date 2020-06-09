package dao;

import domain.Video;

public interface IVideoDao extends IBaseDao{

    Video findById(String v_id);
}
