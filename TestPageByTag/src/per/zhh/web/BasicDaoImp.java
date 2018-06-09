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
		 * ��ȡdb.properties�ļ�
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
	 * ������ݿ����Ӷ���
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
	 * �ر�����
	 * @param conn ���ݿ�����
	 * @param stmt Statement����
	 * @param rs �����
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
	 * ��ɾ�Ĳ���
	 * @param sql ���ݿ�����
	 * @param param�������ַ�������
	 * @return Ӱ��ĺ���
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
	 * ��ѯ��ͨ�÷���
	 * @param sql sql���
	 * @param objects ����
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
			//��ȡ�еĸ���
			int columnCount = metaData.getColumnCount();
			//����rs
			while (rs.next()) {
				t=clazz.newInstance();
				//����ÿһ�е�ÿһ��, ��װ����
				for (int i=0; i<columnCount; i++) {
					// ��ȡÿһ�е�������
					String columnName=metaData.getColumnName(i+1);
					// ��ȡÿһ�ж�Ӧ����ֵ
					Object value=rs.getObject(columnName);
//					System.out.println(columnName);
//					System.out.println(value);
					// ��ȡ���������(����˽�ж���)ע�⣺ʵ�����е�˽��������Ҫ�����ݿ���е���������ȫһ��
	                Field field=clazz.getDeclaredField(columnName);
//	                System.out.println(field);
	                // �����������˽�����Ե�Ȩ��
	                field.setAccessible(true);
	                // ��˽�ж���ֵ
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
