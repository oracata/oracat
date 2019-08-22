package com.oracat.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * ��ҳ��ǩ
 */
public class PagerTag extends SimpleTagSupport {
	
	/** ��������URL�е�ռλ������ */
	private static final String TAG = "{0}";
	
	/** ��ǰҳ�� */
	private int pageIndex;
	/** ÿҳ��ʾ������ */
	private int pageSize;
	/** �ܼ�¼���� */
	private int recordCount;
	/** ����URL page.action?pageIndex={0}*/
	private String submitUrl;
	/** ��ʽ */
	private String style = "sabrosus";
	
	/** ������ҳ�� */
	private int totalPage = 0;
	
	/**  ��ҳ���������Զ����ǩ�ͻᴥ��һ����ǩ������   */
	@Override
	public void doTag() throws JspException, IOException {
		/** ������ƴ�����յĽ�� */
		StringBuilder res = new StringBuilder();
		/** ������ƴ���м��ҳ�� */
		StringBuilder str = new StringBuilder();
		/** �ж��ܼ�¼���� */
		if (recordCount > 0){   //1499 / 15  = 100
			/** ��Ҫ��ʾ��ҳ��ǩ���������ҳ�� ��Ҫ�ֶ���ҳ */
			totalPage = (this.recordCount - 1) / this.pageSize + 1; 
			
			/** �ж���һҳ����һҳ�費��Ҫ��a��ǩ */
			if (this.pageIndex == 1){ // ��ҳ
				str.append("<span class='disabled'>��һҳ</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				
				/** �����м��ҳ�� */
				this.calcPage(str);
				
				/** ��һҳ�費��Ҫa��ǩ */
				if (this.pageIndex == totalPage){
					/** ֻ��һҳ */
					str.append("<span class='disabled'>��һҳ</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}else{
			
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					System.out.println("******url:"+tempUrl);
					//str.append("<a   href='"+ tempUrl +"'>��һҳ</a>");
					str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+String.valueOf(pageIndex + 1)+"';form.submit();\">��һҳ</a>");
				}
			}else if (this.pageIndex == totalPage){ // βҳ
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				//str.append("<a href='"+ tempUrl +"'>��һҳ</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+String.valueOf(pageIndex - 1)+"';form.submit();\">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				/** �����м��ҳ�� */
				this.calcPage(str);
				
				str.append("<span class='disabled'>��һҳ</span>");
			}else{ // �м�
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
			//	str.append("<a href='"+ tempUrl +"'>��һҳ</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+String.valueOf(pageIndex - 1)+"';form.submit();\">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				
				/** �����м��ҳ�� */
				this.calcPage(str);
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				//str.append("<a   href='"+ tempUrl +"'>��һҳ</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+String.valueOf(pageIndex + 1)+"';form.submit();\">��һҳ</a>");
			}
			
			/** ƴ����������Ϣ */
			res.append("<table width='100%' align='center' style='font-size:13px;' class='"+ style +"'>");
			res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
			res.append("&nbsp;��ת��&nbsp;&nbsp;<input  type='text' size='2' id='pager_jump_page_size'/>");
			res.append("&nbsp;<input type='button' value='ȷ��' id='pager_jump_btn'/>");
			res.append("</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>");
			/** ��ʼ���� */
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			/** �������� */
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;
			
			res.append("�ܹ�<font color='red'>"+ this.recordCount +"</font>����¼����ǰ��ʾ"+ startNum +"-"+ endNum +"����¼��");
			res.append("</td></tr>");
			res.append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("   document.getElementById('pager_jump_btn').onclick = function(){");
			res.append("      var page_size = document.getElementById('pager_jump_page_size').value;");
			res.append("      if (!/^[1-9]\\d*$/.test(page_size) || page_size < 1 || page_size > "+ this.totalPage +"){");
			res.append("          alert('������[1-"+ this.totalPage +"]֮���ҳ�룡');");
			res.append("      }else{");
		//	res.append("         var submit_url = '" + this.submitUrl + "';");
		//	res.append("         window.location = submit_url.replace('"+ TAG +"', page_size);");
			res.append("       alert(page_size);");
			res.append("        var form = document.getElementById('form'); ");
			res.append("       form.action='"+this.submitUrl+"=page_size';");
			res.append("       form.submit();");


			res.append("      }");
			res.append("}");
			res.append("</script>");
			System.out.println(res);
			
		}else{
			res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>�ܹ�<font color='red'>0</font>����¼����ǰ��ʾ0-0����¼��</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}
	
	
	/** �����м�ҳ��ķ��� */
	private void calcPage(StringBuilder str) {
		/** �ж���ҳ�� */
		if (this.totalPage <= 11){
			/** һ������ʾȫ����ҳ�� */
			for (int i = 1; i <= this.totalPage; i++){
				if (this.pageIndex == i){
					/** ��ǰҳ�� */
					str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					
					//str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+"+i+"';form.submit();\">"+i+"</a>");	
					
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
				//	str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+i+"';form.submit();\">"+i+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");		
					
				}
			}
		}else{
			/** ������ҳ */
			if (this.pageIndex <= 8){
				for (int i = 1; i <= 10; i++){
					if (this.pageIndex == i){
						/** ��ǰҳ�� */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					//	str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+"+i+"';form.submit();\">"+i+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");		
					}else{
						String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						//str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
						str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+i+"';form.submit();\">"+i+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");	
					}
				}
				str.append("...&nbsp;&nbsp;&nbsp;&nbsp;");
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				//str.append("<a href='"+ tempUrl +"'>"+ this.totalPage +"</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+this.totalPage+"';form.submit();\">"+this.totalPage+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");	
			}
			/** ����βҳ */
			else if (this.pageIndex + 8 >= this.totalPage){
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				//str.append("<a href='"+ tempUrl +"'>1</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+"1';form.submit();\">1</a>&nbsp;&nbsp;&nbsp;&nbsp;");	
				str.append("...");
				
				for (int i = this.totalPage - 10; i <= this.totalPage; i++){
					if (this.pageIndex == i){
						/** ��ǰҳ�� */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
						str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+i+"';form.submit();\">"+i+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");		
						
					}
				}
			}
			/** ���м� */
			else{
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
			//	str.append("<a href='"+ tempUrl +"'>1</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+"1';form.submit();\">1</a>&nbsp;&nbsp;&nbsp;&nbsp;");	
				str.append("...");
				
				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++){
					if (this.pageIndex == i){
						/** ��ǰҳ�� */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					//	str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
						str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+i+"';form.submit();\">"+i+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");		
						
						
					}
				}
				
				str.append("...");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				//str.append("<a href='"+ tempUrl +"'>"+ this.totalPage +"</a>");
				str.append("<a  href=\"javascript:void(0);\" onclick=\"javascript:var form = document.getElementById('form'); form.action='"+this.submitUrl+"="+""+this.totalPage+"';form.submit();\">"+this.totalPage+"</a>&nbsp;&nbsp;&nbsp;&nbsp;");	
				
			}
		}
	}

	/** setter ���� */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}