package worldcup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class dataEx {
	
	public dataEx() {}
	
	public static void main(String[] args) {

		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");//달라지는부분
	         Connection conn = DriverManager.getConnection(
	               "jdbc:mysql://localhost:3306/worldcup?useSSL=false","project_worldcup","1234");
	        PreparedStatement pst = null;
	 		ResultSet rs = null;
	 		
	        Scanner scan = new Scanner(System.in);
	 		BufferedReader br = null;
	 		String line = null;
	 		FileInputStream f = null;
	 		InputStreamReader isr = null;
	 		String[] s = null;
	 		String sql = "";
//	 		List<String> str = null;
	 		
	 		try {
	 			
	 			System.out.println("국가이름은? ");
	 			String name = scan.next();
	 			f = new FileInputStream("c:/temp/" + name + ".txt");
	 			isr = new InputStreamReader(f,"UTF-8");
	 			br = new BufferedReader(isr);
//	 			str = new ArrayList<String>();
	 			while((line = br.readLine()) != null) {
	 				s = line.split("\\s");
//	 				for(int i = 0; i< s.length; i++) {
//	 					System.out.println(s[i]);
//	 				}
 					sql = "insert into player (p_FIRSTNAME, p_LASTNAME, p_POM, p_STAT, P_BACKNUM, p_AGE, n_NAME, p_COND, p_INJ) "
 													+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
 					
 					pst = conn.prepareStatement(sql);
					
					pst.setString(1, s[0]);
					pst.setString(2, s[1]);
					pst.setString(3, s[2]);
					pst.setInt(4, Integer.parseInt(s[3]));
					pst.setInt(5, Integer.parseInt(s[4]));
					pst.setInt(6, Integer.parseInt(s[5]));
					pst.setString(7, s[6]);
					pst.setInt(8, Integer.parseInt(s[7]));
					pst.setBoolean(9, Boolean.parseBoolean(s[8]));
					//System.out.println("정보가 추가되었습니다.");
					pst.executeUpdate();	
					
	 			}	
	 		}catch(IOException e) {
	 		}
	 		
	      }catch(ClassNotFoundException e) {
	         System.out.println(e.getMessage());
	      }
	      catch(SQLException e) {
	         e.printStackTrace();
	      }

		
		  
	}

}
