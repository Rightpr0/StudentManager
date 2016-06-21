package dao;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
public class GradeManager extends AbstractTableModel{
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
    public void addGrade(String sql,String[] param){
   	 colu =new Vector();
   	 colu.add("学号");
   	 colu.add("课程号");
   	 colu.add("成绩");
   	 colu.add("学期");
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
   			 rows.add(row);
   		 }
   	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 base.closeAll(null, null, null);
	 }
 }
 public boolean updateGrade(String sql,String [] param){
	 base=new BaseDao();
	 return base.executeSQL(sql, param);
   }
}
