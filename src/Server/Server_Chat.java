package Server;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server_Chat extends Thread {
	Socket s;
	ArrayList<DataOutputStream> listDOS;
	DataOutputStream dos;
	DataInputStream dis;

	public Server_Chat(Socket s, ArrayList<DataOutputStream> lDOS) throws Exception
	{
		this.s=s;
		this.listDOS = lDOS;
		this.dos= new DataOutputStream(s.getOutputStream());
		this.dis=new DataInputStream(s.getInputStream());
	}

	@Override
	public void run() {
		try {
			String recieved = dis.readUTF();
			for(DataOutputStream dos :listDOS)
			{
				dos.writeUTF(recieved);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
