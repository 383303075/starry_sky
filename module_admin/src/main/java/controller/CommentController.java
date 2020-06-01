package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IBaseService;
import service.ICommentService;
import service.impl.BaseServiceImpl;
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
    public String member_edit(String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        return "X-admin/comment-edit";
    }

    @Override
    public IBaseService getService() {
        return commentService;
    }
}
