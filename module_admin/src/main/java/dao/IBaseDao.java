package dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {
    //批量删除
    int batchDelete(List<String> list);
    //分页
    List findByPage(Map map);
    //统计总条数
    int getRowsCount(Map map);
    //添加
    int insert(Map param);
    //删除单个
    int deleteOne(String id);
    //编辑信息
    int editRow(Map map);
}
