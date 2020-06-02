package service.impl;

import dao.IBaseDao;
import dao.IVideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IVideoService;

import java.util.Map;

@Service
public class VideoServiceImpl extends BaseServiceImpl implements IVideoService {

    @Autowired
    IVideoDao videoDao;

    @Override
    public IBaseDao getDao() {
        return videoDao;
    }
}
