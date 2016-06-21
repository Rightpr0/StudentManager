package dao;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
public class CourseManager extends AbstractTableModel{
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
    public void addCourse(String sql,String[] param){
   	 colu =new Vector();
   	 colu.add("课程号");
   	 colu.add("课程名称");
   	 colu.add("课程类型");
   	 colu.add("课程内容");
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
   			 row.add(rs.getString(4));
   			 rows.add(row);
   		 }
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }finally{
   		 base.closeAll(null, null, null);
   	 }
    }
    public boolean updateCourse(String sql,String [] param){
   	 base=new BaseDao();
   	 return base.executeSQL(sql, param);
    }
}
