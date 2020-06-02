package service;

import java.util.List;
import java.util.Map;

public interface IBaseService {
    List findByPage(Map map);
    int getRowsCount(Map map);
    int insert(Map param);
    int deleteOne(String id);
    int batchDelete(List<String> list);
    int editRow(Map map);
}
