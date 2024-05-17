package operatingSystem;

import static operatingSystem.Constants.LIVE;
import static operatingSystem.Constants.STATUS;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")

public class ProcessControl {

	JFrame frame;
	JTable table;
	JScrollPane scroll;
	DefaultTableModel model;

	public static void main(String[] args) {
		new ProcessControl();

	}

	public ProcessControl() {
		table = new JTable() {
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

			{ // initializer block
				renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			@Override
			public TableCellRenderer getCellRenderer(int arg0, int arg1) {
				return renderRight;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Object[] columns = { "PID", "status", "newCT", "readyCT", "runningCT", "blockedCT", "liveCT" };
		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		frame = new JFrame("Process Control Block");
		frame.setBounds(100, 100, 640, 360);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.getColumnModel().setColumnMargin(20);
		for (int i = 0; i < columns.length; i++) {
			table.getColumnModel().getColumn(i).setResizable(false);
			table.getColumnModel().getColumn(i).setPreferredWidth(10);
		}

		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);

		scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 606, 302);

		frame.getContentPane().add(scroll);

//		frame_1.setVisible(true);
	}

	public void changeStatus(Process process) {
		int row = process.getPID() - 1;
		table.setValueAt(process.getStatus(), row, STATUS);
	}

	public void updateCT(Process process, int CT) {
		int row = process.getPID() - 1;
		table.setValueAt(process.LiveTime()[CT], row, CT);
		process.LiveTime()[LIVE] += 1;
		table.setValueAt((process.LiveTime()[LIVE]), row, LIVE);
	}

	public void addRow(Process process) {
		Object[] row = new Object[table.getColumnCount()];
		row[0] = process.getPID();
		row[1] = process.getStatus();

		// We iterate over all values of the row, and set the values from the process
		// NEW 2 READY 3 RUNNING 4 BLOCKED 5 LIVE 6
		for (int i = 2; i < row.length; i++) {
			row[i] = process.LiveTime()[i];
		}
		model.addRow(row);
	}
}
