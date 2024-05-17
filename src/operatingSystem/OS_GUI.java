package operatingSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class OS_GUI {

	private JFrame frmOperatingSystem;
	private JTextField txtPID0;
	private JTextField txtSize0;
	private JTextField txtProgress0;

	private OS os;
	private HDD hdd;
	private Dispatcher dispatcher;
	private CPU[] cpu;
	private ProcessControl processControl;
	private Settings settings;
	private Blocked blocked;
	private boolean firstCLick;
	private JTextField txtPID1;
	private JTextField txtSize1;
	private JTextField txtProgress1;
	private JTextField txtPID2;
	private JTextField txtSize2;
	private JTextField txtProgress2;
	private JTextField txtPID3;
	private JTextField txtSize3;
	private JTextField txtProgress3;
	private JTextField txtPID4;
	private JTextField txtSize4;
	private JTextField txtProgress4;
	private JTextField txtPID5;
	private JTextField txtSize5;
	private JTextField txtProgress5;
	private JPanel pnl_CPU_0;
	private JPanel pnl_CPU_1;
	private JPanel pnl_CPU_2;
	private JPanel pnl_CPU_3;
	private JPanel pnl_CPU_4;
	private JPanel pnl_CPU_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OS_GUI window = new OS_GUI();
					window.frmOperatingSystem.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Method for updating the text fields in the CPU panel
	void updateCPUFields(CPU[] cpu) {

		// If the current process executing is not null and the cpu is enabled
		if (cpu[0].getCurrentProcess() != null && cpu[0].isEnabled) {

			// Update the field values to the values of the current process id, size and
			// program counter.
			txtPID0.setText(Integer.toString(cpu[0].getCurrentProcess().getPID()));
			txtSize0.setText(Integer.toString(cpu[0].getCurrentProcess().getSize()));
			txtProgress0.setText(Integer.toString(cpu[0].getProcessProgress()));
		} else if (!txtPID0.getText().equals(null)) {

			txtPID0.setText(null);
			txtSize0.setText(null);
			txtProgress0.setText(null);
		}

		if (cpu[1].getCurrentProcess() != null && cpu[1].isEnabled) {

			txtPID1.setText(Integer.toString(cpu[1].getCurrentProcess().getPID()));
			txtSize1.setText(Integer.toString(cpu[1].getCurrentProcess().getSize()));
			txtProgress1.setText(Integer.toString(cpu[1].getProcessProgress()));
		} else if (!txtPID1.getText().equals(null)) {

			txtPID1.setText(null);
			txtSize1.setText(null);
			txtProgress1.setText(null);
		}
		if (cpu[2].getCurrentProcess() != null && cpu[2].isEnabled) {

			txtPID2.setText(Integer.toString(cpu[2].getCurrentProcess().getPID()));
			txtSize2.setText(Integer.toString(cpu[2].getCurrentProcess().getSize()));
			txtProgress2.setText(Integer.toString(cpu[2].getProcessProgress()));
		} else if (!txtPID2.getText().equals(null)) {

			txtPID2.setText(null);
			txtSize2.setText(null);
			txtProgress2.setText(null);
		}
		if (cpu[3].getCurrentProcess() != null && cpu[3].isEnabled) {

			txtPID3.setText(Integer.toString(cpu[3].getCurrentProcess().getPID()));
			txtSize3.setText(Integer.toString(cpu[3].getCurrentProcess().getSize()));
			txtProgress3.setText(Integer.toString(cpu[3].getProcessProgress()));
		} else if (!txtPID3.getText().equals(null)) {
			txtPID3.setText(null);
			txtSize3.setText(null);
			txtProgress3.setText(null);
		}
		if (cpu[4].getCurrentProcess() != null && cpu[4].isEnabled) {

			txtPID4.setText(Integer.toString(cpu[4].getCurrentProcess().getPID()));
			txtSize4.setText(Integer.toString(cpu[4].getCurrentProcess().getSize()));
			txtProgress4.setText(Integer.toString(cpu[4].getProcessProgress()));
		} else if (!txtPID4.getText().equals(null)) {

			txtPID4.setText(null);
			txtSize4.setText(null);
			txtProgress4.setText(null);
		}
		if (cpu[5].getCurrentProcess() != null && cpu[5].isEnabled) {

			txtPID5.setText(Integer.toString(cpu[5].getCurrentProcess().getPID()));
			txtSize5.setText(Integer.toString(cpu[5].getCurrentProcess().getSize()));
			txtProgress5.setText(Integer.toString(cpu[5].getProcessProgress()));
		} else if (!txtPID5.getText().equals(null)) {

			txtPID5.setText(null);
			txtSize5.setText(null);
			txtProgress5.setText(null);
		}
	}

	/**
	 * Create the application.
	 */
	public OS_GUI() {
		// change ui apperance to look modern?
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// set the max number of cpu to 6
//		that matches the ui layout
		cpu = new CPU[6];
		for (int i = 0; i < cpu.length; i++) {
			cpu[i] = new CPU();
		}

		// at start only one cpu is enabled
		cpu[0].isEnabled = true;

		hdd = new HDD();
		dispatcher = new Dispatcher();
		firstCLick = true;
		processControl = new ProcessControl();
		blocked = new Blocked();

		// initialize OS instance with the elements it will manage
		os = new OS(cpu, dispatcher, hdd, processControl, blocked, this);
		settings = new Settings(os);
		initialize();
	}

	private void openPCB() {
		processControl.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOperatingSystem = new JFrame();
		frmOperatingSystem.setFont(new Font("Tahoma", Font.PLAIN, 46));
		frmOperatingSystem.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmOperatingSystem.setTitle("Operating System");
		frmOperatingSystem.setResizable(false);
		frmOperatingSystem.setBounds(120, 35, 1403, 534);
		frmOperatingSystem.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmOperatingSystem.getContentPane().setLayout(null);

		JPanel pnl_HDD = new JPanel();
		pnl_HDD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_HDD.setBorder(new TitledBorder(null, "HDD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_HDD.setBounds(10, 10, 266, 390);
		frmOperatingSystem.getContentPane().add(pnl_HDD);

		JList<Process> lstProcessBatch = new JList<>();
		lstProcessBatch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnl_HDD.add(lstProcessBatch);
		lstProcessBatch.setModel(hdd.ProcessBatchList());

		JPanel pnl_DISPATCHER = new JPanel();
		pnl_DISPATCHER.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_DISPATCHER
				.setBorder(new TitledBorder(null, "Dispatcher", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_DISPATCHER.setBounds(286, 10, 266, 390);
		frmOperatingSystem.getContentPane().add(pnl_DISPATCHER);

		JList<Process> lstSortedBatch = new JList<>();
		lstSortedBatch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnl_DISPATCHER.add(lstSortedBatch);
		lstSortedBatch.setModel(dispatcher.dispatcherBatchList);

		pnl_CPU_0 = new JPanel();
		pnl_CPU_0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_CPU_0.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 0",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_0.setBounds(562, 10, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_0);
		pnl_CPU_0.setLayout(null);

		JLabel lblNewLabel = new JLabel("PID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(26, 26, 73, 13);
		pnl_CPU_0.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SIZE:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 47, 73, 13);
		pnl_CPU_0.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PROGRESS:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(26, 70, 73, 13);
		pnl_CPU_0.add(lblNewLabel_2);

		txtPID0 = new JTextField();
		txtPID0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID0.setEditable(false);
		txtPID0.setBounds(129, 23, 96, 19);
		pnl_CPU_0.add(txtPID0);
		txtPID0.setColumns(10);

		txtSize0 = new JTextField();
		txtSize0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize0.setEditable(false);
		txtSize0.setBounds(129, 44, 96, 19);
		pnl_CPU_0.add(txtSize0);
		txtSize0.setColumns(10);

		txtProgress0 = new JTextField();
		txtProgress0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress0.setEditable(false);
		txtProgress0.setBounds(129, 67, 96, 19);
		pnl_CPU_0.add(txtProgress0);
		txtProgress0.setColumns(10);

		JButton btnClockTick = new JButton("Clock Tick");
		btnClockTick.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnClockTick.setBounds(580, 411, 196, 71);
		frmOperatingSystem.getContentPane().add(btnClockTick);

		JButton btnPCB = new JButton("PCB");
		btnPCB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// opens process control block panel
				openPCB();
			}
		});
		btnPCB.setBounds(237, 411, 196, 71);
		frmOperatingSystem.getContentPane().add(btnPCB);

		pnl_CPU_1 = new JPanel();
		pnl_CPU_1.setVisible(false);
		pnl_CPU_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_CPU_1.setLayout(null);
		pnl_CPU_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 1",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_1.setBounds(562, 127, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_1);

		JLabel lblNewLabel_3 = new JLabel("PID:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(26, 26, 73, 13);
		pnl_CPU_1.add(lblNewLabel_3);

		JLabel lblNewLabel_1_1 = new JLabel("SIZE:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(26, 47, 73, 13);
		pnl_CPU_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("PROGRESS:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(26, 70, 73, 13);
		pnl_CPU_1.add(lblNewLabel_2_1);

		txtPID1 = new JTextField();
		txtPID1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID1.setEditable(false);
		txtPID1.setColumns(10);
		txtPID1.setBounds(129, 23, 96, 19);
		pnl_CPU_1.add(txtPID1);

		txtSize1 = new JTextField();
		txtSize1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize1.setEditable(false);
		txtSize1.setColumns(10);
		txtSize1.setBounds(129, 44, 96, 19);
		pnl_CPU_1.add(txtSize1);

		txtProgress1 = new JTextField();
		txtProgress1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress1.setEditable(false);
		txtProgress1.setColumns(10);
		txtProgress1.setBounds(129, 67, 96, 19);
		pnl_CPU_1.add(txtProgress1);

		pnl_CPU_2 = new JPanel();
		pnl_CPU_2.setVisible(false);
		pnl_CPU_2.setLayout(null);
		pnl_CPU_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 2",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_2.setBounds(562, 244, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_2);

		JLabel lblNewLabel_4 = new JLabel("PID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(26, 26, 73, 13);
		pnl_CPU_2.add(lblNewLabel_4);

		JLabel lblNewLabel_1_2 = new JLabel("SIZE:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(26, 47, 73, 13);
		pnl_CPU_2.add(lblNewLabel_1_2);

		JLabel lblNewLabel_2_2 = new JLabel("PROGRESS:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(26, 70, 73, 13);
		pnl_CPU_2.add(lblNewLabel_2_2);

		txtPID2 = new JTextField();
		txtPID2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID2.setEditable(false);
		txtPID2.setColumns(10);
		txtPID2.setBounds(129, 23, 96, 19);
		pnl_CPU_2.add(txtPID2);

		txtSize2 = new JTextField();
		txtSize2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize2.setEditable(false);
		txtSize2.setColumns(10);
		txtSize2.setBounds(129, 44, 96, 19);
		pnl_CPU_2.add(txtSize2);

		txtProgress2 = new JTextField();
		txtProgress2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress2.setEditable(false);
		txtProgress2.setColumns(10);
		txtProgress2.setBounds(129, 67, 96, 19);
		pnl_CPU_2.add(txtProgress2);

		pnl_CPU_3 = new JPanel();
		pnl_CPU_3.setVisible(false);
		pnl_CPU_3.setLayout(null);
		pnl_CPU_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 3",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_3.setBounds(838, 10, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_3);

		JLabel lblNewLabel_5 = new JLabel("PID:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(26, 26, 73, 13);
		pnl_CPU_3.add(lblNewLabel_5);

		JLabel lblNewLabel_1_3 = new JLabel("SIZE:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(26, 47, 73, 13);
		pnl_CPU_3.add(lblNewLabel_1_3);

		JLabel lblNewLabel_2_3 = new JLabel("PROGRESS:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(26, 70, 73, 13);
		pnl_CPU_3.add(lblNewLabel_2_3);

		txtPID3 = new JTextField();
		txtPID3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID3.setEditable(false);
		txtPID3.setColumns(10);
		txtPID3.setBounds(129, 23, 96, 19);
		pnl_CPU_3.add(txtPID3);

		txtSize3 = new JTextField();
		txtSize3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize3.setEditable(false);
		txtSize3.setColumns(10);
		txtSize3.setBounds(129, 44, 96, 19);
		pnl_CPU_3.add(txtSize3);

		txtProgress3 = new JTextField();
		txtProgress3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress3.setEditable(false);
		txtProgress3.setColumns(10);
		txtProgress3.setBounds(129, 67, 96, 19);
		pnl_CPU_3.add(txtProgress3);

		pnl_CPU_4 = new JPanel();
		pnl_CPU_4.setVisible(false);
		pnl_CPU_4.setLayout(null);
		pnl_CPU_4.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 4",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_4.setBounds(838, 127, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_4);

		JLabel lblNewLabel_6 = new JLabel("PID:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(26, 26, 73, 13);
		pnl_CPU_4.add(lblNewLabel_6);

		JLabel lblNewLabel_1_4 = new JLabel("SIZE:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(26, 47, 73, 13);
		pnl_CPU_4.add(lblNewLabel_1_4);

		JLabel lblNewLabel_2_4 = new JLabel("PROGRESS:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4.setBounds(26, 70, 73, 13);
		pnl_CPU_4.add(lblNewLabel_2_4);

		txtPID4 = new JTextField();
		txtPID4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID4.setEditable(false);
		txtPID4.setColumns(10);
		txtPID4.setBounds(129, 23, 96, 19);
		pnl_CPU_4.add(txtPID4);

		txtSize4 = new JTextField();
		txtSize4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize4.setEditable(false);
		txtSize4.setColumns(10);
		txtSize4.setBounds(129, 44, 96, 19);
		pnl_CPU_4.add(txtSize4);

		txtProgress4 = new JTextField();
		txtProgress4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress4.setEditable(false);
		txtProgress4.setColumns(10);
		txtProgress4.setBounds(129, 67, 96, 19);
		pnl_CPU_4.add(txtProgress4);

		JButton btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settings.frame.setVisible(true);
			}
		});
		btnSettings.setBounds(980, 411, 196, 71);
		frmOperatingSystem.getContentPane().add(btnSettings);

		JPanel pnl_Blocked = new JPanel();
		pnl_Blocked.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Blocked",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_Blocked.setBounds(1114, 10, 266, 390);
		frmOperatingSystem.getContentPane().add(pnl_Blocked);

		JList<BlockedProccess> lstBlockedBatch = new JList<>();
		lstBlockedBatch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstBlockedBatch.setModel(blocked.blockedBatchList);
		pnl_Blocked.add(lstBlockedBatch);

		pnl_CPU_5 = new JPanel();
		pnl_CPU_5.setVisible(false);
		pnl_CPU_5.setLayout(null);
		pnl_CPU_5.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CPU 5",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_CPU_5.setBounds(838, 244, 266, 106);
		frmOperatingSystem.getContentPane().add(pnl_CPU_5);

		JLabel lblNewLabel_6_1 = new JLabel("PID:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(26, 26, 73, 13);
		pnl_CPU_5.add(lblNewLabel_6_1);

		JLabel lblNewLabel_1_4_1 = new JLabel("SIZE:");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4_1.setBounds(26, 47, 73, 13);
		pnl_CPU_5.add(lblNewLabel_1_4_1);

		JLabel lblNewLabel_2_4_1 = new JLabel("PROGRESS:");
		lblNewLabel_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1.setBounds(26, 70, 73, 13);
		pnl_CPU_5.add(lblNewLabel_2_4_1);

		txtPID5 = new JTextField();
		txtPID5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPID5.setEditable(false);
		txtPID5.setColumns(10);
		txtPID5.setBounds(129, 23, 96, 19);
		pnl_CPU_5.add(txtPID5);

		txtSize5 = new JTextField();
		txtSize5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSize5.setEditable(false);
		txtSize5.setColumns(10);
		txtSize5.setBounds(129, 44, 96, 19);
		pnl_CPU_5.add(txtSize5);

		txtProgress5 = new JTextField();
		txtProgress5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProgress5.setEditable(false);
		txtProgress5.setColumns(10);
		txtProgress5.setBounds(129, 67, 96, 19);
		pnl_CPU_5.add(txtProgress5);

		btnClockTick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				os.processControl();
				updateCPUFields(cpu);

//				update blocked process state details
				if (!blocked.blockedBatchList.isEmpty()) {
					lstBlockedBatch.repaint();
				}

				// before the first click probability is set to 100
//				to ensure a process will be created
//				after the first click probability is set to 20
				if (firstCLick) {
					hdd.setProbability(20);
					firstCLick = false;
				}
			}
		});
	}

	// set disabled cpu invisible
	public void updateEnabledCPU(CPU[] cpu) {
		pnl_CPU_0.setVisible(cpu[0].isEnabled);
		pnl_CPU_1.setVisible(cpu[1].isEnabled);
		pnl_CPU_2.setVisible(cpu[2].isEnabled);
		pnl_CPU_3.setVisible(cpu[3].isEnabled);
		pnl_CPU_4.setVisible(cpu[4].isEnabled);
		pnl_CPU_5.setVisible(cpu[5].isEnabled);
	}

}
