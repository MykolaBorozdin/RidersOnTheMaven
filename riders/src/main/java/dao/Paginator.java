package dao;

import java.io.Serializable;

public class Paginator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4616558667873286670L;
	private long currentPage;
	private long pageSize;
	private long maxRow;
	
	public Paginator(int pageSize, long maxRow) {
		this.setCurrentPage(0);
		this.pageSize = pageSize;
		this.setMaxRow(maxRow);
	}
	
	public boolean isFirstPage() {
		if (getCurrentPage() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void turnToNextPage() {
		if (!isLastPage()) {
			setCurrentPage(getCurrentPage() + 1);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void turnPageBack() {
		if (getCurrentPage() > 0) {
			setCurrentPage(getCurrentPage() - 1);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public long getStart() {
		if (getCurrentPage() * pageSize < getMaxRow()) {
			return getCurrentPage() * pageSize;
		} else {
			return getMaxRow();
		}
	}
	
	public long getEnd() {
		if (((getCurrentPage() + 1) * pageSize) >= getMaxRow()) {
			return getMaxRow();
		} else {
			return ((getCurrentPage() + 1) * pageSize) - 1;
		}
	}
	
	public boolean isLastPage() {
		if (((getCurrentPage() + 1) * pageSize) >= getMaxRow()) {
			return true;
		} else {
			return false;
		}
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	
	public long getMaxPage() {
		if (getMaxRow() % pageSize == 0) {
			return getMaxRow()/pageSize - 1;
		} else {
			return getMaxRow()/pageSize;
		}
		
	}

	public long getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(long maxRow) {
		if (maxRow == currentPage*pageSize && currentPage != 0) {
			this.turnPageBack();
		}
		this.maxRow = maxRow;
	}
}
