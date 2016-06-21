package grade;
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
import course.CIndex;
import dao.GradeManager;
public class GIndex extends Frame implements ActionListener {
	private Panel p1, p2, p3, p4;
	private Button btn1, addbtn, updbtn, delbtn;
	private Menu menu1, menu2;
	private MenuItem sitem1; // 菜单项“学生信息”
	private MenuItem citem1;// 菜单项“课程信息”
	private MenuBar bar;
	private Label lab;
	private TextField tf;
	private JScrollPane jsp;
	private GradeManager gm;
	private JTable jtab;
	private JOptionPane jop;

	public void menuCreate() {
		bar = new MenuBar();
		menu1 = new Menu("学生信息");
		menu2 = new Menu("课程信息");
		sitem1 = new MenuItem("学生信息");
		sitem1.addActionListener(this);
		menu1.add(sitem1);
		citem1 = new MenuItem("课程信息");
		citem1.addActionListener(this);
		menu2.add(citem1);
		bar.add(menu1);
		bar.add(menu2);
		this.setMenuBar(bar);
	}
	public GIndex() {
		this.setTitle("成绩管理");
		p1 = new Panel();
		p1.setLayout(new BorderLayout());
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		menuCreate();
		p1.add(p2, BorderLayout.NORTH);
		lab = new Label("请输入课程号：");
		tf = new TextField(8);
		btn1 = new Button("开始查询");
		btn1.addActionListener(this);
		p3.add(lab);
		p3.add(tf);
		p3.add(btn1);
		p1.add(p3, BorderLayout.SOUTH);
		this.add(p1, BorderLayout.NORTH);
		gm = new GradeManager();
		String sql = "select * from grade where 0=?";
		String[] param = new String[] { "0" };
		gm.addGrade(sql, param);
		jtab = new JTable(gm);
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == citem1) {// 是否点击的是“课程信息”
			CIndex cx = new CIndex();
			cx.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == sitem1) {// 是否点击的是“学生信息”
			SIndex sx = new SIndex();
			sx.setVisible(true);
			this.setVisible(false);
		}

		if (e.getSource() == btn1) {
			String jtf = tf.getText().trim();
			if (jtf.equals("")) {
				String sql = "select * from grade where 1=?";
				String[] param = new String[] { "1" };
				gm = new GradeManager();
				gm.addGrade(sql, param);
				jtab.setModel(gm);
			} else {
				String sql = "select * from grade where gcid=?";
				String[] param = new String[] { jtf };
				gm = new GradeManager();
				gm.addGrade(sql, param);
				jtab.setModel(gm);
			}
		} else if (e.getSource() == addbtn || e.getSource() == btn1) {
			Gadd add = new Gadd(this, "添加成绩信息", true);
			gm = new GradeManager();
			String sql = "select * from grade where 1=?";
			String[] param = new String[] { "1" };
			gm.addGrade(sql, param);
			jtab.setModel(gm);
		} else if (e.getSource() == updbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "请选择修改项");
				return;
			} else {
				Gupd upd = new Gupd(this, "修改成绩信息", true, gm, rowNo);
				gm = new GradeManager();
				String sql = "select *  from grade where 1=?";
				String[] param = new String[] { "1" };
				gm.addGrade(sql, param);
				jtab.setModel(gm);
			}
		} else if (e.getSource() == delbtn) {
			int rowNo = this.jtab.getSelectedRow();
			if (rowNo == -1) {
				jop.showMessageDialog(this, "请选择删除项");
				return;
			} else {
				String sql = "delete from grade where gsid=?";
				String cid = (String) this.jtab.getValueAt(rowNo, 0);
				String[] param = new String[] { cid };
				GradeManager sm1 = new GradeManager();
				sm1.updateGrade(sql, param);
				gm = new GradeManager();
				String sql2 = "select * from grade where 1=?";
				String[] param2 = new String[] { "1" };
				gm.addGrade(sql2, param2);
				jtab.setModel(gm);
			}
		}
	}
}
