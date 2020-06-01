package controller;

import domain.Admin;
import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IAdminService;
import service.IBaseService;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public String member_edit(HttpServletRequest request,String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        String u_id = id;
        User user = userService.findById(id);
        request.getSession().setAttribute("editUserInfo",user);
        return "X-admin/member-edit";
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public Map getUserInfo(HttpServletRequest request){
        Map map = new HashMap();
        User user = (User) request.getSession().getAttribute("editUserInfo");
        map.put("code","1");
        map.put("msg","");
        user.setU_password(null);
        map.put("data",user);
        System.out.println("-=-=-=---=-=-=-="+user.toString());
        return map;
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
