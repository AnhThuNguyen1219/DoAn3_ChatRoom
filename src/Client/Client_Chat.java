package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_Chat {
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	
	public Client_Chat(String ip, int port) throws Exception
	{
		s = new Socket(ip, port);
	}
	
	public void openStream() throws Exception
	{
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}
	
	public void sendMess(String send) throws Exception
	{
		dos.writeUTF(send);
	}
	
	public String receiveMess() throws Exception
	{
		return dis.readUTF();
	}

}
