package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    // req.getSession(false)로, 세션에 있는 정보가 있으면 가져오고, 정보가 없으면 null을 반환
    // req.getSession(false)を使用して、セッションに情報があれば取得し、情報がない場合はnullを返す
    HttpServletRequest request = (HttpServletRequest) req;
    HttpSession session = request.getSession(false);

    // 만약 session에 authUser정보가 없으면, login.do로 돌아가기
    // もしセッションにauthUser情報がなければ、login.doに戻る
    if (session == null || session.getAttribute("authUser") == null) {
      HttpServletResponse response = (HttpServletResponse) res;
      response.sendRedirect(request.getContextPath() + "/login.do");
    } else {
      // 이것 외에, 필터가 더 있다면, 다음 필터에 req, res 전달
      // これ以外にも、フィルターがある場合は、次のフィルターに req と res を渡します。
      chain.doFilter(req, res);
    }

  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    // TODO Auto-generated method stub
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
  }

}
