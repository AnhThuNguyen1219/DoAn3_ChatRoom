package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import Server.Server_Chat;

public class Server_Start_Form extends JFrame {

	private JPanel contentPane;
	private JTextField portTextField;
	int port;
	Server server;
	public static ArrayList<Socket> list = new ArrayList<Socket>();
	
	//List socket
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_Start_Form frame = new Server_Start_Form();
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
	public Server_Start_Form() {
		
		
		
		setResizable(false);
		setTitle("Server - Connect");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel avaLabel = new JLabel("");
		Image ava = new ImageIcon(this.getClass().getResource("/ava.jpg")).getImage();
		avaLabel.setIcon(new ImageIcon(ava));
		avaLabel.setBounds(24, 13, 175, 186);
		contentPane.add(avaLabel);
		
		JTextPane connectPanel = new JTextPane();
		connectPanel.setFont(new Font("Roboto Light", Font.PLAIN, 19));
		connectPanel.setEditable(false);
		connectPanel.setBounds(211, 13, 453, 211);
		contentPane.add(connectPanel);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setFont(new Font("Roboto Light", Font.PLAIN, 23));
		lblPort.setBounds(85, 264, 68, 28);
		contentPane.add(lblPort);
		
		portTextField = new JTextField();
		portTextField.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		portTextField.setColumns(10);
		portTextField.setBounds(211, 255, 453, 49);
		contentPane.add(portTextField);
		
		//start server
		JButton connectBtn = new JButton("Connect");
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				port = Integer.parseInt(portTextField.getText());
				System.out.println(port);
				try {
					server = new Server(port);
					server.excute();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Cannot run server!!!");
				}
			}
		});
		connectBtn.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		connectBtn.setBounds(112, 330, 145, 49);
		contentPane.add(connectBtn);
		
		JButton disconnectBtn = new JButton("Disconnect");
		disconnectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				server.close();
			}
		});
		disconnectBtn.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		disconnectBtn.setBounds(486, 330, 145, 49);
		contentPane.add(disconnectBtn);
	}
	
	
	//Server chat
	public class Server {
		int port;
		ServerSocket ss;
		public Server(int port)
		{
			this.port = port;
			
		}
		
		public void excute() throws IOException
		{
			ss= new ServerSocket(port);
			System.out.println("Waiting for client at "+ss.getLocalPort());
			while (true)
			{
				
				Socket s=ss.accept();
				
				list.add(s);
				readnsendServer x = new readnsendServer(s);
				x.start();
			}
		}
		
		public void close()
		{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot close!");
			}
		}
		
		class readnsendServer extends Thread
		{
			Socket s;
			DataInputStream dis = null;
			public readnsendServer(Socket s)
			{
				this.s=s;
			}
			public void run()
			{
				try {
					dis = new DataInputStream(s.getInputStream());
					while(true)
					{
						String received = dis.readUTF();
						for(Socket i : Server_Start_Form.list)
						{
							
								DataOutputStream dos = new DataOutputStream(i.getOutputStream());
								dos.writeUTF(received);
							
							
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}


	
	
	
}