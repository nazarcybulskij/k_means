import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;

import java.awt.color.ColorSpace;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;

public class main_frame extends JFrame {
    private int count_class=1;
    private final  Color[] Color_list = { Color.RED, Color.BLUE, Color.WHITE,
				Color.CYAN, Color.GREEN, Color.MAGENTA ,Color.GRAY,Color.ORANGE};
    private k_means generator;
    private BufferedImage picure;
    private Graphics2D draw;
    private ArrayList<ArrayList<element>> data;
		
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
		
				/*	javax.swing.UIManager
					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");*/
			
				try {
					main_frame frame = new main_frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public main_frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);

		final JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][grow][][][]"));
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(9);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "50"}));
		comboBox.setSelectedIndex(0);
		JButton btnNewButton = new JButton(
				"\u0413\u0435\u043D\u0435\u0440\u0443\u0432\u0430\u0442\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				picure= new BufferedImage(600, 600,BufferedImage.TYPE_INT_RGB);
				draw = picure.createGraphics();
				generator = new k_means();

				if (comboBox.getSelectedItem() != null)
					count_class = Integer.parseInt(comboBox.getSelectedItem().toString());
			



				data= generator.generateData(40,count_class);
				
				for (int j = 0; j < count_class; j++) {
					draw.setColor(Color_list[j]);
					for (int i = 0; i < 40; i++) {
						draw.drawOval(data.get(j).get(i).X,
								data.get(j).get(i).Y, 10, 10);

					}
				}

				panel.add(new JLabel(new ImageIcon((Image) picure)));
				contentPane.repaint();
				pack();

			}
		});

		panel_1.add(comboBox, "cell 0 0,growx");
		panel_1.add(btnNewButton, "cell 0 1");

		Component verticalGlue = Box.createVerticalGlue();
		panel_1.add(verticalGlue, "cell 0 5");

		JButton btnNewButton_1 = new JButton(
				"\u041A\u043B\u0430\u0441\u0438\u0444\u0456\u043A\u0443\u0432\u0430\u0442\u0438");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for (int j = 0; j < picure.getHeight(); j = j + 4) {
					for (int i = 0; i < picure.getWidth(); i = i + 4) {
						int index = generator.getClassIndex(i, j);
						draw.setColor(Color_list[index]);
						draw.drawLine(i, j, i, j);

					}
				}
				contentPane.repaint();
				
			}
		});
		panel_1.add(btnNewButton_1, "cell 0 8");
	}
}
