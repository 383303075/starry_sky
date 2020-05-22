package controller;

import domain.Admin;
import domain.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAdminService;
import service.IBaseService;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
    @Autowired
    IUserService userService;

    @RequestMapping("addPage")
    public String addPage(){
        return "X-admin/member-add";
    }

    @RequestMapping("editPage")
    public String member_edit(String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        return "X-admin/member-edit";
    }

//    @RequestMapping("/getAdminInfo")
//    public String getAdminInfo(){
//
//        return "";
//    }

    @Override
    public IBaseService getService() {
        return userService;
    }
}
