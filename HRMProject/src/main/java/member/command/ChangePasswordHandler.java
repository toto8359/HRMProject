package member.command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordConfirmException;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {


  private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
  private ChangePasswordService changePwdSvc = new ChangePasswordService();

  @Override
  public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    // TODO Auto-generated method stub
    if (req.getMethod().equalsIgnoreCase("GET")) {
      return processForm(req, res);
    } else if (req.getMethod().equalsIgnoreCase("POST")) {
      return processSubmit(req, res);
    } else {
      res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      return null;
    }
  }

  private String processForm(HttpServletRequest req, HttpServletResponse res) {
    return FORM_VIEW;
  }

  private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

    // 현재 로그인 되어있는 유저 정보 불러오기
    // 現在ログインしているユーザー情報を取得する
    User user = (User) req.getSession().getAttribute("authUser");

    // 입력창 빈칸일 경우 에러확인을 위한 Map
    // 入力欄が空欄の場合のエラーチェックのためのマップ
    Map<String, Boolean> errors = new HashMap<>();
    req.setAttribute("errors", errors);

    // 입력받은 현재비번, 새로운비번 불러오기
    // 入力された現在のパスワードと新しいパスワードを取得する
    String curPwd = req.getParameter("curPwd");
    String newPwd = req.getParameter("newPwd");
    String newPwdConfirm = req.getParameter("newPwdConfirm");

    // 빈칸이 있을 경우, 돌아가기
    // 空欄がある場合、戻る
    if (curPwd == null || curPwd.isEmpty()) {
      errors.put("curPwd", Boolean.TRUE);
    }
    if (newPwd == null || newPwd.isEmpty()) {
      errors.put("newPwd", Boolean.TRUE);
    }
    if (newPwdConfirm == null || newPwdConfirm.isEmpty()) {
      errors.put("newPwdConfirm", Boolean.TRUE);
    }
    if (!errors.isEmpty()) {
      return FORM_VIEW;
    }

    try {// 비번 변경 실행 및 성공페이지 반환
         // パスワード変更実行および成功ページを返す
      changePwdSvc.changePassword(user.getMember_id(), curPwd, newPwd, newPwdConfirm);
      return "/WEB-INF/view/changePwdSuccess.jsp";
    } catch (InvalidPasswordException e) {// 현재비번이 틀릴 경우, 예외처리
      // 現在のパスワードが間違っている場合、例外 処理
      errors.put("badCurPwd", Boolean.TRUE);
      return FORM_VIEW;
    } catch (InvalidPasswordConfirmException e) {// 새비번과 비번확인이 다를 경우, 예외처리
      // 新しいパスワードとパスワードの確認が異なる場合、例外 処理
      errors.put("passNotMatch", Boolean.TRUE);
      return FORM_VIEW;
    } catch (MemberNotFoundException e) {// 비번 바꾸려 하는 아이디가 없다면, 예외처리(service에서 넘어옴)
      // パスワードを変更しようとするIDが存在しない場合、例外 処理(serviceからアクセス)
      res.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }

  }

}
