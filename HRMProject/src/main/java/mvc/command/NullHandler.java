package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 클라이언트에게 "페이지를 찾을 수 없음" 오류 코드 전송
		// クライアントに "ページが見つかりません" エラーコードを送信
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
