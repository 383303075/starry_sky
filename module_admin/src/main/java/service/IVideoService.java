package service;

import domain.Video;

public interface IVideoService extends IBaseService{
    Video findById(String v_id);
}
