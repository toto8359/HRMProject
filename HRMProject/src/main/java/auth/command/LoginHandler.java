package auth.command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginService;
import auth.service.User;
import exception.LoginFailException;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {

  // 실패시 다시 돌아갈 페이지
  // 失敗した場合に戻るページ
  private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
  private LoginService loginService = new LoginService();

  // 1.get, post 요청에 따른 처리
  // GET、POST リクエストによる処理

  @Override
  public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    if (req.getMethod().equalsIgnoreCase("get")) {
      return processForm(req, res);
    } else if (req.getMethod().equalsIgnoreCase("post")) {
      return processSubmit(req, res);
    } else {
      res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      return null;
    }
  }

  // get이면 돌아가기
  // GETなら戻る
  private String processForm(HttpServletRequest req, HttpServletResponse res) {
    return FORM_VIEW;
  }

  // post면 로그인 진행
  // POSTならログインを進める
  private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
    // view에서 입력받은 id, password 가져오기, trim()으로 공백제거
    // ビューから受け取ったID、パスワードを取得し、trim() で空白を除去する
    String id = trim(req.getParameter("id"));
    String password = trim(req.getParameter("password"));

    // Map형태 에러 선언 및 session에 저장
    // Map形式でエラーを宣言し、セッションに保存する
    Map<String, Boolean> errors = new HashMap<>();
    req.setAttribute("errors", errors);

    // 입력창 공란 확인 및 errors.value에 저장
    // 入力欄の空欄確認及び(errors.value)に保存
    if (id == null || id.isEmpty()) {
      errors.put("id", Boolean.TRUE);
    }
    if (password == null || password.isEmpty()) {
      errors.put("password", Boolean.TRUE);
    }
    if (!errors.isEmpty()) {
      return FORM_VIEW;
    }

    // 로그인 및 id와 이름을 User형으로 받아, session에 저장
    // 로그인에 성공하면 sendRedirect로 index.jsp페이지로 이동
    // ログインおよびidと名前をUser形式で受け取り、sessionに保存
    // ログインに成功するとsendRedirectでindex.jspページに移動

    try {
      User user = loginService.login(id, password);
      req.getSession().setAttribute("authUser", user);
      res.sendRedirect(req.getContextPath() + "/index.jsp");
      return null;
    } catch (LoginFailException e) {
      // 로그인 실패시 errors에 담기
      // ログイン失敗時にerrorsに入れる
      errors.put("idOrPwNotMatch", Boolean.TRUE);
      return FORM_VIEW;
    }
  }

  // 공백제거
  // ブランク除去
  private String trim(String str) {
    return str == null ? null : str.trim();
  }
}
