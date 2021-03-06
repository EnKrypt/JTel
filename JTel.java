//   **************************************************************************
//   *                                                                        *
//   *  This program is free software: you can redistribute it and/or modify  *
//   *  it under the terms of the GNU General Public License as published by  *
//   *  the Free Software Foundation, either version 3 of the License, or     *
//   * (at your option) any later version.                                    *
//   *                                                                        *
//   *  This program is distributed in the hope that it will be useful,       *  
//   *  but WITHOUT ANY WARRANTY; without even the implied warranty of        *
//   *  MERCHANTABILITY || FITNESS FOR A PARTICULAR PURPOSE.  See the         *
//   *  GNU General Public License for more details.                          *
//   *                                                                        *
//   *  You should have received a copy of the GNU General Public License     *
//   *  along with this program.  If not, see <http://www.gnu.org/licenses/>. *
//   *                                                                        *
//   *         (C) Arvind Kumar 2011 . All rights reserved                    *
//   **************************************************************************

import java.io.*;
import java.net.*;

public class JTel implements Runnable{
	
	static BufferedReader in;
	static BufferedReader b;
	static BufferedWriter out;
	static Socket con;
	static String host;
	static int port;

	public static void main(String args[])throws IOException{
		JTel bot=new JTel();
		bot.go();
	}
	public void go()throws IOException{
		BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Host: ");
		host=b.readLine();
		System.out.print("Port: ");
		try { port=Integer.parseInt(b.readLine()); }
		catch(Exception e){ System.out.println("Incorrect format for parameter\nTerminating"); System.exit(1); }
		try {
			con=new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		}
		catch (UnknownHostException e) {
            		System.out.println("Host not found: "+host);
            		System.exit(1);
        	} 
		catch (IOException e) {
            		System.out.println("Connection error for "+host+" on port "+port);
            		System.exit(1);
        	}
		
		
		new Thread(this).start();
		
		String send="";
		while(true){ 
			send=b.readLine();
			out.write(send+"\r\n");
			out.flush(); 
		}
	}
	public void run(){
		while(true){ try{
			System.out.println(in.readLine()); }
		catch(Exception e){
		e.printStackTrace(); }
		}
	}
}