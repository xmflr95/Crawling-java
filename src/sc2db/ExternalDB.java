package sc2db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExternalDB {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void insertNewsData(List<News> newsList) throws Exception {
		
		int size = newsList.size();
		
		conn = DBCon.getConnection();
		
//		Query
		String insertSql = "INSERT INTO MA_NEWS("
				+ "NUMBER, TITLE, REQUESTURL, DATE, CONTENT, MEDIA) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
//		번호 등록용 카운트
		int count = 1;
		
		for (int i = 0; i < size; i++) {
			pstmt = conn.prepareStatement(insertSql);
			
			News news = newsList.get(i);
			
			String newsDate = news.getDate();
			java.sql.Date date = java.sql.Date.valueOf(newsDate);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, news.getTitle());
			pstmt.setString(3, news.getRequestURL());
			pstmt.setDate(4, date);
			pstmt.setString(5, news.getContent());
			pstmt.setString(6, news.getMedia());
			
			int result = pstmt.executeUpdate();
			
//			if (result == 1) {
//				System.out.println("레코드 추가 성공!");
//			}
			
			count++; // 다음 News는 추가된 번호를 부여 받게함
		}
		
//		닫기
//		rs.close();
//		pstmt.close();
//		conn.close();
	}
	
//	데이터 보기
	public void selectNewsData() throws Exception {
		List<News> jsonNewsList = new ArrayList<News>();
		
		conn = DBCon.getConnection();
		
		String selectSql = "SELECT NUMBER, TITLE FROM MA_NEWS";
		pstmt = conn.prepareStatement(selectSql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) { // 데이터 출력
			System.out.println("NUMBER : " + rs.getString("NUMBER") + ", TITLE : " + rs.getString("TITLE"));
		}
		
//		닫기
//		rs.close();
//		pstmt.close();
//		conn.close();
	}
	
	public List<News> getNewsDate() throws Exception {
		List<News> jsonNewsList = new ArrayList<News>();
		
		conn = DBCon.getConnection();
		
		String selectSql = "SELECT * FROM MA_NEWS";
		pstmt = conn.prepareStatement(selectSql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) { // 데이터 출력
			News news = new News();
			
			news.setTitle(rs.getString("TITLE"));
			news.setRequestURL(rs.getString("REQUESTURL"));
			news.setDate(rs.getDate("DATE").toString());
			news.setContent(rs.getString("CONTENT"));
			news.setMedia(rs.getString("MEDIA"));
			
			jsonNewsList.add(news);
		}
		
//		닫기
//		rs.close();
//		pstmt.close();
//		conn.close();
		
		return jsonNewsList;
	}
	
	public void closeDatabase() {
		try {
			if (rs != null) {
				// rs 닫기
				rs.close();
			}
			
			if (pstmt != null) {
				// pstmt close
				pstmt.close();
			}
			
			if (conn != null) {
				// conn close
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("[닫기 오류]\n");
			e.printStackTrace();
		}
	}
	
}

