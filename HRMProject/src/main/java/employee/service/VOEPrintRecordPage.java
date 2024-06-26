package employee.service;

import java.util.List;

//재직증명서 출력 List 페이징을 위한 VO
// 在職証明書出力リストのページングのためのVO
public class VOEPrintRecordPage {
	// 속성
	// 属性
	private int total;
	private int currentPage;
	private List<InfoRequestVOERecord> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	// 생성자, 저장된 데이터 갯수에 따라 나머지 속성 결정
	// コンストラクタ、保存されたデータ数に応じて残りの属性を決定
	public VOEPrintRecordPage(int total, int currentPage, int size, List<InfoRequestVOERecord> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;

		// 저장된 재직증명서 출력기록이 없으면
		// 保存された在職証明書出力履歴がない場合
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			// 저장된 재직증명서 출력기록이 있으면
			// 保存された在職証明書出力履歴がある場合
			totalPages = total / size;

			// 총 재직증명서 출력기록 수를, 한 페이지당 표시 수로 나눠서 나머지가 있으면,
			// 나머지 표시하기 위해 페이지수 +1
			// 全在職証明書出力履歴数を、1ページ当たりの表示数で割り、余りがある場合、
			// 余りを表示するためにページ数に+1
			if (total % size > 0) {
				totalPages++;
			}
 
			// 페이지 목록 표시를 위함
			// ページリスト表示のため
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;

			endPage = startPage + 4;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}

	// view에서 재직증명서 출력기록 존재 확인
	// viewで在職証明書出力履歴が存在するか確認する
	public boolean hasNoVOERecordList() {
		return total == 0;
	}

	public boolean hasVOERecordList() {
		return total > 0;
	}

	// 게터 세터
	// ゲッター・セッター
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<InfoRequestVOERecord> getContent() {
		return content;
	}

	public void setContent(List<InfoRequestVOERecord> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
