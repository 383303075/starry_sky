package interceptor;

import domain.Admin;
import domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        System.out.println("=====================================================preHandler");
        //获得请求路径
        String uri = request.getRequestURI();
        System.out.println("该次请求路径为："+uri);
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        //判断是否为主页请求并且是否已经登录，否则跳转到登录页面
        if(admin == null && uri.equals("/starry_admin/admin/index")){
            System.out.println("=========session中的admin："+admin);
            System.out.println("用户未登录，将跳转到登录页面");
            response.sendRedirect(request.getContextPath()+"/admin/login");
        }
        System.out.println("======================================================preHandler结束");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("======================================================postHandler");
        System.out.println("======================================================postHandler结束");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("=====================================================afterCompletion");
        System.out.println("=====================================================afterCompletion结束");
    }
}
