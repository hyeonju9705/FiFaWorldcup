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
	         Match.DeleteTable(conn, pst, this.name); // 나라별 선수들 제거
	         Match.MakePlayerTable(conn, pst, this.name); // 선수 삽입
	         Match.DefaultMessage(conn, pst, this.name); // 기본 컨디션 조절

	         System.out.println();
	         Match.MakeRandomInjury(conn, pst, this.name); // 돌발 부상자 발생
	         Match.MinusConditionDis(conn, pst, this.name, dtime);
	         Match.MinusConditionState(conn, pst, this.name, state);
	         double result = MakeBestEleven.MakeBestEleven(conn, pst, this.name)
	               * MakeBestEleven.CoachAvility(conn, pst, this.name) / 100; //결과값 구하는것

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
	      System.out.println("결승전에 올라온 국가입력 >> ");
	      nationName = scan.next();
	      try {
	         socketOfNation = new Socket("192.168.0.45", 9999);

	         PrintWriter pw = new PrintWriter(new OutputStreamWriter(socketOfNation.getOutputStream()));
	         pw.println(nationName);
	         pw.flush();

	         worldcupClient wcw = new worldcupClient(socketOfNation, nationName); //소켓에 나라이름 넣어서 소켓 각각을 구별하기 위함
	         wcw.start();
	         
	      } catch (IOException e) {
	      }

		
	}
}
