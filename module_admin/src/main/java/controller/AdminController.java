package controller;

import domain.Admin;
import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAdminService;
import service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController{
    @Autowired
    IAdminService adminService;

    @RequestMapping("/login")
    public String getLoginPage(){
        return "X-admin/login";
    }

    @RequestMapping("/index")
    public String getHomePage(){
        return "X-admin/index";
    }

    @RequestMapping("/member-list")
    public String getMemberList(){
        return "X-admin/member-list";
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public Object loginCheck(@RequestBody Map map, HttpServletRequest request){
        System.out.println("===============进入logincheck,"+map.toString());
        //获取传过来的账号密码
        String a_id = (String) map.get("adminid");
        String password = (String) map.get("password");

        Admin admin = adminService.findById(a_id);

        ResultInfo rs = new ResultInfo();

        if(admin==null){
            rs.setCode("000");
            rs.setMsg("登录失败，账号不存在");
            return rs;
        } else if(password.equals(admin.getA_password())){
            System.out.println("登录成功");
            rs.setCode("666");
            rs.setMsg("登录成功");
            rs.setObject(admin);
            request.getSession().setAttribute("admin",admin);
            return rs;
        }else{
            rs.setCode("000");
            rs.setMsg("登录失败，密码错误");
            return rs;
        }
    }

    @RequestMapping("logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        ResultInfo rs = new ResultInfo();
        rs.setCode("333");
        rs.setMsg("退出成功");
        return rs;
    }

//    @RequestMapping("/getAdminInfo")
//    public String getAdminInfo(){
//
//        return "";
//    }

    @Override
    public IBaseService getService() {
        return adminService;
    }
}
