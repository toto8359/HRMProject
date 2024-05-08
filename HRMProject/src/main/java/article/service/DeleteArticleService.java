package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {

	// 1.사용할 클래스 인스턴스화
	// service에서 진행할 과정에 필요한 class들을 인스턴스화 함.
	// 여기서는,
	// 기사의 정보를 담고있는 Article과,
	// 기사의 내용을 담고있는 ArticleContent의
	// 정보를 db로부터 주고받는 dao클래스를 인스턴스화 한다.
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();

	// 2.기사를 삭제할 함수 delete()를 선언한다.
	public void delete(int no) {

		Connection conn = null;

		// sql문을 사용할 때, 오류가 날 수 있기에
		// try-catch문을 통한, 예외처리를 함으로써,
		// 해당 에러가 발생해도, 에러 페이지를 띄우지 않도록 한다.
		// (try-catch문을 사용한 예외처리는, 에러가 발생해도, 이를 페이지에 띄우지 않도록 할 수 있다.)
		try {// 우선 해당 내용을 실행한다.

			// db와의 connection을 만든다.
			conn = ConnectionProvider.getConnection();

			// 1번에서 인스턴스화한 dto클래스를 사용하여,
			// 해당 함수의 입력값인, no(기사번호)에 해당하는 기사를 찾아
			// 해당 기사를 db에서 삭제한다.
			// (ctrl 누른 상태로, 궁금한 함수를 누르면,
			// 해당 함수가 어떻게 정의되어 있는지 볼 수 있다.)
			articleDao.delete(conn, no);
			articleContentDao.delete(conn, no);

		} catch (SQLException e) {// sql 예외가 발생하면, 이를 찾아낸다.
			throw new RuntimeException(e);
		} finally {// try문을 실행하고, sql예외가 발생하지 않으면, 다음을 실행한다.
			JdbcUtil.close(conn);// connection을 닫는다.
		}
	}

}
