package Travel;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBC {
		public static Connection DBConnect() {
			Connection con=null;
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="JUNEHEE";
				String password="1111";
				con=DriverManager.getConnection(url, user, password);
				System.out.println("여행지 추천 프로그램을 시작합니다.");
				
			}catch(ClassNotFoundException cne) {
				cne.printStackTrace();
				System.out.println("DB접속 실패 : 드라이버 로딩 실패");
			}catch(SQLException se) {
				se.printStackTrace();
				System.out.println("DB접속 실패 : url,user,password 확인");
			}
			return con;
		}

}
