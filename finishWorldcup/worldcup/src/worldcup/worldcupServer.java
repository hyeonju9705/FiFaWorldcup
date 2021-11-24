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
	Connection conn = Match.getConnectivity(worldcupClient.url, worldcupClient.dbId, worldcupClient.dbPwd); //Ŭ���̾�Ʈ�� ����

	public worldcupServer(Socket socket) {
		this.socket = socket;
	      this.as.add(socket); //arrayList�� socket �߰�
	      
	         try {
	            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //���Ͽ��� �Էµ� ���� �д°� = br
	            name = br.readLine();// client������ �����̸��� �Է����� ���̱� ������ �װ��� �о�ͼ� name ������ ����
	      
	            System.out.println(name + "�� ������� �����Ͽ����ϴ�.");             
	          
	              
	         }catch(IOException e) {
	            System.out.println("�̸��� �޴� �߿� ������ �߻��Ͽ����ϴ�.");
	         }

	}
	
	public void run() {  
		 try {
	            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            String line;
	            victory = new ArrayList<String>();
	            
	            while((line = br.readLine()) != null) {
	              String[] s = line.split("\n");     // �� �� �� ���� ���� �迭�� ����  
	              for(int i = 0; i< s.length; i++) {
	                 victory.add(s[i]);// �迭�� ����� ���� �ٽ� arrayList victory�� ����
	              }
	            }
	            // ȹ���� ������ ���ھ�ȭ �ϱ����� ������
	            //���� ���� ������ִ� ���� , ¦���� �����̸�, Ȧ���� client���� ���� �� ����
	            // int������ ���� ���� �� �� ���� ���ڰ� �������̱� ������ ����ȯ
	            int a = (int)((Double.parseDouble(victory.get(1)) /(Double.parseDouble(victory.get(1)) + Double.parseDouble(victory.get(3))))*10);
	            int b = (int)((Double.parseDouble(victory.get(3)) /(Double.parseDouble(victory.get(1)) + Double.parseDouble(victory.get(3))))*10);
	           
	            int min = (a < b) ? a : b; 
	            int gcd = 0; //�ִ����� �ʱ�ȭ
	            for(int i =1; i <=min; i++) {
	               if(a % i == 0 && b % i ==0)
	                  gcd = i;
	            } // �ִ����� ���Ϸ��� �˰���
	            int a_score = a/gcd;
	            int b_score = b/gcd;
	            
	            
	            
	            
	            
	            boolean delay_play = false; //�������� ���� �� ������ ��Ȳ�� �����Ϸ��� ����
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

	            System.out.printf("�����: %d : %d ", a_score , b_score);
	            if (Double.parseDouble(victory.get(1)) > Double.parseDouble(victory.get(3))) {
	                System.out.printf("%s�¸�\n",victory.get(0));
	            } else {
	                System.out.println(victory.get(2)+" �¸�");
	            } // ������ ��� ����� ���� ��¹�

	      }catch(Exception e ) {}

	}
	
	public static void main(String[] args) {
		
		  try {     
		         ServerSocket server = new ServerSocket(9999);
		         System.out.println("����� ������ �Ǿ����ϴ�.");
		         while(true) {      
		            Socket socketOfServer = server.accept();         
		            worldcupServer wcs = new worldcupServer(socketOfServer);
		            wcs.start();
		         }
		            
		      }catch(IOException e) {}


	}
}
