package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {

	// 1.명령어와 관련된 비즈니스 로직 처리
	//// コマンドに関連するビジネスロジックを処理し、結果を返します。
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;

	// 2.뷰 페이지에서 사용할 정보 저장
	//// ビューページで使用する情報を保存します。
	// req.setAttribute("value", value);

	// 3.뷰 페이지의 URI 리턴
	// ビューページのURIを返します。
	// return "/WEB-INF/view/example.jsp
}
