package course;

import grade.GIndex;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import student.SIndex;
import dao.CourseManager;
import dao.GradeManager;

public class CIndex extends Frame implements ActionListener {
	private Panel p1, p2, p3, p4;
	private Button btn1, addbtn, updbtn, delbtn;
	private Menu menu1, menu2;
	private MenuItem sitem1;// �˵��ѧ����Ϣ��
	private MenuItem gitem1;// �˵���ɼ���Ϣ��
	private MenuBar bar;
	private Label lab;
	private TextField tf;
	private JScrollPane jsp;
	private CourseManager cm;
	private JTable jtab;
	private JOptionPane jop;

	public void menuCreate() {
		bar = new MenuBar();
		menu1 = new Menu("ѧ����Ϣ");
		menu2 = new Menu("�ɼ���Ϣ");
		sitem1 = new MenuItem("ѧ����Ϣ");
		sitem1.addActionListener(this);
		menu1.add(sitem1);
		gitem1 = new MenuItem("�ɼ���Ϣ");
		gitem1.addActionListener(this);
		menu2.add(gitem1);
		bar.add(menu1);
		bar.add(menu2);
		this.setMenuBar(bar);
	}

	public CIndex() {
		this.setTitle("�γ̹���");
		p1 = new Panel();
		p1.setLayout(new BorderLayout());
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		menuCreate();
		p1.add(p2, BorderLayout.NORTH);
		lab = new Label("������γ�����");
		tf = new TextField(8);
		btn1 = new Button("��ʼ��ѯ");
		btn1.addActionListener(this);
		p3.add(lab);
		p3.add(tf);
		p3.add(btn1);
		p1.add(p3, BorderLayout.SOUTH);
		this.add(p1, BorderLayout.NORTH);
		cm = new CourseManager();
		String sql = "select * from course where 0=?";
		String[] param = new String[] { "0" };
		cm.addCourse(sql, param);
		jtab = new JTable(cm);
		jtab.setRowHeight(30);
		jsp = new JScrollPane(jtab);
		this.add(jsp, BorderLayout.CENTER);
		addbtn = new Button("���");
		updbtn = new Button("�޸�");
		delbtn = new Button("ɾ��");
		addbtn.addActionListener(this);
		updbtn.addActionListener(this);
		delbtn.addActionListener(this);
		p4.add(addbtn);
		p4.add(updbtn);
		p4.add(delbtn);
		this.add(p4, BorderLayout.SOUTH);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		this.setSize(1000, tk.getScreenSize().height - 200);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent w) {
				w.getWindow().setVisible(false);
				((Frame) w.getComponent()).dispose();
				System.exit(0);
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sitem1) {// �Ƿ������ǡ�ѧ����Ϣ��
			SIndex in = new SIndex();
			in.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == gitem1) {// �Ƿ������ǡ��ɼ���Ϣ��
			GIndex gx = new GIndex();
			gx.setVisible(true);
			this.setVisible(false);
		}
		
		if (e.getSource() == btn1) {
			String jtf = tf.getText().trim();
			if (jtf.equals("")) {
				String sql = "select * from course where 1=?";
				String[] param = new String[] { "1" };
				cm = new CourseManager();
				cm.addCourse(sql, param);
				jtab.setModel(cm);
			} else {
				String sql = "select * from course where cname=?";
				String[] param = new String[] { jtf };
				cm = new CourseManager();
				cm.addCourse(sql, param);
				jtab.setModel(cm);
			}
		} else if (e.getSource() == addbtn || e.getSource() == btn1) {
			Cadd add = new Cadd(this, "��ӿγ���Ϣ", true);
			cm = new CourseManager();
			String sql = "select * from course where 1=?";
			String[] param = new String[] { "1" };
			cm.addCourse(sql, param);
			jtab.setModel(cm);
		} else if (e.getSource() == updbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "��ѡ���޸���");
				return;
			} else {
				Cupd upd = new Cupd(this, "�޸Ŀγ���Ϣ", true, cm, rowNo);
				cm = new CourseManager();
				String sql = "select *  from course where 1=?";
				String[] param = new String[] { "1" };
				cm.addCourse(sql, param);
				jtab.setModel(cm);
			}
		} else if (e.getSource() == delbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "��ѡ��ɾ����");
				return;
			} else {
				String sql = "delete from course where cid=?";
				String cid = (String) this.jtab.getValueAt(rowNo, 0);
				String[] param = new String[] { cid };
				CourseManager sm1 = new CourseManager();
				sm1.updateCourse(sql, param);
				String sql3="delete from grade where gsid=?";
				String[] param1 = new String[] { cid };
				GradeManager gm=new GradeManager();
				gm.updateGrade(sql3, param1);
				cm = new CourseManager();
				String sql2 = "select * from course where 1=?";
				String[] param2 = new String[] { "1" };
				cm.addCourse(sql2, param2);
				jtab.setModel(cm);
			}
		}
	}
}
