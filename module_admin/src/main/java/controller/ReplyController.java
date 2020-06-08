package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IBaseService;
import service.IReplyService;

@Controller
@RequestMapping("reply")
public class ReplyController extends BaseController {
    @Autowired
    IReplyService replyService;
    @RequestMapping("b")
    public String getreply(){
        return "/X-admin/reply-checked";
    }

    @RequestMapping("lookPage")
    public String lookPage(){return "X-admin/reply-look";}

    public IBaseService getService() {
        return replyService;
    }
}
