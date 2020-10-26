package Travel;

import java.util.Scanner;

public class TravelMain {

	private static TravelList travelList;

	public static void main(String[] args) {

		TravelList travelList = new TravelList();
		Scanner sc = new Scanner(System.in);
		TravelSQL sql = new TravelSQL();
		sql.connect();
		boolean run = true;

		while (run) {

			System.out.println("메뉴를 선택해주세요.");
			System.out.println("┌───────────────┬───────────────┬──────────────┬──────────────┐");
			System.out.println("│  1. 여행자 후기    │  2. 나라 목록       │  3. 랜덤 추천     │  4. 취향 별 추천 │");
			System.out.println("├───────────────┼───────────────┼──────────────┼──────────────┤");
			System.out.println("│  5. 여행지 검색    │  6. 고객 센터       │  7. 관리자모드   │  8. 종료            │");
			System.out.println("└───────────────┴───────────────┴──────────────┴──────────────┘");
			System.out.print("메뉴 번호 입력 >> ");
			int menu = sc.nextInt();
			System.out.println();

			switch (menu) { // 3번 switch-case문 [여행지 후기, 여행지 목록, 랜덤, 취향, 검색, 종료]
			case 1: // 여행자 후기
				System.out.println("┌────────────────┬────────────────┐");
				System.out.println("│  1. 후기 입력         │   2. 후기 보기       │ ");
				System.out.println("└────────────────┴────────────────┘");
				System.out.print("번호 입력 >> ");
				int reNum = sc.nextInt();
				System.out.println();
				switch (reNum) { // 6번 switch-case문 [후기 입력, 후기 보기]
				case 1:
					System.out.println("후기를 작성할 관광지 조회해주세요. ");
					System.out.print("관광지 >> ");
					String r_attraction = sc.next();
					System.out.println();
					travelList.setAttraction(r_attraction);
					boolean check = sql.attraction(r_attraction);

					if (check) {
						System.out.println("후기 입력이 가능합니다.");
						System.out.print("후기 입력 >> ");
						String review1 = sc.next();
						travelList.setReview(review1);
						sql.insertReview(r_attraction, review1);
						
					} else {
						System.out.println("일치한 장소가 없습니다.");
					}
					break;
				case 2:
					System.out.println("후기를 조회할 관광지를 입력하세요.");
					System.out.print("관광지 >> ");
					String pnum = sc.next();
					System.out.println();
					boolean Rcheck = sql.Rattraction(pnum);
					if (Rcheck) {
						System.out.println("입력된 관광지가 있습니다.");
						sql.reviewJoin(pnum);
						System.out.println();
					} else {
						System.out.println("입력된 관광지가 없습니다.");
						System.out.println();
					}

					break;
				}
				break;

			case 2: // 관광지 목록 조회
				System.out.println("현재 등록 되어 있는 나라입니다.");
				System.out.println();
				sql.travelList();
				break;

			case 3: // 랜덤 추천
				System.out.println(" 랜덤으로 여행지를 추천합니다.");
				System.out.println("해외와 국내를 선택해주세요.");
				System.out.println("┌────────────────┬────────────────┐");
				System.out.println("│      1. 해외        │      2. 국내        │ ");
				System.out.println("└────────────────┴────────────────┘");
				System.out.print("번호 입력 >> ");
				int rMenuNum = sc.nextInt();
				System.out.println();

				switch (rMenuNum) { // 4번 switch-case문 [랜덤 해외, 랜덤 국내]
				case 1: // 랜덤 해외
					sql.recommendRandom(rMenuNum);
					System.out.println();
					break;

				case 2: // 랜덤 국내
					sql.recommendRandom(rMenuNum);
					System.out.println();
					break;

				default: // 다른 번호
					System.out.println("잘못 입력 하였습니다.");
					System.out.println("다시 입력해 주세요.");
					System.out.println();
					break;
				}
				break;
			case 4: // 취향 별 추천
				System.out.println("Q1.해외와 국내를 선택해주세요.");
				System.out.println("┌──────────────────┬──────────────────┐");
				System.out.println("│      1. 해외            │      2. 국내            │ ");
				System.out.println("└──────────────────┴──────────────────┘");
				System.out.print("번호 입력 >> ");
				int pMenuNum = sc.nextInt();
				System.out.println();

				switch (pMenuNum) { // 5번 switch-case문 [취향 해외, 취향 국내]
				case 1: // 취향 해외
					System.out.println();
					System.out.println("Q2. 원하시는 대륙을 선택해주세요.");
					System.out.println("┌───────────────┬───────────────┬───────────────┐");
					System.out.println("│  1. 아시아           │  2. 유럽              │  3. 오세아니아     │");
					System.out.println("├───────────────┼───────────────┼───────────────┤");
					System.out.println("│  4. 남아메리카     │  5. 북아메리카     │  6. 아프리카        │");
					System.out.println("└───────────────┴───────────────┴───────────────┘");
					System.out.print("번호 입력 >> ");
					String recommendNum1 = sc.next();
					System.out.println();
					System.out.println("Q3. 원하시는 여행 스타일을 선택해주세요.");
					System.out.println("┌────────────────┬────────────────┐");
					System.out.println("│      1. 휴양        │     2. 활동적       │ ");
					System.out.println("└────────────────┴────────────────┘");
					System.out.print("번호 입력 >> ");
					String recommendNum2 = sc.next();
					System.out.println();
					System.out.println("Q4. 원하시는 여행지를 선택해주세요.");
					System.out.println("┌────────────────┬────────────────┐");
					System.out.println("│      1. 도심        │      2. 자연        │ ");
					System.out.println("└────────────────┴────────────────┘");
					System.out.print("번호 입력 >> ");
					String recommendNum3 = sc.next();
					System.out.println();
					sql.Taste2(pMenuNum, recommendNum1, recommendNum2, recommendNum3);
					break;

				case 2: // 취향 해외
					System.out.println("Q2. 원하시는 지역을 선택해주세요.");
					System.out.println("┌─────────────┬─────────────┬─────────────┐");
					System.out.println("│  1. 수도권       │  2. 강원도       │  3. 전라도       │");
					System.out.println("├─────────────┼─────────────┼─────────────┤");
					System.out.println("│  4. 충청도       │  5. 제주도       │  6. 경상도       │");
					System.out.println("└─────────────┴─────────────┴─────────────┘");
					System.out.print("번호 입력 >> ");
					String k_recommendNum1 = sc.next();
					System.out.println();
					System.out.println("Q3. 원하시는 여행 스타일을 선택해주세요.");
					System.out.println("┌────────────────┬────────────────┐");
					System.out.println("│      1. 휴양        │     2. 활동적       │");
					System.out.println("└────────────────┴────────────────┘");
					System.out.print("번호 입력 >> ");
					String k_recommendNum2 = sc.next();
					System.out.println();
					System.out.println("Q4. 원하시는 여행지를 선택해주세요.");
					System.out.println("┌────────────────┬────────────────┐");
					System.out.println("│      1. 도심        │      2. 자연        │ ");
					System.out.println("└────────────────┴────────────────┘");
					System.out.print("번호 입력 >> ");
					String k_recommendNum3 = sc.next();
					System.out.println();
					sql.Taste2(pMenuNum, k_recommendNum1, k_recommendNum2, k_recommendNum3);
					break;

				default: // 다른 번호
					System.out.println("잘못 입력 하였습니다.");
					System.out.println("다시 입력해 주세요.");
					System.out.println();
					break;
				}
				break;

			case 5: // 나라명 검색
				System.out.println("나라별 관광지를 검색합니다.");
				System.out.println("찾고 싶은 나라명을 입력해 주세요.");
				System.out.print("나라명 입력 >> ");
				String country = sc.next();
				System.out.println();
				boolean check = sql.ckCountry(country);
				if (check) {
					System.out.println();
					sql.sealch(country);
				} else {
					System.out.println("등록되지 않은 나라입니다.");
					System.out.println("등록을 원하시면 고객센터로 문의해주세요.");
				}
				break;

			case 6: // 고객센터
				System.out.println("문의 하실 내용을 선택하세요");
				System.out.println("┌───────────────┬───────────────┬─────────────┐");
				System.out.println("│  1. 후기 문의       │  2. 목록 문의       │  3. 추천 문의   │");
				System.out.println("├───────────────┼───────────────┼─────────────┤");
				System.out.println("│  4. 취향 문의       │  5. 검색 문의       │  6.기타 문의     │");
				System.out.println("└───────────────┴───────────────┴─────────────┘");
				System.out.print("문의 내용 선택>>");
				int service = sc.nextInt();
				System.out.println();
				switch (service) {
				case 1:
					System.out.println("후기 관련 문의는 ehdtjs214@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				case 2:
					System.out.println("목록 관련 문의는 su8552@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				case 3:
					System.out.println("랜덤추천 관련 문의는 leji02789@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				case 4:
					System.out.println("취향추천 관련 문의는 ehdtjs214@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				case 5:
					System.out.println("검색 관련 문의는 wnsgml3399@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				case 6:
					System.out.println("기타 문의는 su8552@naver.com으로 남겨주세요.");
					System.out.println("감사합니다.");
					System.out.println();
					break;
				default:

					System.out.println();
					System.out.println("잘못 입력 하였습니다.");
					System.out.println("다시 입력해 주세요.");
					System.out.println();
					break;

				}
				break;

			case 7: // 관리자모드
				System.out.println("관리자모드입니다.");
				System.out.println("패스워드를 입력하세요.");
				System.out.print("패스워드 입력 >>");
				String password = sc.next();
				System.out.println();
				if (password.equals("양준희")) {
					sql.management();
					System.out.println("메뉴를 선택해주세요.");
					System.out.println("┌───────────────┬───────────────┐");
					System.out.println("│  1. 여행지 추가    │  2. 여행지 삭제    │ ");
					System.out.println("└───────────────┴───────────────┘");
					System.out.print("메뉴 입력>>");
					int mMenu = sc.nextInt();
					System.out.println();
					// 2번 switch-case문 [여행지 입력, 여행지 삭제]
					switch (mMenu) {
					case 1: // 여행지 입력
						System.out.println("여행지 정보를 입력해주세요.");
						String lastNum = sql.lastNum();					
						System.out.println();

						System.out.print("관광지 번호 입력 >> ");
						String privatenum = sc.next();
						travelList.setPrivatenum(privatenum);
						System.out.println();

						String conNum = sql.conNum();
						System.out.print("나라 번호 입력 >> ");
						String countryNum = sc.next();
						travelList.setCountryNum(countryNum);
						System.out.println();

						System.out.print("나라 입력 >> ");
						String country1 = sc.next();
						travelList.setCountry(country1);
						System.out.println();

						System.out.print("도시 입력 >> ");
						String city = sc.next();
						System.out.println();
						travelList.setCity(city);

						System.out.print("관광지 입력 >> ");
						String attraction = sc.next();
						travelList.setAttraction(attraction);

						System.out.println(" 1. 아시아     │ 2. 유럽        │ 3. 오세아니아 │4. 남아메리카│ 5. 북아메리카│6. 아프리카 ");
						System.out.println();
						System.out.println(
								" 1. 수도권     │ 2. 강원도     │ 3. 전라도       │4. 충청도       │5. 제주도       │6. 경상도 ");

						System.out.print("대륙 or 지역 번호 입력 >> ");
						String recommend1 = sc.next();
						travelList.setRecommend1(recommend1);

						System.out.println("     1. 휴양        │      2. 활동         ");
						System.out.print("여행 스타일 번호 입력 >> ");
						String recommend2 = sc.next();
						travelList.setRecommend2(recommend2);

						System.out.println("     1. 도심        │      2. 자연         ");
						System.out.print("여행지 번호 입력 >> ");
						String recommend3 = sc.next();
						travelList.setRecommend3(recommend3);
						sql.insertAttraction(travelList);
						break;

					case 2: // 여행지 삭제
						System.out.println("삭제할 여행지를 선택 해주세요.");
						System.out.print("관광지 입력 >> ");
						String dAttraction = sc.next();
						sql.delete(dAttraction);
						break;
					default: // 다른 번호
						System.out.println("잘못 입력 하였습니다.");
						System.out.println("다시 입력해 주세요.");
						System.out.println();
						break;
					}
					break;
				} else {
					System.out.println("비밀번호가 일치하지않습니다.");
					break;
				}
			case 8:
				// System.out.println("프로그램을 종료합니다.");
				sql.conClose();
				System.out.println("여행지 추천 프로그램을 종료합니다.");
				System.out.println();
				run = false;
				break;
			default:
				System.out.println();
				System.out.println("잘못 입력 하였습니다.");
				System.out.println("다시 입력해 주세요.");
				System.out.println();
				break;

			}
		}
	}
}
