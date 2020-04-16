package sc2db;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawl {
	private String keyword;
	private int START;
	private int LIMIT;
	private String url;
	
	public Crawl() {
		this("");
	}
	
	public Crawl(String keyword) {
		this.keyword = keyword;
		this.START = 1;
		this.LIMIT = 10;
		this.url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=" + keyword;
	}
	
	public int getLastPage() throws Exception {
//		Document doc = Jsoup.connect(url).get();
//		
//		Elements pages = doc.select(".paging a");
		
//		int lastPage = Integer.parseInt(pages.get(pages.size() - 2).text());
		
		int lastPage = 10;
		
//		System.out.println(lastPage);
		
		return lastPage;
	}
	
	public List<String> getUrlList(int lastPage) throws Exception {
		Document doc = Jsoup.connect(url).get();
		
		List<String> reqList = new ArrayList<String>();
		
		for (int i = 0; i < lastPage; i++) {
			// HTML 문서
			doc = Jsoup.connect(url + "&start=" + START).get();
			
			// 기사 한칸 li 가져오기
			Elements type01 = doc.select("ul.type01 > li");
			// title까지 경로 dl
			Elements dl = type01.select("dl");
			Elements title = dl.select("dt a._sp_each_title");
			// reqURL 속성 -> dd.get(j).select("a").attr("href")
			Elements dd = dl.select("dd.txt_inline");
			
//			System.out.println("☆★☆★☆★☆★ 타이틀 크기 title.size = " + title.size() + " ☆★☆★☆★☆★☆★");
			
			for (int j = 0; j < title.size(); j++) {
				reqList.add(dd.get(j).select("a").attr("href"));
			}
			
//			System.out.println("타입이름" + dd.select("a").attr("href").getClass().getName());
			
			// 다음 페이지로
			START += LIMIT;
		}
		
		return reqList;
	}
	
	public List<News> getNewsList(List<String> reqList) throws Exception {
		List<News> newsList = new ArrayList<News>();
		
		// 각 페이지당 기사 컨텐츠 담기
		for (int i = 0; i < reqList.size(); i++) {
			String url = reqList.get(i);
			
			if (!reqList.get(i).equals("#") && !reqList.get(i).contains("sports")) {
				Document doc = Jsoup.connect(url).get();
				
				String title = doc.select("#articleTitle").text();
				String date = doc.select("div.sponsor span.t11").first().text().split(". ")[0].replace(".", "-");
				String contentBody = doc.select("#articleBodyContents").text();
				String media = doc.select("div.article_header div.press_logo a img").attr("title");
				
				// News 객체 생성
				News news = new News();
				
				news.setTitle(title);
				news.setRequestURL(reqList.get(i));
				news.setDate(date);
				news.setContent(contentBody);
				news.setMedia(media);
				
				// 리스트에 넣기
				newsList.add(news);
			} else {
				// reqList URL이 "#"이거나 'sports'가 들어가 있으면 가져오기 않는다.
			}
		}
		
		return newsList;
	}
}

