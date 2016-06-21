package dao;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class StudentManager extends AbstractTableModel{
	     private BaseDao base;
	     private ResultSet rs=null;
	     private Vector rows,colu;
	     public int getRowCount() {
				return this.rows.size();
			}
			public Object getValueAt(int rowIndex, int columnIndex) {
				 return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
			}
	     public int getColumnCount(){
	    	 return this.colu.size();
	     }
	     public String  getColu(int column){
	    	 return (String)this.colu.get(column);
	     }
		public void addStudent(String sql,String[] param){
	    	 colu =new Vector();
	    	 colu.add("学号");
	    	 colu.add("姓名");
	    	 colu.add("性别");
	    	 colu.add("年龄");
	    	 colu.add("生日");
	    	 colu.add("电话");
	    	 rows =new Vector();
	    	 try{
	    		 base =new BaseDao();
	    		 rs=base.result(sql, param);
	    		 rows.add(colu);
	    		 while(rs.next()){
	    			 Vector row=new Vector();
	    			 row.add(rs.getString(1));
	    			 row.add(rs.getString(2));
	    			 row.add(rs.getString(3));
	    			 row.add(rs.getInt(4));
	    			 row.add(rs.getString(5));
	    			 row.add(rs.getString(6));
	    			 rows.add(row);
	    		 }
	    	 }catch(Exception e){
	    		 e.printStackTrace();
	    	 }finally{
	    		 base.closeAll(null, null, null);
	    	 }
	     }
	     public boolean updateStu(String sql,String [] param){
	    	 base=new BaseDao();
	    	 return base.executeSQL(sql, param);
	     }
}

