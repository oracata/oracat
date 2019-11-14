package com.oracat.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ToJson {


    /**
     * �������ܣ�������ת����JSON�ַ���������Ӧ��ǰ̨
     * ������object,request,response
     * ����ֵ��void
     * �쳣��IOException
     */
    public static void toJson(Object object, HttpServletRequest request, HttpServletResponse response) {

        //Object ���Ϳ��Խ���list���ϣ�����Ҳ��һ�����󣬶���ӵ�����ͣ�Object���������͵ĸ��ࡣ
        Object obj=new ArrayList();

        /*PrintWriter��ӡ�ַ��������ڽ�����java����һ�ַ�������ʽ��ӡ���ײ��ַ��������
         * */
        PrintWriter out = null;
        try {
            //JSON.toJSONStringWithDateFormat�������л���Ϣ��
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");

            /*response.setContentType(MIME)��������ʹ�ͻ�������������ֲ�ͬ��������ݣ������ݲ�ͬ��MIME�������
             *���ڲ�ͬ�ĳ���Ƕ��ģ����������Ӧ�����ݡ�����web���������ͨ��MIME�������ж��ļ���GIFͼƬ��ͨ��MIME��������
             *��json�ַ�����
             * */
            response.setContentType("text/html;charset=utf-8");
            //�õ�һ����ӡ�ַ������
            out = response.getWriter();
            //[{"areaId":1,"areaName":"��ƽ��"},{"areaId":2,"areaName":"������"},{"areaId":3,"areaName":"�����"}]
            System.out.println(json);
            //Ҫͨ���ַ����������ǰ̨�������
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
