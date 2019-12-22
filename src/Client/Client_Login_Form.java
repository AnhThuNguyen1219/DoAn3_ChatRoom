package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client_Login_Form extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Login_Form frame = new Client_Login_Form();
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
	public Client_Login_Form() {
		setTitle("Client - Connect");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Roboto Light", Font.PLAIN, 23));
		lblIp.setBounds(236, 95, 104, 31);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setFont(new Font("Roboto Light", Font.PLAIN, 23));
		lblPort.setBounds(236, 159, 104, 42);
		contentPane.add(lblPort);
		
		textField = new JTextField();
		textField.setBounds(352, 92, 295, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(352, 162, 295, 42);
		contentPane.add(textField_1);
		
		JLabel lblWelcomeToServer = new JLabel("Welcome to AmTuCo");
		lblWelcomeToServer.setFont(new Font("Roboto Light", Font.BOLD, 30));
		lblWelcomeToServer.setBounds(12, 13, 338, 49);
		contentPane.add(lblWelcomeToServer);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		btnConnect.setBounds(104, 320, 128, 42);
		contentPane.add(btnConnect);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setBounds(471, 319, 128, 42);
		contentPane.add(btnCancel);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 23));
		lblName.setBounds(236, 238, 104, 31);
		contentPane.add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(352, 235, 295, 42);
		contentPane.add(textField_2);
		
		JLabel avaLabel = new JLabel("");
		Image ava = new ImageIcon(this.getClass().getResource("/ava.jpg")).getImage();
		avaLabel.setIcon(new ImageIcon(ava));
		avaLabel.setBounds(12, 95, 167, 167);
		contentPane.add(avaLabel);
		
		
	}
}
