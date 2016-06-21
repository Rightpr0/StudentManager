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
import dao.StudentManager;
public class Gadd extends Dialog implements ActionListener{
    private Panel p1,p2,p3;
    private Label lab1,lab2,lab3,lab4;
    private TextField t1,t2,t3,t4;
    private Button btn1,btn2;
    public Gadd(Frame f,String title,boolean mo){
   	 super(f,title,mo);
   		p1=new Panel();
   		p1.setLayout(new GridLayout(4,1));
		lab1=new Label("ѧ��");
		lab2=new Label("�γ̺�");
		lab3=new Label("�ɼ�");
		lab4=new Label("ѧ��");
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
		btn1=new Button("ȷ��");
		btn2=new Button("ȡ��");
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
			GradeManager sm=new GradeManager();
			String sql="insert grade values(?,?,?,?)";
			String[] param=new String []{t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),t4.getText().trim()};
			sm.addGrade(sql, param);
			this.dispose();
		}else if(e.getSource()==btn2){
			this.dispose();
		}
	 }
  }
