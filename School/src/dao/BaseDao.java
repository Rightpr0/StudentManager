package dao;
import java.sql.*;
public class BaseDao {   
	// ���ݿ�����
    public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    // url
    public final static String URL    = "jdbc:sqlserver://10.100.33.2:1433;DataBaseName=school";  
    // ���ݿ��û���
    public final static String DBNAME = "sa";  
    // ���ݿ�����
    public final static String DBPASS = "12345";                                                   
      // �õ����ݿ�����
    public Connection getConn() throws ClassNotFoundException, SQLException{
    	//ע������
        Class.forName(DRIVER);                                           
        //������ݿ�����
        Connection conn = DriverManager.getConnection(URL,DBNAME,DBPASS);  
        //��������
        return conn ;                                                           
    }
    //�ͷ���Դ
    public void closeAll( Connection conn, PreparedStatement ps, ResultSet rs ) {
        /*  ���rs���գ��ر�rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���pstmt���գ��ر�pstmt  */
        if(ps != null){
            try { ps.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���conn���գ��ر�conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
     //ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ
    public boolean executeSQL(String sql,String[] param) {
        Connection        conn  = null;
        PreparedStatement ps = null;
        int               num=0;
        boolean          ban   = true;
        
        /*  ����SQL,ִ��SQL  */
        try {
            conn = getConn();// �õ����ݿ�����
            ps = conn.prepareStatement(sql);// �õ�PreparedStatement����
            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                    ps.setString(i+1, param[i]);// ΪԤ����sql���ò���
                }
            }
            num = ps.executeUpdate();// ִ��SQL���
            if(num!=1){
            	ban=false;
            }else{
            	ban=true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();// ����ClassNotFoundException�쳣
        } catch (SQLException e) {
            e.printStackTrace();// ����SQLException�쳣
        } finally {
            closeAll(conn,ps,null);// �ͷ���Դ
        }
        return ban;
    }
   public ResultSet result(String sql,String[] param){
	   Connection     conn  = null;
       PreparedStatement ps = null;
       ResultSet        rs=null;
	    try{  
		    conn = getConn();// �õ����ݿ�����
            ps = conn.prepareStatement(sql);// �õ�PreparedStatement����
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
