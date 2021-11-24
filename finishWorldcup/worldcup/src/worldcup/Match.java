package worldcup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class MakeBestEleven {
   private String b_firstname;
   private String b_lastname;
   private String b_pom;
   private int b_stat;
   private int b_backnum;
   private int b_age;
   private String b_nName;
   private double b_con;
   private boolean b_inj;

   public MakeBestEleven() {
   }

   public void setB_firstname(String b_firstname) {
      this.b_firstname = b_firstname;
   }

   public void setB_lastname(String b_lastname) {
      this.b_lastname = b_lastname;
   }

   public void setB_pom(String b_pom) {
      this.b_pom = b_pom;
   }

   public void setB_stat(int b_stat) {
      this.b_stat = b_stat;
   }

   public int getB_stat() {
      return b_stat;
   }

   public double getB_con() {
      return b_con;
   }

   public void setB_backnum(int b_backnum) {
      this.b_backnum = b_backnum;
   }

   public void setB_age(int b_age) {
      this.b_age = b_age;
   }

   public void setB_nName(String b_nName) {
      this.b_nName = b_nName;
   }

   public void setB_con(double b_con) {
      this.b_con = b_con;
   }

   public void setB_inj(boolean b_inj) {
      this.b_inj = b_inj;
   }

   public static double CoachAvility(Connection conn, PreparedStatement pst, String name) {// 감독 역량
      ResultSet rs = null;
      int k = 0;
      String sql = "select n_co_com from nation where n_name = ?";//국가별 감독역량 가져오기

      try {
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         rs.next();

         k = rs.getInt(1);//감독 역량스탯을 k에 저장
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return k;
   }

   public static double MakeBestEleven(Connection conn, PreparedStatement pst, String name) {
      // 베스트 11 만들고 팀 평가값 반환 코드
	 //부상 아닌애중에 컨디션 젤 좋은애 포지션별로 뽑기
      ResultSet rs = null;
      Statement st = null;
      ArrayList<MakeBestEleven> mbe = new ArrayList<MakeBestEleven>();//포지션 별로 뽑은애들 저장시킬 공간
      Double sum = 0.0;
      String sql = "";
      try {//부상 당하지 않은 선수중 컨디션이 제일 좋은 골기퍼 1명 뽑기
         sql = "select * from player where n_name = ? and p_INJ = 0 and p_POM='gk' order by p_STAT * p_COND desc limit 1 ";
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         while (rs.next()) {//조건에 맞는 애 다 넣어질 때까지 while문 돌리기
            MakeBestEleven m = new MakeBestEleven();
            m.setB_firstname(rs.getString(1));
            m.setB_lastname(rs.getString(2));
            m.setB_pom(rs.getString(3));
            m.setB_stat(rs.getInt(4));
            m.setB_backnum(rs.getInt(5));
            m.setB_age(rs.getInt(6));
            m.setB_nName(rs.getString(7));
            m.setB_con(rs.getDouble(8));
            m.setB_inj(rs.getBoolean(9));
            mbe.add(m);// mbe 어레이 리스트에 전부 저장
         }

         sql = "select * from player where n_name = ? and p_INJ =0 and p_POM='df' order by p_STAT * p_COND  desc limit 4 ";

         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         while (rs.next()) {
            MakeBestEleven m = new MakeBestEleven();
            m.setB_firstname(rs.getString(1));
            m.setB_lastname(rs.getString(2));
            m.setB_pom(rs.getString(3));
            m.setB_stat(rs.getInt(4));
            m.setB_backnum(rs.getInt(5));
            m.setB_age(rs.getInt(6));
            m.setB_nName(rs.getString(7));
            m.setB_con(rs.getDouble(8));
            m.setB_inj(rs.getBoolean(9));
            mbe.add(m);
         }

         sql = "select * from player where n_name = ? and p_INJ =0 and p_POM='mf' order by p_STAT * p_COND  desc limit 4 ";

         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         while (rs.next()) {
            MakeBestEleven m = new MakeBestEleven();
            m.setB_firstname(rs.getString(1));
            m.setB_lastname(rs.getString(2));
            m.setB_pom(rs.getString(3));
            m.setB_stat(rs.getInt(4));
            m.setB_backnum(rs.getInt(5));
            m.setB_age(rs.getInt(6));
            m.setB_nName(rs.getString(7));
            m.setB_con(rs.getDouble(8));
            m.setB_inj(rs.getBoolean(9));
            mbe.add(m);
         }

         sql = "select * from player where n_name = ? and p_INJ =0 and p_POM='fw' order by p_STAT * p_COND  desc limit 2 ";

         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         while (rs.next()) {
            MakeBestEleven m = new MakeBestEleven();
            m.setB_firstname(rs.getString(1));
            m.setB_lastname(rs.getString(2));
            m.setB_pom(rs.getString(3));
            m.setB_stat(rs.getInt(4));
            m.setB_backnum(rs.getInt(5));
            m.setB_age(rs.getInt(6));
            m.setB_nName(rs.getString(7));
            m.setB_con(rs.getDouble(8));
            m.setB_inj(rs.getBoolean(9));
            mbe.add(m);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      for (int i = 0; i < mbe.size(); i++) {//best일레븐 arrayList에 저장된 전체 선수들의 컨디션*능력을 다 더함 for문 끝날때까지
         sum += (mbe.get(i).getB_con() * mbe.get(i).getB_stat());
       //mbe에 포지션별 젤 나은 11명 뽑힌애들 다 저장되어있으므로 index별로
         // 컨디션과 stat값을 가져와서 곱해줌
      }

      return sum;
   }

}

public class Match {
   static Scanner scan = new Scanner(System.in);
   public static Random rd = new Random();

   public static void main(String[] args) {

   }

   public static Connection getConnectivity(String url, String dbId, String dbPwd) {
      // DB 연결
      try {
         return DriverManager.getConnection(url, dbId, dbPwd);
      } catch (SQLException e) {

         e.printStackTrace();
      }
      return null;
   }

   public static void MakePlayerTable(Connection conn, PreparedStatement pst, String Name)
         // 텍스트 파일에서 선수 정보 받아와 디비 저장하는 부분
         throws SQLException, NumberFormatException, IOException {
      BufferedReader br = null;
      String line = null;
      FileInputStream f = null;
      InputStreamReader isr = null;
      String[] s = null;
      String sql = "";
      f = new FileInputStream("c:/temp/" + Name + ".txt");  //우리의 c:/temp 경로에는 국가별 txt가 있음
      isr = new InputStreamReader(f, "UTF-8"); // f를 한글로 불러올 수 있도록 utf-8로 설정
      br = new BufferedReader(isr); //텍스트파일을 읽어오기
      while ((line = br.readLine()) != null) {// 텍스트 파일을 줄별로 읽는데 null이기 전까지 읽음
         s = line.split("\\s"); // 스페이스 한 칸 별로 나눠준 것을 배열 s에 저장
         sql = "insert into player (p_FIRSTNAME, p_LASTNAME, p_POM, p_STAT, P_BACKNUM, p_AGE, n_NAME, p_COND, p_INJ) "
               + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       //계속 배열s를 텍스트파일 읽는게 끝날때까지 넣어줌
         pst = conn.prepareStatement(sql);
         pst.setString(1, s[0]);
         pst.setString(2, s[1]);
         pst.setString(3, s[2]);
         pst.setInt(4, Integer.parseInt(s[3]));
         pst.setInt(5, Integer.parseInt(s[4]));
         pst.setInt(6, Integer.parseInt(s[5]));
         pst.setString(7, s[6]);
         pst.setDouble(8, Double.parseDouble(s[7]));
         pst.setBoolean(9, Boolean.parseBoolean(s[8]));
         // System.out.println("정보가 추가되었습니다.");

         pst.executeUpdate();

      }
      br.close();
   }

   public static void MinusConditionDis(Connection conn, PreparedStatement pst, String name, int dtime) {

      // 입력받아 선수단의 상태를 조정하는 부분
      System.out.println("선수단 숙소에서 경기장의 이동시간을 입력하시요 >> ");
      dtime = scan.nextInt();
      try {
         if (dtime > 4) {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 4);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("이동시간이 4시간을 초과하여 선수단 컨디션이 4 하락합니다");
         } else if (dtime > 2) {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 2);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("이동시간이 2시간을 초과하여 선수단 컨디션이 2 하락합니다");
         } else {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("이동시간이 2시간 이하로 선수단 컨디션이 1 하락합니다");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void MinusConditionState(Connection conn, PreparedStatement pst, String name, String state) {
      System.out.println("선수단의 상태는 어떤가요? <GOOD , SOSO , BAD>중에서 선택하시오 : ");
      state = scan.next();

      try {
         if ("GOOD".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 91) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("선수단 컨디션이 좋음으로 설정되었습다");
         } else if ("SOSO".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 81) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("선수단 컨디션이 보통으로 설정되었습다");
         } else if ("BAD".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 71) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("선수단 컨디션이 나쁨으로 설정되었습니다");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void DefaultMessage(Connection conn, PreparedStatement pst, String name) {
      // 온도 영향 부분
      ResultSet rs1 = null; // ResultSet클래스는 sql문의 실행결과를 얻어오기위해 사용
      try {
         String sql = "select (low_avg_t + heigh_avg_t)/2 from weather where nation = '러시아'";
         //개최국인 러시아의 평균 온도를 구해옴
         pst = conn.prepareStatement(sql);
         rs1 = pst.executeQuery();
         rs1.next();
         double rw = rs1.getDouble(1);
         System.out.printf("경기날 러시아의 기온은 %.1f도 입니다.\n", rs1.getDouble(1));

         sql = "select (low_avg_t + heigh_avg_t)/2 from weather where nation = ? ";//입력한 국가의 평균 온도를 구해옴
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();
         double iw = rs1.getDouble(1);
         System.out.printf("%s의 7월 평균 기온은 %.1f도 입니다.\n", name, rs1.getDouble(1));
         System.out.println("국가간 온도차는 선수의 퍼포먼스에 영향을 줄수있습니다!!");
         double ju = Math.abs(iw - rw); //평균온도 차의 절대값을 ju에 저장
         if (ju > 3) {// 평균온도 차의 절대값이 3을 넘는다면
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 4); //컨디션을 4만큼 깎겠다
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s는 러시아와 %.1f도 차이가나 선수들의 컨디션이 하락하였음.\n", name, ju);
         } else if (ju > 2) {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s는 러시아와 %.1f도 차이가나 선수들의 컨디션이 하락하였음.\n", name, ju);
         } else {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s는 러시아와 %.1f도 차이가나 선수들의 컨디션이 하락하였음.\n", name, ju);
         }
         System.out.println();
         /////////////////////////////////////////////////////////
         // 국가간 거리로 컨디션 저하 코드
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         sql = "select n_Name,dis,mov_t,dif_t from nation where n_Name = ?"; //입력한 국가이름에 따른 
         //국가이름,이동거리,이동시간,시차 가져온다.

         pst = conn.prepareStatement(sql);//커넥션에 sql구문을 준비시키는 것 = pst
         pst.setString(1, name); // 첫번째 ? 에 name을 집어넣음
         rs1 = pst.executeQuery();
         rs1.next();
         System.out.printf("%s는 러시아 까지 거리가 %dkm이고 이동시간은 %d시간 소요, 시차는 %d시간입니다\n", rs1.getString(1), rs1.getInt(2),
               rs1.getInt(3), rs1.getInt(4));

         ////////////////////////////////////////////////////////////
         // 선수 나이로 인한 컨디션 저하 코드
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select avg(p_AGE) from player where n_Name = ?";//국가별 평균 선수 나이를 구해옴
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();

         if (rs1.getDouble(1) > 28) {//평균 나이가 28살을 초과하면
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2);//컨디션을 2만큼 깎겠다
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("선수단 평균 나이는 %.1f 입니다. 선수단 나이에 따른 체력 저하 발생! \n", rs1.getDouble(1));
         } else if (rs1.getDouble(1) <= 28) {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("선수단 평균 나이는 %.1f 입니다. 선수단 나이에 따른 체력 저하 발생!\n", rs1.getDouble(1));
         }
         sql = "update player set p_COND = p_COND - ? where n_NAME= ? and p_AGE > 33"; //선수의 나이가 33살을 넘으면 컨디션 깎기
         pst = conn.prepareStatement(sql);
         pst.setDouble(1, 0.5); // 컨디션을 0.5씩 깎겠다
         pst.setString(2, name);
         pst.executeUpdate();
         System.out.println("33살 이상의 선수는 추가 체력 저하 발생!");
         ////////////////////////////////////////////////////
         // 전 경기일 휴식
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select m_DATE   from match_d where a_Nation = ? or  b_Nation = ? order by m_DATE";//이전 경기 날짜 조회
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         pst.setString(2, name);
         rs1 = pst.executeQuery();
         rs1.next();
         System.out.println("해당 국가의 이전 경기일을 조회합니다! >> 이전 경기일은" + rs1.getDate(1) + " 입니다.");
         DateFormat df = new SimpleDateFormat("dd"); //날짜- 일(day)만 뽑는 format
         Date rdate1 = rs1.getDate(1);
         String str1 = df.format(rdate1);//df의 형식에 맞게 이전 경기일을 바꿔줌
         rs1.next();
         Date rdate2 = rs1.getDate(1);
         String str2 = df.format(rdate2);//order by되어있기 때문에 결승 날짜를 가져옴
         int difd = Integer.parseInt(str2) - Integer.parseInt(str1);
         System.out.println(name + "은 " + (difd - 1) + " 일의 휴식을 가졌습니다. 선수들의 컨디션이 회복됩니다!");
         sql = "update player set p_COND = p_COND + ? where n_NAME= ?";
         pst = conn.prepareStatement(sql);
         pst.setDouble(1, (difd - 1) * 2); // 휴일수*2만큼 컨디션을 더해줌
         pst.setString(2, name);
         pst.executeUpdate();
         //////////////////////////////////////////////////////
         // 전 경기 영향 (연장)
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select m_DELAY   from match_d where a_Nation = ? or  b_Nation = ? and m_STAGE = '4강' ";//연장전 진행 여부를 가져옴
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         pst.setString(2, name);
         rs1 = pst.executeQuery();
         rs1.next();
         if (rs1.getInt(1) == 0) {
            // 연장하지 않음
            System.out.println("이전경기에 연장없이 경기를 종료하였습니다");

         } else if (rs1.getInt(1) == 1) {
            // 연장함
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2); // 연장승부로 체력저하 --> 5만큼 컨디션 깎음
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("이전경기에 연장으로 경기를 종료하였습니다 소폭 체력이 저하됩니다");
         }
         //////////////////////////////////////////////////////
         // 전 경기 영향 (점수차)
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select a_score , b_score   from match_d where a_Nation = ? and m_STAGE = '4강' ";
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();
         int difs = rs1.getInt(1) - rs1.getInt(2);//이긴 국가 점수-패배한 국가 점수
         System.out.println("이전경기를 " + difs + "점수차로 승리하였습니다.");
         if (difs >= 3) {//점수차가 3 이상이면
            System.out.println("압도적인 점수차로 산수들의 사기가 상승하였습니다.");
            sql = "update player set p_COND = p_COND + ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 8); // 압도적인 점수차로 사기충전 -> 컨디션을 8만큼 더하겠다
            pst.setString(2, name);
            pst.executeUpdate();
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void MakeRandomInjury(Connection conn, PreparedStatement pst, String name) {
	 //부상자 만드는 메소드
      ResultSet rs = null;
      ArrayList<String> al = new ArrayList<String>();
      String sql = "select p_firstname  from player where n_name = ?  ";//국가에 맞는 선수 이름을 골라줌
      Random rd = new Random();
      int v = rd.nextInt(4);// 0부터 3까지 총 4개의 정수가 랜덤으로 1회 뽑힘 = 부상자 수 결정.
      int R[] = new int[v]; //부상자 수에 따라 배열크기 결정

      for (int i = 0; i < v; i++) { //배열의 크기만큼 for문을 돌린다
         R[i] = rd.nextInt(23);//선수가 23명이니까 0~22까지의 번호를 뽑은 것을 R[i]에 넣는다
         for (int j = 0; j < i; j++) {
            if (R[i] == R[j]) {
               i--; //번호가 중복될 시에 다시 돌리게끔
            }
         }
      }

      try {
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();//select 문을 실행하고 resultSet 객체 반환
         rs.next();

         for (int i = 0; i < 23; i++) {//한 국가 당 선수는 23명
            al.add(rs.getString(1)); //어레이리스트 al에 선수이름 차례로 넣음
            rs.next(); //rs.next()를 하면 다음 차례로 이동해서 또 첫번째 getString(1) 가능
         }

         for (int i = 0; i < v; i++) {//부상자 수만큼 for문을 돌린다.    를 update해줌
            sql = "update player set p_INJ = true where p_firstname= ?"; //player 테이블의 p_inj를 1로 수정할건데 firstname이 
            //이거인 사람만 수정
            pst = conn.prepareStatement(sql);
            pst.setString(1, al.get(R[i]));// al에는 선수의 firstname만 들어있기 때문에 for문 만큼 al의 인덱스 번호에 맞는 firstname이 들어감
            pst.executeUpdate();
         }

         if (v != 0) {//부상자가 있다면
            System.out.printf("부상자가 %d명 발생했습니다!\n부상자 : ", v);
            for (int i = 0; i < v; i++) {
               System.out.printf("%s ", al.get(R[i])); //부상자 first_name출력
            }
            System.out.println();
         } else {
            System.out.println("부상자가 발생하지 않았습니다. 좋은 페어플레이!");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void DeleteTable(Connection conn, PreparedStatement pst, String name) {
      String sql = "delete from player where n_NAME = ?";
      try {
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         pst.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public static void ShowGameResult(Connection conn, PreparedStatement pst, String name) { // 각 나라의 승패여부 출력문
      ResultSet rs = null;
      String sql = "select a_nation , b_nation, a_SCORE ,b_SCORE  from match_d where m_STAGE = '결승'  ";
      try {
         pst = conn.prepareStatement(sql);
         rs = pst.executeQuery();

         while (rs.next()) {
            if (name.equals(rs.getString(1))) {
               if (rs.getInt(3) > rs.getInt(4)) {
                  System.out.printf("%s가 %d : %d로 승리하였습니다!", rs.getString(1), rs.getInt(3), rs.getInt(4));
               } else if (rs.getInt(3) < rs.getInt(4))
                  System.out.printf("%s가 %d : %d로 패배하였습니다!", rs.getString(1), rs.getInt(3), rs.getInt(4));
            }
            if (name.equals(rs.getString(2))) {
               if (rs.getInt(3) < rs.getInt(4)) {
                  System.out.printf("%s가 %d : %d로 승리하였습니다!", rs.getString(2), rs.getInt(3), rs.getInt(4));
               } else if (rs.getInt(3) > rs.getInt(4))
                  System.out.printf("%s가 %d : %d로 패배하였습니다!", rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}