package sc1;

public class News {
	
	private String title;
	private String requestURL;
	private String date;
	private String content;
	private String media;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	
	@Override
	public String toString() {
		return "News [title=" + title + ", requestURL=" + requestURL + ", date=" + date + ", content= , media=" + media + "]";
	}
	
}
