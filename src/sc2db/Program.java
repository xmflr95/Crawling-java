package sc2db;

import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Program {

	public static void main(String[] args) throws Exception {
//		프로그램 LOOP
		boolean keepLoop = true;
		
		String menu;
		// JsonMaker 객체 생성
		JsonMaker json = new JsonMaker();
		// keyword 받기
		List<String> keyword = json.getKeyword();
		
		// 외부망 DB객체 생성
		ExternalDB xDB = new ExternalDB();
		
		while(keepLoop) {
			menu = inputMenu();
			
			Crawl cw = new Crawl(keyword.get(0));
			
			int lastPage = cw.getLastPage();	
			
			switch(menu) {
			case "1":
				List<String> reqURL = cw.getUrlList(lastPage);				
				List<News> newsList = cw.getNewsList(reqURL);
				xDB.insertNewsData(newsList);
				break;
			case "2":
//				데이터 취합
				List<News> dbNewsList = xDB.getNewsDate();
//				JSON 내보내기
				json.setKeyword(dbNewsList);
				break;
			case "3":
//				NUMBER, TITLE 보기
				xDB.selectNewsData();
				break;
			case "4":
				System.out.println("종료합니다. Bye~~");
				xDB.closeDatabase();
				keepLoop = false;
				break;
			default:			
				System.out.println("잘못된 값이 입력되었습니다.");
			}
		}
				
	}
	
	static String inputMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("----------------------------");
        System.out.println("|           메뉴 선택                    |");
        System.out.println("----------------------------");
        System.out.println("\t1. 데이터 수집 ");
        System.out.println("\t2. 데이터 취합 ");        
        System.out.println("\t3. 데이터 간략 보기 ");
        System.out.println("\t4. 종료 ");
        System.out.print("\t선택>> ");
        String menu = sc.next();
        
        return menu;
	}

}
