package course;
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


import dao.CourseManager;
import dao.StudentManager;

public class Cadd extends Dialog implements ActionListener{
    private Panel p1,p2,p3;
    private Label lab1,lab2,lab3,lab4;
    private TextField t1,t2,t3,t4;
    private Button btn1,btn2;
    public Cadd(Frame f,String title,boolean mo){
   	 super(f,title,mo);
   		p1=new Panel();
   		p1.setLayout(new GridLayout(4,1));
   		lab1=new Label("课程号");
   		lab2=new Label("课程名称");
   		lab3=new Label("课程类型");
   		lab4=new Label("课程内容");
   		p1.add(lab1);
		p1.add(lab2);
		p1.add(lab3);
		p1.add(lab4);
		this.add(p1,BorderLayout.WEST);
		p2=new Panel();
		p2.setLayout(new GridLayout(4,1));
		t1=new TextField();
		t2=new TextField();
		t3=new TextField();
		t4=new TextField();
		p2.add(t1);
		p2.add(t2);
		p2.add(t3);
		p2.add(t4);
		this.add(p2,BorderLayout.CENTER);
		p3=new Panel();
		btn1=new Button("确定");
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
			CourseManager sm=new CourseManager();
			String sql="insert course values(?,?,?,?)";
			String[] param=new String []{t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),t4.getText().trim()};
			sm.addCourse(sql, param);
			this.dispose();
		}else if(e.getSource()==btn2){
			this.dispose();
		}
	 }		
  }
