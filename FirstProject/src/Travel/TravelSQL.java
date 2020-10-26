package Travel;

import java.sql.*;
import java.util.Scanner;

public class TravelSQL {

	Scanner sc = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	TravelList tourList = new TravelList();
	Statement stmt = null;
	
//----------------------------------------------------------------------------------------------
// 데이터베이스 연결 메소드
	public void connect() {
		con = DBC.DBConnect();
	}

//----------------------------------------------------------------------------------------------
// 데이터베이스 연결 해제 메소드
	public void conClose() {
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

//----------------------------------------------------------------------------------------------
// Case1-1.후기 작성

	
	public void insertReview(String Attraction , String review) { // 여행지입력
		String sql = "UPDATE TRAVELLIST SET REVIEW=? WHERE ATTRACTION=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review);
			pstmt.setString(2, Attraction);
			

			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}	
	
	
	
	
//	case1-2.후기 보기
	
	public void reviewJoin(String pnum) {
		 try {
			   String sql = "SELECT * FROM TRAVELLIST WHERE ATTRACTION = ?";
			   pstmt = con.prepareStatement(sql);
			   pstmt.setString(1, pnum);
			   rs = pstmt.executeQuery();
			   
			   while (rs.next()) {
			    System.out.println("관광지 : " + rs.getString(5));
			    System.out.println("후기 : " + rs.getString(9));
			    System.out.println();
			   }   
			   
			  } catch (SQLException se) {
			   se.printStackTrace();
			  }
		
	}

	public boolean Rattraction(String pnum) {
		
		boolean Rcheck = false;
		String sql = "SELECT * FROM TRAVELLIST WHERE ATTRACTION = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Rcheck = true;
			} else {
				Rcheck = false;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		return Rcheck;
	}
	
	
//////////명소 있는지 확인
public boolean attraction(String attraction) {

	boolean checkResult = false;
	String sql = "SELECT * FROM TRAVELLIST WHERE ATTRACTION = ?";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, attraction);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			checkResult = true;
			System.out.println("번호 : " + rs.getString(1) +"    " + "관광지 : " + rs.getString(5));
		} else {
			checkResult = false;
		}

	} catch (SQLException se) {
		se.printStackTrace();
	}

	return checkResult;
}

	//
	public void management() { // 관리자 모드
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM TRAVELLIST";
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String lastNum() { // 마지막 PRAVATENUM 출력
		String lastNum = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT PRIVATENUM FROM TRAVELLIST ORDER BY PRIVATENUM DESC";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("마지막 번호 : " + rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "lastNum";
	}

	public String conNum() { // 나라번호 출력
		String conNum = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT DISTINCT COUNTRYNUM, COUNTRY FROM TRAVELLIST ORDER BY COUNTRYNUM";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("나라 번호 : " + rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "conNum";
	}

	public void insertAttraction(TravelList travelList) { // 여행지입력
		String sql = "INSERT INTO TRAVELLIST VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, travelList.getPrivatenum()); // PRAVATENUM
			pstmt.setString(2, travelList.getCountryNum()); // 나라번호
			pstmt.setString(3, travelList.getCountry());
			pstmt.setString(4, travelList.getCity());
			pstmt.setString(5, travelList.getAttraction());
			pstmt.setString(6, travelList.getRecommend1());
			pstmt.setString(7, travelList.getRecommend2());
			pstmt.setString(8, travelList.getRecommend3());
			pstmt.setString(9, travelList.getReview());

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 삭제
	public void delete(String dAttraction) {
		try {

			String sql = "DELETE TRAVELLIST WHERE ATTRACTION = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dAttraction);
			pstmt.executeUpdate();
			System.out.println("삭제 성공");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("삭제 실패");
		}
	}

// ----------------------------------------------------------------------------------------------
// Case2. 나라 목록 보기
	public void travelList() {
		try {
			stmt = con.createStatement();
			String sql = "SELECT DISTINCT COUNTRY FROM TRAVELLIST ORDER BY COUNTRY";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

//---------------------------------------------------------------------------------------------
// Case3. 랜덤 여행지 추천
	
	public void recommendRandom(int rNum) {
		if (rNum == 1) {
			try {
				stmt = con.createStatement();
				String sql = "SELECT DISTINCT * FROM (SELECT * FROM TRAVELLIST WHERE COUNTRYNUM != 41  ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM < 4";
				rs = stmt.executeQuery(sql);
				System.out.println();
				System.out.println("랜덤으로 검색한 결과 입니다.");
				System.out.println();
				while (rs.next()) {
					System.out.println("나라 : " + rs.getString(3));
					System.out.println("도시 : " + rs.getString(4));
					System.out.println("관광지 : " + rs.getString(5));
					System.out.println();
				}
			} catch (Exception se) {
				se.printStackTrace();
			}
		} else if (rNum == 2) {
			try {
				stmt = con.createStatement();
				String sql = "SELECT * FROM (SELECT DISTINCT * FROM TRAVELLIST WHERE COUNTRYNUM = 41 ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM < 4";
				rs = stmt.executeQuery(sql);
				System.out.println();
				System.out.println("랜덤으로 검색한 결과 입니다.");
				System.out.println();
				while (rs.next()) {
					System.out.println("도시 : " + rs.getString(4));
					System.out.println("관광지 : " + rs.getString(5));
					System.out.println();
				}
			} catch (Exception se) {
				se.printStackTrace();
			}
		} else {
			System.out.println("잘못 입력하였습니다.");
		}
	}

// ----------------------------------------------------------------------------------------------
// Case4. 취향별 추천
	
	public void Taste2(int sNum, String recommendNum1, String recommendNum2, String recommendNum3) {
		if (sNum == 1) {
			try {
				String sql = "SELECT * FROM TRAVELLIST WHERE COUNTRYNUM != 41 AND RECOMMENDNUM1 = ? AND RECOMMENDNUM2 =? AND RECOMMENDNUM3= ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, recommendNum1);
				pstmt.setString(2, recommendNum2);
				pstmt.setString(3, recommendNum3);
				rs = pstmt.executeQuery();

				System.out.println("취향에 맞는 여행지 검색 결과 입니다.");
				System.out.println();
				while (rs.next()) {
					System.out.println("나라 :" + rs.getString(3));
					System.out.println("도시 :" + rs.getString(4));
					System.out.println("관광지 :" + rs.getString(5));
					System.out.println();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} else if (sNum == 2) {
			try {
				String sql = "SELECT * FROM TRAVELLIST WHERE COUNTRYNUM = 41 AND RECOMMENDNUM1 = ? AND RECOMMENDNUM2 =? AND RECOMMENDNUM3= ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, recommendNum1);
				pstmt.setString(2, recommendNum2);
				pstmt.setString(3, recommendNum3);
				rs = pstmt.executeQuery();
				System.out.println("취향에 맞는 여행지 검색 결과 입니다.");
				System.out.println();
				while (rs.next()) {
					System.out.println("도시 :" + rs.getString(4));
					System.out.println("관광지 :" + rs.getString(5));
					System.out.println();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} else {
			System.out.println("잘못 입력하였습니다.");
		}
	}

// ----------------------------------------------------------------------------------------------
// Case5. 검색
	public boolean ckCountry(String country) {
		boolean checkResult = false;
		String sql = "SELECT * FROM TRAVELLIST WHERE COUNTRY = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, country);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return checkResult;
	}

	public void sealch(String country) {
		try {
			String sql = "SELECT * FROM TRAVELLIST WHERE COUNTRY = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, country);
			rs = pstmt.executeQuery();
			System.out.println(country + "(으)로 검색한 결과 입니다.");
			System.out.println();
			while (rs.next()) {
				System.out.println("도시 : " + rs.getString(4));
				System.out.println("관광지 : " + rs.getString(5));
				System.out.println();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}


	
}
