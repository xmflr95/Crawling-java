package sc2db;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonMaker {
//	JSON 처리함수
	public List<String> getKeyword() throws Exception {
//		키워드 순서대로 저장할 리스트
		List<String> keyList = new ArrayList<String>();
		
//		키워드 가져오기
		JSONParser parser = new JSONParser();
//		keyword.json 경로
		Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/keyword.json"));
		
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray keywordArray = (JSONArray) jsonObject.get("keyword");
		
		int size = keywordArray.size();
		
		for (int i = 0; i < size; i++) {
			JSONObject nameObject = (JSONObject) keywordArray.get(i);
			
			String companyName = nameObject.get("name").toString();
			
			keyList.add(companyName);
		}
		
//		for (String item : keyList) {
//			System.out.println(item);
//		}
		
		return keyList;
	}
	
	public void setKeyword(List<News> newsList) throws Exception {
		
		int size = newsList.size();
		
		JSONObject jsonObject = new JSONObject();
		
		// 전체 기사 정보 리스트
		JSONArray newsArray = new JSONArray();
		
		// 기사 하나의 정보
		JSONObject newsInfo = new JSONObject();
		
		for (int i = 0; i < size; i++) {
			News news = newsList.get(i);
			
			newsInfo.put("title", news.getTitle());
			newsInfo.put("requestURL", news.getRequestURL());
			newsInfo.put("date", news.getDate());
			newsInfo.put("content", news.getContent());
			newsInfo.put("media", news.getMedia());
			
			// JsonArray에 기사 하나 정보 저장
			newsArray.add(newsInfo);
			// 다음 정보를 위한 초기화
			newsInfo = new JSONObject();
		}
		
//		"news" 키워드 obejct에 배열 저장
		jsonObject.put("news", newsArray);
		
		String jsonInfo = jsonObject.toJSONString();
//		System.out.println(jsonInfo);
		
		System.out.println("☆★☆★☆★☆★☆★ << 내보내기 작업 완료 >> ☆★☆★☆★☆★☆★");
		
		// JSON 생성
		FileWriter file = new FileWriter("C:/Users/xmflr/Desktop/test/news.json");
		file.write(jsonInfo);
		file.flush();
		file.close();
	}
	
//	public void readJson() throws Exception {
//		JSONParser parser = new JSONParser();
////		keyword.json 경로
//		Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/news.json"));
//		
//		if (obj == null) {
//			System.out.println("news.json 파일이 존재하지 않습니다.");
//		} else {
//			JSONObject jsonObject = (JSONObject) obj;
//			
//			JSONArray keywordArray = (JSONArray) jsonObject.get("news");
//			
//			int size = keywordArray.size();
//			
//			System.out.println(jsonObject);	
//		}
//		
//	}
	
}
