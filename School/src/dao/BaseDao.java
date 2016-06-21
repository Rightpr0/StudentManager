package dao;
import java.sql.*;
public class BaseDao {   
	// 数据库驱动
    public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    // url
    public final static String URL    = "jdbc:sqlserver://10.100.33.2:1433;DataBaseName=school";  
    // 数据库用户名
    public final static String DBNAME = "sa";  
    // 数据库密码
    public final static String DBPASS = "12345";                                                   
      // 得到数据库连接
    public Connection getConn() throws ClassNotFoundException, SQLException{
    	//注册驱动
        Class.forName(DRIVER);                                           
        //获得数据库连接
        Connection conn = DriverManager.getConnection(URL,DBNAME,DBPASS);  
        //返回连接
        return conn ;                                                           
    }
    //释放资源
    public void closeAll( Connection conn, PreparedStatement ps, ResultSet rs ) {
        /*  如果rs不空，关闭rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果pstmt不空，关闭pstmt  */
        if(ps != null){
            try { ps.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果conn不空，关闭conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
     //执行SQL语句，可以进行增、删、改的操作，不能执行查询
    public boolean executeSQL(String sql,String[] param) {
        Connection        conn  = null;
        PreparedStatement ps = null;
        int               num=0;
        boolean          ban   = true;
        
        /*  处理SQL,执行SQL  */
        try {
            conn = getConn();// 得到数据库连接
            ps = conn.prepareStatement(sql);// 得到PreparedStatement对象
            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                    ps.setString(i+1, param[i]);// 为预编译sql设置参数
                }
            }
            num = ps.executeUpdate();// 执行SQL语句
            if(num!=1){
            	ban=false;
            }else{
            	ban=true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();// 处理ClassNotFoundException异常
        } catch (SQLException e) {
            e.printStackTrace();// 处理SQLException异常
        } finally {
            closeAll(conn,ps,null);// 释放资源
        }
        return ban;
    }
   public ResultSet result(String sql,String[] param){
	   Connection     conn  = null;
       PreparedStatement ps = null;
       ResultSet        rs=null;
	    try{  
		    conn = getConn();// 得到数据库连接
            ps = conn.prepareStatement(sql);// 得到PreparedStatement对象
            for(int i=0;i<param.length;i++){
            	ps.setString(i+1, param[i]);
            }
            rs=ps.executeQuery();
	  }catch(Exception e){
		  e.printStackTrace();
	  }finally{
	  }
	  return rs;
    }
}
