package com.oracat.dao;

import com.oracat.model.RealTime;
import com.oracat.util.DynamicDataSourceHolder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportRealTimeDao {




        @Select("select * from report_b2b_data_realtime where shengfen='����ʡ' AND chengshi=' �ϼ�' order by rq ")
        List<RealTime> selectRealTime();



        @Select("select " +
                "CASE WHEN chengshi='��������������' THEN '����'\n" +
                "       WHEN chengshi='�������������' THEN '����'\n" +
                "       WHEN chengshi='�º���徰����������' THEN '�º�'\n" +
                "       WHEN chengshi='�������������' THEN '����'\n" +
                "       WHEN chengshi='��ӹ���������������' THEN '���'\n" +
                "       WHEN chengshi='˼é����' THEN '�ն�'\n" +
                "       WHEN chengshi='��ɽ׳������������' THEN '��ɽ'\n" +
                "       WHEN chengshi='��˫���ɴ���������' THEN '����' \n" +
                " when chengshi='��ɽ��'     then   '��ɽ'\n" +
                " when chengshi='��ͨ��'     then   '��ͨ'\n" +
                " when chengshi='��ɽ��'     then   '��ɽ'\n" +
                " when chengshi='�ٲ���'     then   '�ٲ�'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�ն���'     then   '�ն�'\n" +
                " when chengshi='��˫������' then   '����'\n" +
                " when chengshi='��Ϫ��'     then   '��Ϫ'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�����'     then   '���'\n" +
                " when chengshi='ŭ����'     then   'ŭ��'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�º���'     then   '�º�'\n"+
                "  else   '����'  END  AS chengshi \n"+
                ",order_pay_price from report_b2b_data_realtime where shengfen='����ʡ' AND quyufl=' �ϼ�' AND chengshi<>' �ϼ�' order by rq ")
        List<RealTime> selectArea();


}
