package service.impl;

import dao.IBaseDao;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl {
    public abstract IBaseDao getDao();

    public List findByPage(Map map) {
        return getDao().findByPage(map);
    }

    public int getRowsCount(Map map) {
        return getDao().getRowsCount(map);
    }

    public int insert(Map param) {
        return getDao().insert(param);
    }

    public int deleteOne(String id) {
        return getDao().deleteOne(id);
    }

    public int batchDelete(List<String> list) {
        return getDao().batchDelete(list);
    }

}
