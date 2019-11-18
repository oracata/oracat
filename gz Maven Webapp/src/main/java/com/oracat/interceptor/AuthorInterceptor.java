package com.oracat.interceptor;

import com.oracat.model.User;
import com.oracat.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * �ж��û�Ȩ�޵�Spring MVC��������
 */
public class AuthorInterceptor  implements HandlerInterceptor {

    /** ���岻��Ҫ���ص����� */
    private static final String[] IGNORE_URI = {"/loginform", "/loginform.do","/404.html"};

    /**
     * �÷�����ҪpreHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С�
     * �÷������������������֮��ִ�У���Ҫ����������������Դ��
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
      //  System.out.println("**********����֮��**********");
    }

    /**
     * ���������preHandle��������ֵΪtrue��ʱ��Ż�ִ�С�
     * ִ��ʱ�����ڴ��������д���֮ ��Ҳ������Controller�ķ�������֮��ִ�С�
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
       // System.out.println("**********����post**********");
    }

    /**
     * preHandle�����ǽ��д����������õģ��÷�������Controller����֮ǰ���е��ã�
     * ��preHandle�ķ���ֵΪfalse��ʱ����������ͽ����ˡ�
     * ���preHandle�ķ���ֵΪtrue��������ִ��postHandle��afterCompletion��
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("**********��ʼ����**********");

        /** Ĭ���û�û�е�¼ */
        boolean flag = false;
        /** ��������ServletPath */
        String servletPath = request.getServletPath();
        /**  �ж������Ƿ���Ҫ���� */
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        /** �������� */
        if (!flag){
            /** 1.��ȡsession�е��û�  */
            User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
            /** 2.�ж��û��Ƿ��Ѿ���¼ */
            if(user == null){
                /** ����û�û�е�¼����ת����¼ҳ�� */
                request.setAttribute("message", "���ȵ�¼�ٷ�����վ!");
                //request.getRequestDispatcherֻ����frame��ҳ������ת
               // request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<script>");
                out.println("window.open ('"+request.getContextPath()+"/loginform.do','_top')");
                out.println("</script>");
                out.println("</html>");

                return flag;
            }else{
                flag = true;
            }
        }
        return flag;

    }

}
