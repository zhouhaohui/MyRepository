package per.zhh.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public  class BasicDaoImp{
	
	private String driver;
	private String url;
	private String username;
	private String password;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BasicDaoImp(){
		/**
		 * 读取db.properties文件
		 */
		try {
			InputStream is = BasicDaoImp.class.getResourceAsStream("/database.properties");
			Properties properties=new Properties();
			properties.load(is);
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			username=properties.getProperty("username");
			password=properties.getProperty("password");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库连接对象
	 * 
	 */
	public Connection getConnection(){
		if(conn==null){		
			try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 关闭连接
	 * @param conn 数据库连接
	 * @param stmt Statement对象
	 * @param rs 结果集
	 */
	public void closeAll(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
				stmt=null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	/**
	 * 增删改操作
	 * @param sql 数据库连接
	 * @param param参数的字符串数组
	 * @return 影响的函数
	 */
	public int executeUpdate(String preparedSql,Object[] param){
		
		int num=0;
		conn=this.getConnection();
		try {
			pstmt=conn.prepareStatement(preparedSql);
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		//	closeAll(conn, pstmt, rs);
			this.close(pstmt, rs);
		}
		return num;
	}
	/**
	 * 查询的通用方法
	 * @param sql sql语句
	 * @param objects 数组
	 */
	public <T> List<T> executeQuery(String sql, Object[] objects, Class<T> clazz) {
		// TODO Auto-generated method stub
		List<T> list = new ArrayList<T>();
		try {
			T t=null;
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i=0; i<objects.length; i++) {
					pstmt.setObject(i+1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			//获取列的个数
			int columnCount = metaData.getColumnCount();
			//遍历rs
			while (rs.next()) {
				t=clazz.newInstance();
				//遍历每一行的每一列, 封装数据
				for (int i=0; i<columnCount; i++) {
					// 获取每一列的列名称
					String columnName=metaData.getColumnName(i+1);
					// 获取每一行对应的列值
					Object value=rs.getObject(columnName);
//					System.out.println(columnName);
//					System.out.println(value);
					// 获取对象的属性(包括私有对象)注意：实体类中的私有属性名要和数据库表中的属性名完全一致
	                Field field=clazz.getDeclaredField(columnName);
//	                System.out.println(field);
	                // 赋予操作对象私有属性的权限
	                field.setAccessible(true);
	                // 给私有对象赋值
	                field.set(t,value);
//	                System.out.println(t);
				}
				list.add(t);
//				System.out.println(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		//	this.closeAll(conn,pstmt, rs);
			this.close(pstmt, rs);
		}
		return list;
	}
	
	public void close(Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
				stmt=null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public ResultSet executeQueryUnique(String sql, Object[] objects) {
		// TODO Auto-generated method stub
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i=0; i<objects.length; i++) {
					pstmt.setObject(i+1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public ResultSet executeQuery(String sql, Object[] objects){
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i=0; i<objects.length; i++) {
					pstmt.setObject(i+1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
}
