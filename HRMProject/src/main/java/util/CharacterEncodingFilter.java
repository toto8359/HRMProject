package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String encoding;

	// 필터가 요청에 대해 작업을 수행하는 메서드
	// フィルターがリクエストに対して処理を行うメソッド
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// 요청의 문자 인코딩 설정
		// リクエストの文字エンコーディングを設定
		arg0.setCharacterEncoding(encoding);
		// 다음 필터로 요청 전달
		// 次のフィルターにリクエストを渡す
		arg2.doFilter(arg0, arg1);
	}

	// 필터 초기화 시 호출되는 메서드
	// フィルターの初期化時に呼び出されるメソッド
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 초기화 파라미터로부터 인코딩 설정 값 획득
		// フィルターの初期化パラメーターからエンコーディング設定値を取得
		encoding = filterConfig.getInitParameter("encoding");
		// 인코딩 설정 값이 없으면 기본값으로 UTF-8 설정
		// エンコーディング設定値がない場合、デフォルト値としてUTF-8を設定
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

	// 필터 종료 시 호출되는 메서드
	// フィルターの破棄時に呼び出されるメソッド
	@Override
	public void destroy() {
	}

}