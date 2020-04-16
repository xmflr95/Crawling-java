package json1.test3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Program {

	public static void main(String[] args) {
//		JSON 파싱 - json-simple 이용
		JSONParser parser = new JSONParser();
		
		List<String> keyList = new ArrayList<String>();
		
		try {
			
			Object obj = parser.parse(new FileReader("C:/Users/xmflr/Desktop/test/company_keyword.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray keywordArray = (JSONArray) jsonObject.get("keyword");
			
			for (int i = 0; i < keywordArray.size(); i++) {
//				System.out.println("======== keyword : " + i + "========");
				JSONObject nameObject = (JSONObject) keywordArray.get(i);
				
//				System.out.println(nameObject.get("name"));
				
//				String companyName = nameObject.get("name").toString();
				
//				keyList.add(companyName);
			}
			
			System.out.println(keywordArray);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		for (String item : keyList) {
//			System.out.println(item);
//		}
		
	}

}
