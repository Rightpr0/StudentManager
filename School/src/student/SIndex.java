package student;
import grade.GIndex;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import course.CIndex;
import dao.GradeManager;
import dao.StudentManager;
public class SIndex extends Frame implements ActionListener {
	private Panel p1, p2, p3, p4;
	private Menu menu2, menu3;
	// 菜单项“课程信息”
	private MenuItem citem1;
	// 菜单项“成绩信息”
	private MenuItem gitem1;
	private MenuBar bar;
	private Button btn1, addbtn, updbtn, delbtn;
	private Label lab;
	private TextField tf;
	private JScrollPane jsp;
	private StudentManager sm;
	private JTable jtab;
	private JOptionPane jop;

	public void menuCreate() {
		bar = new MenuBar();
		menu2 = new Menu("课程信息");
		menu3 = new Menu("成绩信息");
		citem1 = new MenuItem("课程信息");
		citem1.addActionListener(this);
		menu2.add(citem1);
		gitem1 = new MenuItem("成绩信息");
		gitem1.addActionListener(this);
		menu3.add(gitem1);
		bar.add(menu2);
		bar.add(menu3);
		this.setMenuBar(bar);
	}
	public SIndex() {
		this.setTitle("学生管理");
		p1 = new Panel();
		p1.setLayout(new BorderLayout());
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		menuCreate();
		p1.add(p2, BorderLayout.NORTH);
		lab = new Label("请输入性别：");
		tf = new TextField(8);
		btn1 = new Button("开始查询");
		btn1.addActionListener(this);
		p3.add(lab);
		p3.add(tf);
		p3.add(btn1);
		p1.add(p3, BorderLayout.SOUTH);
		this.add(p1, BorderLayout.NORTH);
		sm = new StudentManager();
		String sql = "select * from student where 0=?";
		String[] param = new String[] { "0" };
		sm.addStudent(sql, param);
		jtab = new JTable(sm);
		jtab.setRowHeight(30);
		jsp = new JScrollPane(jtab);
		this.add(jsp, BorderLayout.CENTER);
		addbtn = new Button("添加");
		updbtn = new Button("修改");
		delbtn = new Button("删除");
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

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == citem1) {// 是否点击的是“课程信息”
			CIndex cx = new CIndex();
			cx.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == gitem1) {// 是否点击的是“成绩信息”
			GIndex gx = new GIndex();
			gx.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == btn1) {
			String jtf = tf.getText().trim();
			if (jtf.equals("")) {
				String sql = "select * from student where 1=?";
				String[] param = new String[] { "1" };
				sm = new StudentManager();
				sm.addStudent(sql, param);
				jtab.setModel(sm);
			} else {
				String sql = "select * from student where ssex=?";
				String[] param = new String[] { jtf };
				sm = new StudentManager();
				sm.addStudent(sql, param);
				jtab.setModel(sm);
			}
		} else if (e.getSource() == addbtn ) {
			Sadd add = new Sadd(this, "添加学生信息", true);
			sm = new StudentManager();
			String sql = "select * from student where 1=?";
			String[] param = new String[] { "1" };
			sm.addStudent(sql, param);
			jtab.setModel(sm);
		} else if (e.getSource() == updbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "请选择修改项");
				return;
			} else {
				Supd upd = new Supd(this, "修改学生信息", true, sm, rowNo);
				sm = new StudentManager();
				String sql = "select *  from student where 1=?";
				String[] param = new String[] { "1" };
				sm.addStudent(sql, param);
				jtab.setModel(sm);
			}
		} else if (e.getSource() == delbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "请选择删除项");
				return;
			} else {
				String sql = "delete from student where sid=?";
				String sid = (String) this.jtab.getValueAt(rowNo, 0);
				String[] param = new String[] { sid };
				StudentManager sm1 = new StudentManager();
				sm1.updateStu(sql, param);
				String sql3="delete from grade where gsid=?";
				String[] param1 = new String[] { sid };
				GradeManager gm=new GradeManager();
				gm.updateGrade(sql3, param1);
				sm = new StudentManager();
				String sql2 = "select * from student where 1=?";
				String[] param2 = new String[] { "1" };
				sm.addStudent(sql2, param2);
				jtab.setModel(sm);
			}
		}
	}
	public static void main(String[] args) {
		new SIndex();
	}
}
