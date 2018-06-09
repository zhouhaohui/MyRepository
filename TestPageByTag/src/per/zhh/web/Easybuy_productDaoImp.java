package per.zhh.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import per.zhh.utils.Pager;

public class Easybuy_productDaoImp extends BasicDaoImp {
	
	//方式2
	public List<Easybuy_productDomain> findAll(Pager pager) {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	List<Easybuy_productDomain> productsList=new ArrayList<Easybuy_productDomain>();
	try {
		int currentPage = pager.getPageNow();
		int index = (currentPage -1 ) * pager.getPageSize();
		String sql="select * from easybuy_product limit "+index+","+pager.getPageSize()+"";
	    rs = this.executeQuery(sql, null);
	    while(rs.next()){
	    	int id = rs.getInt("id");
	    	String name = rs.getString("name");
	    	String description = rs.getString("description");
	    	Double price= rs.getDouble("price");
	    	int stock = rs.getInt("stock");
	    	String fileName = rs.getString("fileName");
	    	String isDelete=rs.getString("isDelete");
	    	if("0".equals(isDelete)){
		    	Easybuy_productDomain product=new Easybuy_productDomain();
		    	product.setId(id);
		    	product.setName(name);
		    	product.setDescription(description);
		    	product.setPrice(price);
		    	product.setStock(stock);
		    	productsList.add(product);
	    	}
	    }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException("对不起！你查询的编号不存在");
	}
	return productsList;
}
	
	public int getCount() throws SQLException {
		String sql="select count(*) from easybuy_product";
	    rs = this.executeQuery(sql, null);
	    while(rs.next()){
	    	int count=rs.getInt(1);
	    	return count;
	    }
		return 0;
	}
}
