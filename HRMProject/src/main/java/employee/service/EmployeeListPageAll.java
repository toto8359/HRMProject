package employee.service;

import java.util.List;

//사원정보 List 페이징을 위한 VO
public class EmployeeListPageAll {

	// 속성
	private int total;
	private int currentPage;
	private List<InfoRequestAll> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	// 생성자, 저장된 데이터 갯수에 따라 나머지 속성 결정
	public EmployeeListPageAll(int total, int currentPage, int size, List<InfoRequestAll> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;

		// 저장된 사원정보가 없으면
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			// 저장된 사원정보가 있으면
			totalPages = total / size;

			// 총 사원정보 수를, 한 페이지당 표시 수로 나눠서 나머지가 있으면,
			// 나머지 표시하기 위해 페이지수 +1
			if (total % size > 0) {
				totalPages++;
			}

			// 페이지 목록 표시를 위함
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;

			endPage = startPage + 4;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}

	//view에서 사원정보 존재 확인
	public boolean hasNoEmployeeList() {
		return total == 0;
	}

	public boolean hasEmployeeList() {
		return total > 0;
	}

	// 게터 세터
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

	public List<InfoRequestAll> getContent() {
		return content;
	}

	public void setContent(List<InfoRequestAll> content) {
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
