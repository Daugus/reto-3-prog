package pruebas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TestTemaClaro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9195804482787132040L;
	private JPanel contentPane;
	private JTextField txtCampoDeTexto;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTemaClaro frame = new TestTemaClaro();
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
	public TestTemaClaro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		dlm.addAll(Arrays.asList("uno", "dos", "tres"));
		JList<String> list = new JList<String>();
		list.setModel(dlm);
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.BOLD, 13));
		list.setBounds(53, 64, 128, 164);
		list.setBackground(Color.WHITE);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("botón");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(240, 159, 158, 46);
		contentPane.add(btnNewButton);
		
		txtCampoDeTexto = new JTextField();
		txtCampoDeTexto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCampoDeTexto.setText("campo de texto");
		txtCampoDeTexto.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCampoDeTexto.setForeground(Color.BLACK);
		txtCampoDeTexto.setBackground(Color.WHITE);
		txtCampoDeTexto.setBounds(240, 22, 172, 36);
		contentPane.add(txtCampoDeTexto);
		txtCampoDeTexto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("etiqueta");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(42, 22, 139, 31);
		contentPane.add(lblNewLabel);
		
		JList<String> list_1 = new JList<String>();
		list_1.setForeground(Color.BLACK);
		list_1.setModel(dlm);
		list_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		list_1.setBackground(Color.WHITE);
		list_1.setBounds(53, 336, 128, 164);
		contentPane.add(list_1);
		
		JLabel lblNewLabel_1 = new JLabel("etiqueta");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(42, 294, 139, 31);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText("campo de texto");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Segoe UI", Font.BOLD, 13));
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(240, 294, 172, 36);
		contentPane.add(textField);
		
		JButton btnNewButton_1 = new JButton("botón");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(240, 431, 158, 46);
		contentPane.add(btnNewButton_1);
	}
}
