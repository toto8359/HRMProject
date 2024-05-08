package article.model;

public class ArticleContent {

	private Integer number;
	private String content;

	public ArticleContent(Integer number, String content) {
		super();
		this.number = number;
		this.content = content;
	}

	public ArticleContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
