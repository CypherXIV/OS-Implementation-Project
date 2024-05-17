package operatingSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

public class Settings extends JFrame implements ItemListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JComboBox<Integer> blockCmb;

	public static void main(String[] args) {

	}

	public Settings(OS os) {
		frame = new JFrame("Settings");
		frame.setBounds(100, 100, 739, 319);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("New Process Probability \r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 177, 50);
		frame.getContentPane().add(lblNewLabel);

		JSlider processPrSlider = new JSlider();
		processPrSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = processPrSlider.getValue();
				if (value == 0) {
					os.setNewProcessPRtoRandom();
				} else {
					os.setNewProcessPR(value);
				}
			}
		});

		processPrSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dictionary<Integer, JComponent> diction;
		processPrSlider.setMinorTickSpacing(10);
		processPrSlider.setMajorTickSpacing(10);
		processPrSlider.setPaintLabels(true);
		processPrSlider.setValue(20);
		processPrSlider.setPaintTicks(true);
		processPrSlider.setMinimum(0);
		processPrSlider.setBounds(181, 11, 518, 50);
		diction = processPrSlider.createStandardLabels(10);
		JLabel labelRandom = new JLabel("Random");
		labelRandom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		diction.put(0, labelRandom);
		processPrSlider.setLabelTable(diction);
		processPrSlider.setSnapToTicks(true);
		frame.getContentPane().add(processPrSlider);

		JComboBox<Integer> sizeCmb = new JComboBox<>();
		sizeCmb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				os.setProcessSize((int) sizeCmb.getSelectedItem());
			}
		});
		sizeCmb.setModel(new DefaultComboBoxModel<>(new Integer[] { 5, 10, 15, 20 }));
		sizeCmb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sizeCmb.setBounds(105, 164, 200, 21);
		sizeCmb.setSelectedIndex(1);
		frame.getContentPane().add(sizeCmb);

		JLabel lblNewLabel_1 = new JLabel("Process Size");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 164, 137, 21);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblCpuCount = new JLabel("CPU Count");
		lblCpuCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpuCount.setBounds(10, 87, 177, 50);
		frame.getContentPane().add(lblCpuCount);

		JSlider cpuSlider = new JSlider();
		cpuSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				os.setNumberOfCPUs(cpuSlider.getValue());
			}
		});
		cpuSlider.setMaximum(6);
		cpuSlider.setValue(1);
		cpuSlider.setSnapToTicks(true);
		cpuSlider.setPaintTicks(true);
		cpuSlider.setPaintLabels(true);
		cpuSlider.setMinorTickSpacing(1);
		cpuSlider.setMinimum(1);
		cpuSlider.setMajorTickSpacing(1);
		cpuSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpuSlider.setBounds(195, 87, 487, 50);
		frame.getContentPane().add(cpuSlider);

		JLabel lblNewLabel_1_1 = new JLabel("Max Blocked Time");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(353, 164, 137, 21);
		frame.getContentPane().add(lblNewLabel_1_1);

		blockCmb = new JComboBox<>();
		blockCmb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				os.setMaxTimeBlocked((int) blockCmb.getSelectedItem());
			}
		});
		blockCmb.setModel(new DefaultComboBoxModel<>(new Integer[] { 5, 10, 15, 20 }));
		blockCmb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blockCmb.setBounds(477, 164, 200, 21);
		blockCmb.setSelectedIndex(1);
		frame.getContentPane().add(blockCmb);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
