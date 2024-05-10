package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {
	// 각 요청 URI에 따른 CommandHandler를 매핑하기 위한 맵
	// 各要請URIに応じたコマンドハンドラをマッピングするためのマップ
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	// 서블릿 초기화 메서드
	// サーブレットの初期化メソッド
	@Override
	public void init() throws ServletException {
		// web.xml에서 설정한 configFile 초기화 파라미터 값 획득
		// web.xmlで設定したconfigFileの初期化パラメータ値を取得
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		// configFile에 해당하는 설정 파일 경로 획득
		// configFileに対応する設定ファイルのパスを取得
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			// 설정 파일 로드
			// 設定ファイルをロード
			prop.load(fis);
		} catch (IOException e) {
			// 설정 파일 로드 중 예외 발생 시 ServletException 발생
			// 設定ファイルのロード中に例外が発生した場合、ServletExceptionをスロー
			throw new ServletException(e);
		}
		// 설정 파일의 각 항목에 대해 CommandHandler 맵에 추가
		// 設定ファイルの各項目について、CommandHandlerマップに追加
		Iterator keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				// 클래스 로드
				// クラスのロード
				Class<?> handlerClass = Class.forName(handlerClassName);
				// 인스턴스 생성
				// インスタンス生成
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				// 맵에 추가
				// マップに追加
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// 클래스 로드 또는 인스턴스 생성 중 예외 발생 시 ServletException 발생
				// クラスのロードまたはインスタンスの生成中に例外が発生した場合、ServletExceptionを発生
				throw new ServletException(e);
			}
		}
	}

	// GET 요청 처리 메서드
	// GETリクエスト処理メソッド
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	// POST 요청 처리 메서드
	// POSTリクエスト処理メソッド
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	// 요청 처리 메서드
	// リクエスト処理メソッド
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 요청 URI 획득
		// リクエストURIの取得
		String command = req.getRequestURI();
		if (command.indexOf(req.getContextPath()) == 0) {
			// 컨텍스트 패스 제거
			// コンテキストパスの削除
			command = command.substring(req.getContextPath().length());
		}
		// 해당 URI에 매핑된 CommandHandler 획득
		// 対応するURIにマッピングされたCommandHandlerの取得
		CommandHandler handler = commandHandlerMap.get(command);
		if (handler == null) {
			// 매핑된 핸들러가 없을 경우 기본 NullHandler로 설정
			// マッピングされたハンドラがない場合、デフォルトのNullHandlerに設定
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			// 요청 처리 및 뷰 페이지 획득
			// リクエストの処理とビューページの取得
			viewPage = handler.process(req, resp);
		} catch (Exception e) {
			// 요청 처리 중 예외 발생 시 ServletException 발생
			// リクエストの処理中に例外が発生した場合、ServletExceptionを発生
			throw new ServletException(e);
		}
		if (viewPage != null) {
			// 뷰 페이지가 존재할 경우 해당 페이지로 포워딩
			// ビューページが存在する場合、そのページにフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}

}
