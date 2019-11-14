package com.oracat.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ToJson {


    /**
     * 方法功能：将对象转换成JSON字符串，并响应回前台
     * 参数：object,request,response
     * 返回值：void
     * 异常：IOException
     */
    public static void toJson(Object object, HttpServletRequest request, HttpServletResponse response) {

        //Object 类型可以接收list集合，集合也是一个对象，对象拥有类型，Object是所有类型的父类。
        Object obj=new ArrayList();

        /*PrintWriter打印字符流、用于将各种java数据一字符串的形式打印到底层字符输出流中
         * */
        PrintWriter out = null;
        try {
            //JSON.toJSONStringWithDateFormat用于序列化信息。
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");

            /*response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览
             *器内不同的程序嵌入模块来处理相应的数据。例如web浏览器就是通过MIME类型来判断文件是GIF图片。通过MIME类型来处
             *理json字符串。
             * */
            response.setContentType("text/html;charset=utf-8");
            //得到一个打印字符输出流
            out = response.getWriter();
            //[{"areaId":1,"areaName":"和平区"},{"areaId":2,"areaName":"铁西区"},{"areaId":3,"areaName":"沈河区"}]
            System.out.println(json);
            //要通过字符输出流来向前台输出数据
            out.write(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
