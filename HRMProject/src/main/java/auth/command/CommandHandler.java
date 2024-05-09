package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {

  // 1.get, post 요청에 따른 처리
  // GET、POST リクエストによる処理
  public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;

}
