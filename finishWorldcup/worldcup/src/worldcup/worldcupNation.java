package worldcup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class worldcupClient extends Thread{
	Socket socket;
	String name;
	static String url = "jdbc:mysql://192.168.0.45:3306/scottdb?useSSL=false";
    static String dbId = "scott";
    static String dbPwd = "tiger";
    Connection conn = Match.getConnectivity(url, dbId, dbPwd);
    PreparedStatement pst = null;
    int dtime = 0;
    String state = "";
    
	public worldcupClient(Socket socket, String name){
		super();
		this.socket = socket;
		this.name = name;
	}
	
	public void run() {
		PrintWriter newPw;

	      try {
	         Match.DeleteTable(conn, pst, this.name); // ���� ������ ����
	         Match.MakePlayerTable(conn, pst, this.name); // ���� ����
	         Match.DefaultMessage(conn, pst, this.name); // �⺻ ����� ����

	         System.out.println();
	         Match.MakeRandomInjury(conn, pst, this.name); // ���� �λ��� �߻�
	         Match.MinusConditionDis(conn, pst, this.name, dtime);
	         Match.MinusConditionState(conn, pst, this.name, state);
	         double result = MakeBestEleven.MakeBestEleven(conn, pst, this.name)
	               * MakeBestEleven.CoachAvility(conn, pst, this.name) / 100; //����� ���ϴ°�

	         newPw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	         newPw.println(this.name);
	         newPw.println(result);
	         newPw.flush();
	         newPw.close();

	         try {
	            Thread.sleep(5000);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	         
	         Match.ShowGameResult(conn, pst, this.name);

	      } catch (NumberFormatException | SQLException | IOException e) {
	         e.printStackTrace();
	      }

	   }

}

public class worldcupNation{
	
	public static void main(String[] args) {
		 Socket socketOfNation = null;
	      String nationName;
	      Scanner scan = new Scanner(System.in);
	      System.out.println("������� �ö�� �����Է� >> ");
	      nationName = scan.next();
	      try {
	         socketOfNation = new Socket("192.168.0.45", 9999);

	         PrintWriter pw = new PrintWriter(new OutputStreamWriter(socketOfNation.getOutputStream()));
	         pw.println(nationName);
	         pw.flush();

	         worldcupClient wcw = new worldcupClient(socketOfNation, nationName); //���Ͽ� �����̸� �־ ���� ������ �����ϱ� ����
	         wcw.start();
	         
	      } catch (IOException e) {
	      }

		
	}
}
