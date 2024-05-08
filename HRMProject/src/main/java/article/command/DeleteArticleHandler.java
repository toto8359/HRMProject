package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.DeleteArticleService;
import article.service.ListArticleService;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1.process
		// 핸들러에서, 기사를 지우기 위해 필요한 작업
		// >여기서는, 기사를 삭제하는 과정을 담고있다.
		// >기사번호를 받은 뒤, 이를 저장하여,
		// >deleteService에 있는 함수를 통해, 기사를 지운다.
		// (ctrl 누른 상태로, 궁금한 함수를 누르면,
		// 해당 함수가 어떻게 정의되어 있는지 볼 수 있다.)
		String articleNo = req.getParameter("no");
		int no = Integer.parseInt(articleNo);
		DeleteArticleService deleteArticleService = new DeleteArticleService();
		deleteArticleService.delete(no);

		// 2.정보저장
		// 작업이 끝나고, 페이지가 이동하여, 다음에 보여줄 페이지에서
		// 필요한 정보들을 여기서 저장하고, 다음 페이지로 넘겨주는 과정
		// >기사를 삭제하고 나서, 보여주고 싶은 페이지에 대한 정보
		// >여기서는, 해당 기사를 삭제하면, 다시 기사 목록을 보여주기 위해
		// >기사 목록을 보여주는, listService를 이용하고 있다.
		ListArticleService listService = new ListArticleService();
		ArticlePage articlePage = listService.getArticlePage(1);
		req.setAttribute("articlePage", articlePage);

		// 3.보여줄 페이지 반환
		// 기사목록을 보여줄 페이지를 반환(return)하여,
		// 기사를 삭제하고 나서, 어떤 페이지로 이동할 것인지를 결정한다.
		// >여기서는 기사목록을 다시 보려주려고 한다.
		return "/WEB-INF/view/listArticle.jsp";

	}

}