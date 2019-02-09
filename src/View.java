import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class View {
	private JFrame frame;
	private JPanel panel;
	private JTable table;
	private JLabel label1,label2,label3;
	private JComboBox<String> combo;
	private JTextField text_field;
	private JList<String> list;
	private JButton button1;
	private Controller controller;
	private Model model;
	private List<String> something;
	
	View() throws SQLException{
		model = new Model();
		controller = new Controller(this,model);
		
		
		frame = new JFrame("Manage Etudiant");
		frame.setSize(1200, 700);
		frame.addWindowFocusListener(controller);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(frame.getWidth(),frame.getHeight());
		panel.setBackground(Color.cyan);
		
		label1 = new JLabel("Table");
		label1.setFont(new Font("arial", Font.BOLD, 16));
		label1.setBounds(20,25,60,20);
		
		combo = new JComboBox<String>();
		combo.setBounds(80, 25, 120, 20);
		combo.addItemListener(controller);
		
		list = new JList<String>();
		list.addListSelectionListener(controller);
		JScrollPane sc_list = new JScrollPane(list);
		sc_list.setBounds(frame.getWidth()-200,25,150,100);
		
		label2 = new JLabel("Select");
		label2.setFont(new Font("arial", Font.BOLD, 16));
		label2.setBounds(20,60,80,20);
		
		text_field = new JTextField();
		text_field.setFont(new Font("arial", Font.BOLD, 16));
		text_field.setBounds(20,85,700,30);
		text_field.setEditable(false);
		
		button1 = new JButton("Execute");
		button1.setBounds(730,85,100,30);
		button1.addActionListener(controller);
		
		label3 = new JLabel("Result");
		label3.setFont(new Font("arial", Font.BOLD, 16));
		label3.setBounds(20,120,60,20);
		
		table = new JTable();
		JScrollPane sc_table= new JScrollPane(table);
		sc_table.setBounds(20,145,frame.getWidth()-40,frame.getHeight()-190);
		
		panel.add(label1);
		panel.add(combo);
		panel.add(sc_list);
		panel.add(text_field);
		panel.add(label2);
		panel.add(button1);
		panel.add(label3);
		panel.add(sc_table);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JTable getTable() {
		return table;
	}

	public JTextField getText_field1() {
		return text_field;
	}
	
	public JComboBox<String> getCombo() {
		return combo;
	}

	public void getTable(ArrayList<String> tables) {
		for(String table : tables)
			combo.addItem(table);
	}
	
	public void getColumnsTable(ArrayList<String> columns) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String column : columns)
			model.addElement(column);
		list.setModel(model);
	}
	
	public void prepareRequest() {
		List<String> items = list.getSelectedValuesList();
		something = items;
		StringBuffer str = new StringBuffer("select ");
		for(String item : items) {
			str.append("`" + item + "` ,");
		}
		str.delete(str.length()-1,str.length());
		str.append(" " + "from " + combo.getSelectedItem());
		text_field.setText(str.toString());
	}

	public void insertIntoTable(Object[][] rows) {
		String[] columns = new String[something.size()];
		
		int i=0;
		for(String column : something) {
			columns[i] = column;
			i++;
		}

		DefaultTableModel tmodel = new DefaultTableModel();
		tmodel.setDataVector(rows,columns);
		table.setModel(tmodel);
	}
}
