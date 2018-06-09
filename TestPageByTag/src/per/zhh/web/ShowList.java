package per.zhh.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.zhh.utils.Pager;

public class ShowList extends HttpServlet {
	Easybuy_productDaoImp easybuyProductDaoImp=new Easybuy_productDaoImp();	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		
		show(request, response);
		
	}

	private void show(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String s_pageNo = request.getParameter("pageNo");
//		System.out.println(s_pageNo);
		if(s_pageNo==null){
			s_pageNo="1";
		}else {
		}
		int totalCount =0;
		try {
			totalCount = easybuyProductDaoImp.getCount();//38
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//方式2
		Pager pager=new Pager(totalCount, 5, Integer.valueOf(s_pageNo));
//		System.out.println(pager.getPageCount()+"ppppp");//8
//		pager.setUrl("/ShowList");//不在这设置url，在jsp页面设置
		List<Easybuy_productDomain> findAll = easybuyProductDaoImp.findAll(pager);
		request.setAttribute("findAll", findAll);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("showlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
