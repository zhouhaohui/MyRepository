package per.zhh.utils;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签处理类      此类已经封装成jar包
 */
public class PagerTag extends TagSupport {

	private static final long serialVersionUID = 6882823777910064847L;

	private String url;         //请求URI  
    private int pageSize = 10;  //每页要显示的记录数  
    private int pageNo = 1;     //当前页号  
    private int recordCount;    //总记录数  
    
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		//拼写要输出到页面的HTML文本  
        StringBuilder sb = new StringBuilder();
        sb.append("<style type=\"text/css\">");  
        sb.append(".pagination {padding: 5px;float:right;font-size:12px;}");  
        sb.append(".pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}");  
        sb.append(".pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}");  
        sb.append(".pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #ff0000;font-weight: bold;background-color: #ff0000;color: #FFF;}");  
        sb.append(".pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}");  
        sb.append("</style>\r\n");  
        sb.append("<div class=\"pagination\">\r\n");
		//计算总页数
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		if(recordCount == 0){  
            sb.append("<strong>没有可显示的项目</strong>\r\n");  
        }else {
        	//页号越界处理  
        	if(pageNo<=1){
        		pageNo = 1;
  	      	}
        	if(pageNo > pageCount){
        		pageNo = pageCount; 
        	}
        	//首页处理
        	sb.append("<a href='"+url+"&pageNo="+1+"'>首页</a>");
        	//上一页处理开始
        	if (pageNo >1) {  
        		sb.append("<a href='"+url+"&pageNo="+(pageNo-1)+"'>上一页</a>");    
            }
        	//上一页处理结束
        	//中间处理开始
        	
        	 //如果前面页数过多,显示"..."  
            int start = 1;   
            if(this.pageNo > 4){  
                start = this.pageNo - 1;  
                sb.append("<a href='"+url+"&pageNo="+1+"'>1</a>\r\n");  
                sb.append("<a href='"+url+"&pageNo="+2+"'>2</a>\r\n");  
                sb.append("&hellip;\r\n");//&hellip表示省略号
            }  
            //显示当前页附近的页  
            int end = this.pageNo + 1;  
            if(end > pageCount){  
                end = pageCount;  
            }  
            for(int i = start; i <= end; i++){  
                if(pageNo == i){   //当前页号不需要超链接  
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
            //如果后面页数过多,显示"..."  
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
    	
        	//中间处理结束
        	//下一页处理开始
        	if (pageNo < pageCount) {  
        		sb.append("<a href='"+url+"&pageNo="+(pageNo+1)+"'>下一页</a>");    
            }
        	//下一页处理结束
        	//尾页处理
        	sb.append("<a href='"+url+"&pageNo="+pageCount+"'>尾页</a>&nbsp;&nbsp;");
        	//当前页
        	sb.append("当前页  "+pageNo+"&nbsp;&nbsp;");
        	//共有多少页
        	sb.append("总页数  "+pageCount+"&nbsp;&nbsp;");
        	//共有多少条记录
        	sb.append("总记录数  "+recordCount);
		}
		
		JspWriter out = this.pageContext.getOut();
        try {
            out.println(sb);
        } catch (IOException e) {
            System.out.println("输出标签时，出现异常");
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
