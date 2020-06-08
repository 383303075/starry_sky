package controller;

import domain.Comment;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IBaseService;
import service.ICommentService;
import service.impl.BaseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    ICommentService commentService;

    @RequestMapping("a")
    public String getcom(){
        return "/X-admin/comment-checked";
    }

    @RequestMapping("addPage")
    public String addPage(){
        return "X-admin/comment-add";
    }



    @RequestMapping("editPage")
    public String member_edit(HttpServletRequest request, String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        String c_id = id;
        Comment comment = commentService.findById(c_id);
        System.out.println("======="+comment.toString());
        request.getSession().setAttribute("editCommentInfo",comment);
        return "X-admin/comment-edit";
    }

    @RequestMapping("getCommentInfo")
    @ResponseBody
    public Map getCommentInfo(HttpServletRequest request){
        Map map = new HashMap();
        Comment comment = (Comment)request.getSession().getAttribute("editCommentInfo");
        map.put("code","1");
        map.put("msg","");
        map.put("data",comment);
        System.out.println("====================================="+comment.toString());
        return map;
    }
    @RequestMapping("lookPage")
    public String getlook(){
        return "/X-admin/comment-look";
    }

    @Override
    public IBaseService getService() {
        return commentService;
    }
}
