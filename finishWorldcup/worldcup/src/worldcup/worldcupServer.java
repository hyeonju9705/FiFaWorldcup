package worldcup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class worldcupServer extends Thread {
	private Socket socket;
	private String name;
	private BufferedReader br;
	private PrintWriter pw;
	private ArrayList<Socket> as = new ArrayList<Socket>();
	private static ArrayList<String> victory;
	PreparedStatement pst;
	Connection conn = Match.getConnectivity(worldcupClient.url, worldcupClient.dbId, worldcupClient.dbPwd); //클라이언트와 연결

	public worldcupServer(Socket socket) {
		this.socket = socket;
	      this.as.add(socket); //arrayList에 socket 추가
	      
	         try {
	            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //소켓에서 입력된 것을 읽는것 = br
	            name = br.readLine();// client에서는 국가이름을 입력해줄 것이기 때문에 그것을 읽어와서 name 변수에 저장
	      
	            System.out.println(name + "가 결승전에 출전하였습니다.");             
	          
	              
	         }catch(IOException e) {
	            System.out.println("이름을 받는 중에 오류가 발생하였습니다.");
	         }

	}
	
	public void run() {  
		 try {
	            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            String line;
	            victory = new ArrayList<String>();
	            
	            while((line = br.readLine()) != null) {
	              String[] s = line.split("\n");     // 한 줄 씩 읽은 것을 배열에 저장  
	              for(int i = 0; i< s.length; i++) {
	                 victory.add(s[i]);// 배열에 저장된 것을 다시 arrayList victory에 저장
	              }
	            }
	            // 획득한 점수를 스코어화 하기위한 과정들
	            //점수 몇대몇 만들어주는 공식 , 짝수는 국가이름, 홀수는 client에서 계산된 총 점수
	            // int형으로 만든 것은 몇 대 몇의 숫자가 정수형이기 때문에 형변환
	            int a = (int)((Double.parseDouble(victory.get(1)) /(Double.parseDouble(victory.get(1)) + Double.parseDouble(victory.get(3))))*10);
	            int b = (int)((Double.parseDouble(victory.get(3)) /(Double.parseDouble(victory.get(1)) + Double.parseDouble(victory.get(3))))*10);
	           
	            int min = (a < b) ? a : b; 
	            int gcd = 0; //최대공약수 초기화
	            for(int i =1; i <=min; i++) {
	               if(a % i == 0 && b % i ==0)
	                  gcd = i;
	            } // 최대공약수 구하려는 알고리즘
	            int a_score = a/gcd;
	            int b_score = b/gcd;
	            
	            
	            
	            
	            
	            boolean delay_play = false; //연장전이 들어갔을 때 연장전 상황을 연출하려는 변수
	            if(a_score == b_score)
	               delay_play = true;
	            
	            String sql = "update match_d set a_nation = ?, b_nation = ?, a_score = ?, b_score = ?, m_delay  = ? where m_date ='2018-07-16'";
	            pst = conn.prepareStatement(sql);
	            pst.setString(1, victory.get(0));
	            pst.setString(2, victory.get(2));
	            pst.setInt(3, a_score);
	            pst.setInt(4, b_score);
	            pst.setBoolean(5, delay_play);            
	            pst.executeUpdate();

	            System.out.printf("경기결과: %d : %d ", a_score , b_score);
	            if (Double.parseDouble(victory.get(1)) > Double.parseDouble(victory.get(3))) {
	                System.out.printf("%s승리\n",victory.get(0));
	            } else {
	                System.out.println(victory.get(2)+" 승리");
	            } // 서버의 결과 출력을 위한 출력문

	      }catch(Exception e ) {}

	}
	
	public static void main(String[] args) {
		
		  try {     
		         ServerSocket server = new ServerSocket(9999);
		         System.out.println("결승전 당일이 되었습니다.");
		         while(true) {      
		            Socket socketOfServer = server.accept();         
		            worldcupServer wcs = new worldcupServer(socketOfServer);
		            wcs.start();
		         }
		            
		      }catch(IOException e) {}


	}
}
