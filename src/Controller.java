import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.SQLException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ItemListener,WindowFocusListener,ListSelectionListener,ActionListener{
	private Model model;
	private View view;
	
	Controller(View view,Model model){
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		try {
			view.getColumnsTable(model.getElementsTable((String)e.getItem()));
		} catch (SQLException e1) {}
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		try {
			view.getCombo().removeAllItems();
			view.getTable(model.getTable());
		} catch (SQLException e1) {}
	}

	@Override
	public void windowLostFocus(WindowEvent e) {}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		view.prepareRequest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			view.insertIntoTable(model.getRows(view.getText_field1().getText().toString(),(String)view.getCombo().getSelectedItem()));
		} catch (SQLException e1) {}
	}
}