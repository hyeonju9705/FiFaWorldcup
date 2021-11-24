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

   public static double CoachAvility(Connection conn, PreparedStatement pst, String name) {// ���� ����
      ResultSet rs = null;
      int k = 0;
      String sql = "select n_co_com from nation where n_name = ?";//������ �������� ��������

      try {
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         rs.next();

         k = rs.getInt(1);//���� ���������� k�� ����
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return k;
   }

   public static double MakeBestEleven(Connection conn, PreparedStatement pst, String name) {
      // ����Ʈ 11 ����� �� �򰡰� ��ȯ �ڵ�
	 //�λ� �ƴѾ��߿� ����� �� ������ �����Ǻ��� �̱�
      ResultSet rs = null;
      Statement st = null;
      ArrayList<MakeBestEleven> mbe = new ArrayList<MakeBestEleven>();//������ ���� �����ֵ� �����ų ����
      Double sum = 0.0;
      String sql = "";
      try {//�λ� ������ ���� ������ ������� ���� ���� ����� 1�� �̱�
         sql = "select * from player where n_name = ? and p_INJ = 0 and p_POM='gk' order by p_STAT * p_COND desc limit 1 ";
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();

         while (rs.next()) {//���ǿ� �´� �� �� �־��� ������ while�� ������
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
            mbe.add(m);// mbe ��� ����Ʈ�� ���� ����
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

      for (int i = 0; i < mbe.size(); i++) {//best�Ϸ��� arrayList�� ����� ��ü �������� �����*�ɷ��� �� ���� for�� ����������
         sum += (mbe.get(i).getB_con() * mbe.get(i).getB_stat());
       //mbe�� �����Ǻ� �� ���� 11�� �����ֵ� �� ����Ǿ������Ƿ� index����
         // ����ǰ� stat���� �����ͼ� ������
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
      // DB ����
      try {
         return DriverManager.getConnection(url, dbId, dbPwd);
      } catch (SQLException e) {

         e.printStackTrace();
      }
      return null;
   }

   public static void MakePlayerTable(Connection conn, PreparedStatement pst, String Name)
         // �ؽ�Ʈ ���Ͽ��� ���� ���� �޾ƿ� ��� �����ϴ� �κ�
         throws SQLException, NumberFormatException, IOException {
      BufferedReader br = null;
      String line = null;
      FileInputStream f = null;
      InputStreamReader isr = null;
      String[] s = null;
      String sql = "";
      f = new FileInputStream("c:/temp/" + Name + ".txt");  //�츮�� c:/temp ��ο��� ������ txt�� ����
      isr = new InputStreamReader(f, "UTF-8"); // f�� �ѱ۷� �ҷ��� �� �ֵ��� utf-8�� ����
      br = new BufferedReader(isr); //�ؽ�Ʈ������ �о����
      while ((line = br.readLine()) != null) {// �ؽ�Ʈ ������ �ٺ��� �дµ� null�̱� ������ ����
         s = line.split("\\s"); // �����̽� �� ĭ ���� ������ ���� �迭 s�� ����
         sql = "insert into player (p_FIRSTNAME, p_LASTNAME, p_POM, p_STAT, P_BACKNUM, p_AGE, n_NAME, p_COND, p_INJ) "
               + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       //��� �迭s�� �ؽ�Ʈ���� �д°� ���������� �־���
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
         // System.out.println("������ �߰��Ǿ����ϴ�.");

         pst.executeUpdate();

      }
      br.close();
   }

   public static void MinusConditionDis(Connection conn, PreparedStatement pst, String name, int dtime) {

      // �Է¹޾� �������� ���¸� �����ϴ� �κ�
      System.out.println("������ ���ҿ��� ������� �̵��ð��� �Է��Ͻÿ� >> ");
      dtime = scan.nextInt();
      try {
         if (dtime > 4) {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 4);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("�̵��ð��� 4�ð��� �ʰ��Ͽ� ������ ������� 4 �϶��մϴ�");
         } else if (dtime > 2) {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 2);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("�̵��ð��� 2�ð��� �ʰ��Ͽ� ������ ������� 2 �϶��մϴ�");
         } else {
            String sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("�̵��ð��� 2�ð� ���Ϸ� ������ ������� 1 �϶��մϴ�");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void MinusConditionState(Connection conn, PreparedStatement pst, String name, String state) {
      System.out.println("�������� ���´� �����? <GOOD , SOSO , BAD>�߿��� �����Ͻÿ� : ");
      state = scan.next();

      try {
         if ("GOOD".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 91) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("������ ������� �������� �����Ǿ�����");
         } else if ("SOSO".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 81) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("������ ������� �������� �����Ǿ�����");
         } else if ("BAD".equalsIgnoreCase(state)) {
            String sql = "update player set p_COND = p_COND * ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, (rd.nextInt(10) + 71) / 100.0);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("������ ������� �������� �����Ǿ����ϴ�");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void DefaultMessage(Connection conn, PreparedStatement pst, String name) {
      // �µ� ���� �κ�
      ResultSet rs1 = null; // ResultSetŬ������ sql���� �������� ���������� ���
      try {
         String sql = "select (low_avg_t + heigh_avg_t)/2 from weather where nation = '���þ�'";
         //���ֱ��� ���þ��� ��� �µ��� ���ؿ�
         pst = conn.prepareStatement(sql);
         rs1 = pst.executeQuery();
         rs1.next();
         double rw = rs1.getDouble(1);
         System.out.printf("��⳯ ���þ��� ����� %.1f�� �Դϴ�.\n", rs1.getDouble(1));

         sql = "select (low_avg_t + heigh_avg_t)/2 from weather where nation = ? ";//�Է��� ������ ��� �µ��� ���ؿ�
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();
         double iw = rs1.getDouble(1);
         System.out.printf("%s�� 7�� ��� ����� %.1f�� �Դϴ�.\n", name, rs1.getDouble(1));
         System.out.println("������ �µ����� ������ �����ս��� ������ �ټ��ֽ��ϴ�!!");
         double ju = Math.abs(iw - rw); //��տµ� ���� ���밪�� ju�� ����
         if (ju > 3) {// ��տµ� ���� ���밪�� 3�� �Ѵ´ٸ�
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 4); //������� 4��ŭ ��ڴ�
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s�� ���þƿ� %.1f�� ���̰��� �������� ������� �϶��Ͽ���.\n", name, ju);
         } else if (ju > 2) {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s�� ���þƿ� %.1f�� ���̰��� �������� ������� �϶��Ͽ���.\n", name, ju);
         } else {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("%s�� ���þƿ� %.1f�� ���̰��� �������� ������� �϶��Ͽ���.\n", name, ju);
         }
         System.out.println();
         /////////////////////////////////////////////////////////
         // ������ �Ÿ��� ����� ���� �ڵ�
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         sql = "select n_Name,dis,mov_t,dif_t from nation where n_Name = ?"; //�Է��� �����̸��� ���� 
         //�����̸�,�̵��Ÿ�,�̵��ð�,���� �����´�.

         pst = conn.prepareStatement(sql);//Ŀ�ؼǿ� sql������ �غ��Ű�� �� = pst
         pst.setString(1, name); // ù��° ? �� name�� �������
         rs1 = pst.executeQuery();
         rs1.next();
         System.out.printf("%s�� ���þ� ���� �Ÿ��� %dkm�̰� �̵��ð��� %d�ð� �ҿ�, ������ %d�ð��Դϴ�\n", rs1.getString(1), rs1.getInt(2),
               rs1.getInt(3), rs1.getInt(4));

         ////////////////////////////////////////////////////////////
         // ���� ���̷� ���� ����� ���� �ڵ�
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select avg(p_AGE) from player where n_Name = ?";//������ ��� ���� ���̸� ���ؿ�
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();

         if (rs1.getDouble(1) > 28) {//��� ���̰� 28���� �ʰ��ϸ�
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2);//������� 2��ŭ ��ڴ�
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("������ ��� ���̴� %.1f �Դϴ�. ������ ���̿� ���� ü�� ���� �߻�! \n", rs1.getDouble(1));
         } else if (rs1.getDouble(1) <= 28) {
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 1);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.printf("������ ��� ���̴� %.1f �Դϴ�. ������ ���̿� ���� ü�� ���� �߻�!\n", rs1.getDouble(1));
         }
         sql = "update player set p_COND = p_COND - ? where n_NAME= ? and p_AGE > 33"; //������ ���̰� 33���� ������ ����� ���
         pst = conn.prepareStatement(sql);
         pst.setDouble(1, 0.5); // ������� 0.5�� ��ڴ�
         pst.setString(2, name);
         pst.executeUpdate();
         System.out.println("33�� �̻��� ������ �߰� ü�� ���� �߻�!");
         ////////////////////////////////////////////////////
         // �� ����� �޽�
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select m_DATE   from match_d where a_Nation = ? or  b_Nation = ? order by m_DATE";//���� ��� ��¥ ��ȸ
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         pst.setString(2, name);
         rs1 = pst.executeQuery();
         rs1.next();
         System.out.println("�ش� ������ ���� ������� ��ȸ�մϴ�! >> ���� �������" + rs1.getDate(1) + " �Դϴ�.");
         DateFormat df = new SimpleDateFormat("dd"); //��¥- ��(day)�� �̴� format
         Date rdate1 = rs1.getDate(1);
         String str1 = df.format(rdate1);//df�� ���Ŀ� �°� ���� ������� �ٲ���
         rs1.next();
         Date rdate2 = rs1.getDate(1);
         String str2 = df.format(rdate2);//order by�Ǿ��ֱ� ������ ��� ��¥�� ������
         int difd = Integer.parseInt(str2) - Integer.parseInt(str1);
         System.out.println(name + "�� " + (difd - 1) + " ���� �޽��� �������ϴ�. �������� ������� ȸ���˴ϴ�!");
         sql = "update player set p_COND = p_COND + ? where n_NAME= ?";
         pst = conn.prepareStatement(sql);
         pst.setDouble(1, (difd - 1) * 2); // ���ϼ�*2��ŭ ������� ������
         pst.setString(2, name);
         pst.executeUpdate();
         //////////////////////////////////////////////////////
         // �� ��� ���� (����)
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select m_DELAY   from match_d where a_Nation = ? or  b_Nation = ? and m_STAGE = '4��' ";//������ ���� ���θ� ������
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         pst.setString(2, name);
         rs1 = pst.executeQuery();
         rs1.next();
         if (rs1.getInt(1) == 0) {
            // �������� ����
            System.out.println("������⿡ ������� ��⸦ �����Ͽ����ϴ�");

         } else if (rs1.getInt(1) == 1) {
            // ������
            sql = "update player set p_COND = p_COND - ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 2); // ����ºη� ü������ --> 5��ŭ ����� ����
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("������⿡ �������� ��⸦ �����Ͽ����ϴ� ���� ü���� ���ϵ˴ϴ�");
         }
         //////////////////////////////////////////////////////
         // �� ��� ���� (������)
         try {
            Thread.sleep(1500);
         } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         System.out.println();
         sql = "select a_score , b_score   from match_d where a_Nation = ? and m_STAGE = '4��' ";
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs1 = pst.executeQuery();
         rs1.next();
         int difs = rs1.getInt(1) - rs1.getInt(2);//�̱� ���� ����-�й��� ���� ����
         System.out.println("������⸦ " + difs + "�������� �¸��Ͽ����ϴ�.");
         if (difs >= 3) {//�������� 3 �̻��̸�
            System.out.println("�е����� �������� ������� ��Ⱑ ����Ͽ����ϴ�.");
            sql = "update player set p_COND = p_COND + ? where n_NAME= ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, 8); // �е����� �������� ������� -> ������� 8��ŭ ���ϰڴ�
            pst.setString(2, name);
            pst.executeUpdate();
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public static void MakeRandomInjury(Connection conn, PreparedStatement pst, String name) {
	 //�λ��� ����� �޼ҵ�
      ResultSet rs = null;
      ArrayList<String> al = new ArrayList<String>();
      String sql = "select p_firstname  from player where n_name = ?  ";//������ �´� ���� �̸��� �����
      Random rd = new Random();
      int v = rd.nextInt(4);// 0���� 3���� �� 4���� ������ �������� 1ȸ ���� = �λ��� �� ����.
      int R[] = new int[v]; //�λ��� ���� ���� �迭ũ�� ����

      for (int i = 0; i < v; i++) { //�迭�� ũ�⸸ŭ for���� ������
         R[i] = rd.nextInt(23);//������ 23���̴ϱ� 0~22������ ��ȣ�� ���� ���� R[i]�� �ִ´�
         for (int j = 0; j < i; j++) {
            if (R[i] == R[j]) {
               i--; //��ȣ�� �ߺ��� �ÿ� �ٽ� �����Բ�
            }
         }
      }

      try {
         pst = conn.prepareStatement(sql);
         pst.setString(1, name);
         rs = pst.executeQuery();//select ���� �����ϰ� resultSet ��ü ��ȯ
         rs.next();

         for (int i = 0; i < 23; i++) {//�� ���� �� ������ 23��
            al.add(rs.getString(1)); //��̸���Ʈ al�� �����̸� ���ʷ� ����
            rs.next(); //rs.next()�� �ϸ� ���� ���ʷ� �̵��ؼ� �� ù��° getString(1) ����
         }

         for (int i = 0; i < v; i++) {//�λ��� ����ŭ for���� ������.    �� update����
            sql = "update player set p_INJ = true where p_firstname= ?"; //player ���̺��� p_inj�� 1�� �����Ұǵ� firstname�� 
            //�̰��� ����� ����
            pst = conn.prepareStatement(sql);
            pst.setString(1, al.get(R[i]));// al���� ������ firstname�� ����ֱ� ������ for�� ��ŭ al�� �ε��� ��ȣ�� �´� firstname�� ��
            pst.executeUpdate();
         }

         if (v != 0) {//�λ��ڰ� �ִٸ�
            System.out.printf("�λ��ڰ� %d�� �߻��߽��ϴ�!\n�λ��� : ", v);
            for (int i = 0; i < v; i++) {
               System.out.printf("%s ", al.get(R[i])); //�λ��� first_name���
            }
            System.out.println();
         } else {
            System.out.println("�λ��ڰ� �߻����� �ʾҽ��ϴ�. ���� ����÷���!");
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

   public static void ShowGameResult(Connection conn, PreparedStatement pst, String name) { // �� ������ ���п��� ��¹�
      ResultSet rs = null;
      String sql = "select a_nation , b_nation, a_SCORE ,b_SCORE  from match_d where m_STAGE = '���'  ";
      try {
         pst = conn.prepareStatement(sql);
         rs = pst.executeQuery();

         while (rs.next()) {
            if (name.equals(rs.getString(1))) {
               if (rs.getInt(3) > rs.getInt(4)) {
                  System.out.printf("%s�� %d : %d�� �¸��Ͽ����ϴ�!", rs.getString(1), rs.getInt(3), rs.getInt(4));
               } else if (rs.getInt(3) < rs.getInt(4))
                  System.out.printf("%s�� %d : %d�� �й��Ͽ����ϴ�!", rs.getString(1), rs.getInt(3), rs.getInt(4));
            }
            if (name.equals(rs.getString(2))) {
               if (rs.getInt(3) < rs.getInt(4)) {
                  System.out.printf("%s�� %d : %d�� �¸��Ͽ����ϴ�!", rs.getString(2), rs.getInt(3), rs.getInt(4));
               } else if (rs.getInt(3) > rs.getInt(4))
                  System.out.printf("%s�� %d : %d�� �й��Ͽ����ϴ�!", rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}