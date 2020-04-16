package sc1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Program {

	public static void main(String[] args) throws Exception {
		
		List<String> keyList = parseJson();
		//keyList.get(0) = "삼성";
		
//		System.out.println(keyList.size());
		
//		for (int i = 0; i < keyList.size(); i++) {
//			Crawl cw = new Crawl(keyList.get(i));
//			
//			int lastPage = cw.getLastPage();
//			
//			List<String> reqURL = cw.getUrlList(lastPage);
//			
//			List<News> newsList = cw.getNewsList(reqURL);
//			
//			for (int j = 0; j < newsList.size(); j++)
//				System.out.println(newsList.get(j).toString());			
//		}
		
		Crawl cw = new Crawl("롯데");
		
		int lastPage = cw.getLastPage();
		
		List<String> reqURL = cw.getUrlList(lastPage);
		
//		for (String item : reqURL) {
//			System.out.println(item);
//		}
		
		List<News> newsList = cw.getNewsList(reqURL);
		
		for (int j = 0; j < newsList.size(); j++)
			System.out.println(newsList.get(j).toString());		
		
//		for (String item : keyList) {
//			System.out.println(item);
//		}
	}
	
	public static List<String> parseJson() throws Exception {
		JSONParser parser = new JSONParser();
		
		List<String> keyList = new ArrayList<String>();
		
		Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/company_keyword.json"));
		
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray keywordArray = (JSONArray) jsonObject.get("keyword");
		
		for (int i = 0; i < keywordArray.size(); i++) {
//			System.out.println("======== keyword : " + i + "========");
			JSONObject nameObject = (JSONObject) keywordArray.get(i);
			
//			System.out.println(nameObject.get("name"));
			
			String companyName = nameObject.get("name").toString();
			
			keyList.add(companyName);
		}
		
		return keyList;
	}

}
