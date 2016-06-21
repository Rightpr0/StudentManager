package grade;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.GradeManager;
public class Gupd extends Dialog implements ActionListener{
	private Panel p1,p2,p3;
    private Label lab1,lab2,lab3,lab4,lab5,lab6;
    private TextField t1,t2,t3,t4;
    private Button btn1,btn2;
    public Gupd(Frame f,String title,boolean mo,GradeManager sm, int rowNo){
    	super(f,title,mo);
    	p1=new Panel();
		p1.setLayout(new GridLayout(4,1));
		lab1=new Label("学号");
		lab2=new Label("课程号");
		lab3=new Label("成绩");
		lab4=new Label("学期");
		p1.add(lab1);
		p1.add(lab2);
		p1.add(lab3);
		p1.add(lab4);
		this.add(p1,BorderLayout.WEST);
		p2=new Panel();
		p2.setLayout(new GridLayout(4,1));
		t1=new TextField((String)sm.getValueAt(rowNo,0));
		t1.setEditable(false);
		t2=new TextField((String)sm.getValueAt(rowNo,1));
		t3=new TextField((String)sm.getValueAt(rowNo,2));
		t4=new TextField(sm.getValueAt(rowNo,3).toString());
		p2.add(t1);
		p2.add(t2);
		p2.add(t3);
		p2.add(t4);
		this.add(p2,BorderLayout.CENTER);
		p3=new Panel();
		btn1=new Button("确认修改");
		btn2=new Button("取消");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		p3.add(btn1);
		p3.add(btn2);
		this.add(p3,BorderLayout.SOUTH);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim=tk.getScreenSize();
		this.setBounds(300, 400, 200, 200);
		this.setVisible(true);
    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1){
			String sql="update grade set gmark=?,gstate=?where gsid=? and gcid=?";
			String[] param=new String []{t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),t4.getText().trim()};
			GradeManager sm2=new GradeManager();
			sm2.updateGrade(sql, param);
			this.dispose();
		}else if(e.getSource()==btn2){
			this.dispose();
	}
  }	
}
