package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IBaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.GetUUID.getUUID;

public abstract class BaseController {

    public abstract IBaseService getService();

    @RequestMapping("/findByPage")
    @ResponseBody
    public Map findByPage(@RequestBody Map map){
        int curr = (int)map.get("curr");//当前页数
        int nums = (int)map.get("nums");//显示条数
        System.out.println("进入到控制层");
        System.out.println("查询第" + curr + "页");//1 2  3  4 5
        System.out.println("每页显示" + nums+ "条");//10  10 10 10 10
        System.out.println("查询参数是：" + map.get("username"));

        Map param = new HashMap();
        param.put("start",(curr-1)*nums);
        param.put("end",nums);
        param.put("name",map.get("username"));

        List results = getService().findByPage(param);

        Map dataMap = new HashMap();

        dataMap.put("code","0");
        dataMap.put("msg","");
        //从数据库查询到具体的条数
        dataMap.put("count",getService().getRowsCount(param));//要计算总条数
        dataMap.put("data",results);

        return dataMap;
    }


    @RequestMapping("/add")
    @ResponseBody
    public  Map  addObject(@RequestBody Map map){

        if(map.get("address1")!=null){
            String ad1 = map.get("address1")+"省";
            String ad2 = (String) map.get("address2");
            String ad3 = (String) map.get("address3");
            String address = ad1+ad2+ad3;
            map.put("address",address);
            map.remove("address1");
            map.remove("address2");
            map.remove("address3");

        }

        //1、创建一个id
        map.put("u_id",getUUID());
        System.out.println(map.toString());
        //2、调用后台的业务逻辑
        int insertFlag = getService().insert(map);
        //3、处理返回结果
        Map resultMap = new HashMap();
        if(insertFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","新增成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","新增失败");
        }
        return  resultMap;
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public  Map  deleteOne(@RequestBody String id){//1、通过requestBody接受参数
        System.out.println("============================================进入到删除方法");
        //2、调用后台业务逻辑
        int delFlag = getService().deleteOne(id);
        //3、处理返回数据
        Map  resultMap  = new HashMap();
        if(delFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","删除成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","删除失败");
        }
        return  resultMap;
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public  Map  batchDelete(@RequestBody String ids){
        //1、通过requestBody接受参数
        List<String> idsList = new ArrayList<String>();
        //将传过来的string字符串处理成list
        for(String id : ids.split(",")){
            idsList.add(id);
        }
        System.out.println("============================================进入到批量删除方法"+ids);
        //2、调用后台业务逻辑
        int deleteFlag = getService().batchDelete(idsList);
        //3、处理返回数据
        Map  resultMap  = new HashMap();
        if(deleteFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","删除成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","删除失败");
        }
        return  resultMap;
    }

    @RequestMapping("editOne")
    @ResponseBody
    public Map editOne(@RequestBody Map params){
        int deleteFlag = getService().editRow(params);
        Map  resultMap  = new HashMap();
        if(deleteFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","修改成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","修改失败");
        }
        return  resultMap;
    }

}
