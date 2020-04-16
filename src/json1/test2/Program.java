package json1.test2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

public class Program {

	public static void main(String[] args) {
		
		try {			
//			바이트 단위로 파일 읽기
			String filePath = "C:/Users/xmflr/Desktop/test/company_keyword.json";
			FileInputStream fileStream = null; // fileStream
			
			fileStream = new FileInputStream(filePath); // 파일 스트림 생성
			
			// 버퍼 선언
			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
				System.out.println(new String(readBuffer)); // 출력
			}
			
			fileStream.close(); // 스트림 닫기
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		
	}

}
