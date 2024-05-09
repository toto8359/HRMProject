package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

  @Override
  public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    // 1.로그아웃 처리
    // 로그인을 했는지 확인하는 것은, authUser가 session에 저장되어있는지로 판단
    // 그래서 session에 아무것도 없도록 하여 다시 index.jsp로 이동하면,
    // 비로그인 상태가 됨
    // session 에 아무것도 없으면, null을 반환

    // 1.ログアウト処理
    // ログイン可否の確認は、authUserがsession(セッション)に保存されていることで確認されます。
    // したがって、session(セッション)に何もないようにしてindex.jspに再度移動すると、
    // 非ログイン状態になります。
    // session(セッション)に何もなければ、nullを返します。
    HttpSession session = req.getSession(false);

    // 만약 세션이 null이 아니면, 그것을 끝내버림
    // もしsession(セッション)がnullでなければ、それを終了します。
    if (session != null) {
      session.invalidate();
    }

    // 3.되돌아가기
    // 戻る
    res.sendRedirect(req.getContextPath() + "/index.jsp");
    return null;
  }

}
