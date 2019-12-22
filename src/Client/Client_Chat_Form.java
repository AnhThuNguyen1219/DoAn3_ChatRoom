package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Client_Chat_Form extends JFrame {

	private JPanel chatPane;
	private JTextField inputField;
	private JTextField portField;
	private JTextField nameField;
	private JTextArea textArea;
	
	//Client chat
	int port;
	Socket s;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	readClient r;
	String name="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Chat_Form frame = new Client_Chat_Form();
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
	public Client_Chat_Form() {
		
		
		
		//Form
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 580);
		chatPane = new JPanel();
		chatPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(chatPane);
		chatPane.setLayout(null);
		
		JLabel lblAva = new JLabel("");
		Image ava = new ImageIcon(this.getClass().getResource("/ava.jpg")).getImage();
		lblAva.setIcon(new ImageIcon(ava));
		lblAva.setBounds(86, 64, 167, 167);
		chatPane.add(lblAva);
		
		inputField = new JTextField();
		inputField.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		inputField.setBounds(416, 470, 456, 50);
		chatPane.add(inputField);
		inputField.setColumns(10);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblPort.setBounds(24, 286, 56, 24);
		chatPane.add(lblPort);
		
		Button button = new Button("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("Roboto Thin", Font.PLAIN, 40));
		button.setBounds(356, 470, 50, 50);
		chatPane.add(button);
		Image sendIcon = new ImageIcon(this.getClass().getResource("/send.png")).getImage();
		
		portField = new JTextField();
		portField.setColumns(10);
		portField.setBounds(108, 279, 207, 42);
		chatPane.add(portField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(108, 347, 207, 42);
		chatPane.add(nameField);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblName.setBounds(24, 354, 56, 24);
		chatPane.add(lblName);
		
		
	
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					r.send();
			}
			
		});
		btnSend.setFont(new Font("Roboto Light", Font.PLAIN, 23));
		btnSend.setBounds(884, 469, 98, 50);
		chatPane.add(btnSend);
		
		JButton connectBtn = new JButton("Connect");
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				port = Integer.parseInt(portField.getText());
				name = nameField.getText().trim();
				System.out.println(port);
				try
				{
					s = new Socket("localhost", port);
					System.out.println("Client is started!");
					r =	new readClient(s);
					r.start();
					
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		});
		connectBtn.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		connectBtn.setBounds(12, 415, 128, 42);
		chatPane.add(connectBtn);
		
		JButton disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		disconnectBtn.setBounds(214, 415, 128, 42);
		chatPane.add(disconnectBtn);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		textArea.setBounds(356, 13, 626, 447);
		chatPane.add(textArea);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
		
		class readClient extends Thread
		{
			Socket s;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			public readClient(Socket s) throws IOException
			{
				this.s=s;
				dos = new DataOutputStream(s.getOutputStream());
				dis = new DataInputStream(s.getInputStream());
			}
			
			public void run()
			{
				try {
					while(true)
					{
						String received = dis.readUTF();
						System.out.println(received);
						textArea.append(received+"\n");
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void send()
			{
				try {
					String sent = inputField.getText().trim();
					if(sent.equals(""))
					{
						return;
					}
					dos.writeUTF(name+ ": "+sent);
					inputField.requestFocus();
					inputField.setText(null);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
}
