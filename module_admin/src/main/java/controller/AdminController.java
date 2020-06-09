package controller;

import domain.Admin;
import domain.ResultInfo;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAdminService;
import service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @RequestMapping("/video-checked")
    public String getVideoChecked(){return "X-admin/video-checked";}

    @RequestMapping("/comment-checked")
    public String getCommentChecked(){return "X-admin/comment-checked";}

    @RequestMapping("/b")
    public String getreply(){
        return "X-admin/reply-checked";
    }

    @RequestMapping("/video-uncheck")
    public String getVideoUncheck(){return "X-admin/video-uncheck";}

    @RequestMapping("/admin-list")
    public String getAdminList(){return "X-admin/admin-list";}

    @RequestMapping("/changeName")
    public String getEditNamePage(){
        return "X-admin/update_aname";
    }

    @RequestMapping("/changePwd")
    public String getEditPwdPage(){
        return "X-admin/update_apwd";
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
            System.out.println(admin.toString());
            request.getSession().setAttribute("LoginAdmin",admin);
            return rs;
        }else{
            rs.setCode("000");
            rs.setMsg("登录失败，密码错误");
            return rs;
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        ResultInfo rs = new ResultInfo();
        rs.setCode("333");
        rs.setMsg("退出成功");
        return rs;
    }


    @RequestMapping("/getAdminInfo")
    @ResponseBody
    public Admin getAdminInfo(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        admin.setA_password(null);
        return admin;
    }

    @RequestMapping("updateSession")
    @ResponseBody
    public void updateSession(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        String a_id = admin.getA_id();
        Admin newAdmin = adminService.findById(a_id);
        request.getSession().removeAttribute("LoginAdmin");
        request.getSession().setAttribute("LoginAdmin",newAdmin);
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public Map editPassword(@RequestBody Map params,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        System.out.println(admin.toString());
        String a_id = admin.getA_id();
        String param_id = (String) params.get("a_id");
        String old_pwd = (String) params.get("old_password");

        Map resultMap = new HashMap();

        if(!a_id.equals(param_id)) {
            resultMap.put("code", "0");
            resultMap.put("msg", "登录账户与修改账户不一致！请清除缓存后重试~");
            return resultMap;
        }

        if(!admin.getA_password().equals(old_pwd)){
            resultMap.put("code", "0");
            resultMap.put("msg", "密码错误");
            return resultMap;
        }

        int flag = adminService.editRow(params);

        if(flag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","修改成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","修改失败");
        }
        return resultMap;
    }

    @Override
    public IBaseService getService() {
        return adminService;
    }
}
