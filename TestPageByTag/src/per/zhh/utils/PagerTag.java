package per.zhh.utils;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * ��ҳ��ǩ������      �����Ѿ���װ��jar��
 */
public class PagerTag extends TagSupport {

	private static final long serialVersionUID = 6882823777910064847L;

	private String url;         //����URI  
    private int pageSize = 10;  //ÿҳҪ��ʾ�ļ�¼��  
    private int pageNo = 1;     //��ǰҳ��  
    private int recordCount;    //�ܼ�¼��  
    
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		//ƴдҪ�����ҳ���HTML�ı�  
        StringBuilder sb = new StringBuilder();
        sb.append("<style type=\"text/css\">");  
        sb.append(".pagination {padding: 5px;float:right;font-size:12px;}");  
        sb.append(".pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}");  
        sb.append(".pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}");  
        sb.append(".pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #ff0000;font-weight: bold;background-color: #ff0000;color: #FFF;}");  
        sb.append(".pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}");  
        sb.append("</style>\r\n");  
        sb.append("<div class=\"pagination\">\r\n");
		//������ҳ��
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		if(recordCount == 0){  
            sb.append("<strong>û�п���ʾ����Ŀ</strong>\r\n");  
        }else {
        	//ҳ��Խ�紦��  
        	if(pageNo<=1){
        		pageNo = 1;
  	      	}
        	if(pageNo > pageCount){
        		pageNo = pageCount; 
        	}
        	//��ҳ����
        	sb.append("<a href='"+url+"&pageNo="+1+"'>��ҳ</a>");
        	//��һҳ����ʼ
        	if (pageNo >1) {  
        		sb.append("<a href='"+url+"&pageNo="+(pageNo-1)+"'>��һҳ</a>");    
            }
        	//��һҳ�������
        	//�м䴦��ʼ
        	
        	 //���ǰ��ҳ������,��ʾ"..."  
            int start = 1;   
            if(this.pageNo > 4){  
                start = this.pageNo - 1;  
                sb.append("<a href='"+url+"&pageNo="+1+"'>1</a>\r\n");  
                sb.append("<a href='"+url+"&pageNo="+2+"'>2</a>\r\n");  
                sb.append("&hellip;\r\n");//&hellip��ʾʡ�Ժ�
            }  
            //��ʾ��ǰҳ������ҳ  
            int end = this.pageNo + 1;  
            if(end > pageCount){  
                end = pageCount;  
            }  
            for(int i = start; i <= end; i++){  
                if(pageNo == i){   //��ǰҳ�Ų���Ҫ������  
                    sb.append("<span class=\"current\">")  
                        .append(i)  
                        .append("</span>\r\n");  
                }else{  
                    sb.append("<a href='"+url+"&pageNo=")  
                        .append(i)  
                        .append("'>")  
                        .append(i)  
                        .append("</a>\r\n");  
                }  
            }  
            //�������ҳ������,��ʾ"..."  
            if(end < pageCount - 2){  
                sb.append("&hellip;\r\n");  
            }  
            if(end < pageCount - 1){  
                sb.append("<a href='"+url+"&pageNo=")  
                .append(pageCount - 1)  
                .append("'>")  
                .append(pageCount - 1)  
                .append("</a>\r\n");  
            }  
            if(end < pageCount){  
                sb.append("<a href='"+url+"&pageNo=")  
                .append(pageCount)  
                .append("'>")  
                .append(pageCount)  
                .append("</a>\r\n");   
            }  
    	
        	//�м䴦�����
        	//��һҳ����ʼ
        	if (pageNo < pageCount) {  
        		sb.append("<a href='"+url+"&pageNo="+(pageNo+1)+"'>��һҳ</a>");    
            }
        	//��һҳ�������
        	//βҳ����
        	sb.append("<a href='"+url+"&pageNo="+pageCount+"'>βҳ</a>&nbsp;&nbsp;");
        	//��ǰҳ
        	sb.append("��ǰҳ  "+pageNo+"&nbsp;&nbsp;");
        	//���ж���ҳ
        	sb.append("��ҳ��  "+pageCount+"&nbsp;&nbsp;");
        	//���ж�������¼
        	sb.append("�ܼ�¼��  "+recordCount);
		}
		
		JspWriter out = this.pageContext.getOut();
        try {
            out.println(sb);
        } catch (IOException e) {
            System.out.println("�����ǩʱ�������쳣");
            e.printStackTrace();
        }
        
		return this.EVAL_PAGE;
	}
	

	public void setUrl(String url) {
		this.url = url;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
