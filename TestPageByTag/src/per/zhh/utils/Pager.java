package per.zhh.utils;
//方式2
public class Pager {
	
	private int pageNow;//当前页
	private int rowCount;//总条数
	private int pageSize;//每页显示条数
	private int pageCount;//总页数
	private String url;

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	
	public Pager(int rowCount, int rowPerPage, int currentPage) {

		this.rowCount = rowCount;
		this.pageSize = rowPerPage;
		this.pageNow = currentPage;
		if(this.rowCount % this.pageSize == 0){
			this.pageCount = this.rowCount / this.pageSize;
		}else if(this.rowCount % this.pageSize > 0){
			this.pageCount = this.rowCount / this.pageSize + 1;
		}else{
			this.pageCount = 0;
		}
	}

	public int getRowCount() {
		return rowCount;
	}



	public int getPageCount() {
		return pageCount;
	}
}
