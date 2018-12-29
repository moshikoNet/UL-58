package com.moshiko.beans;

import com.moshiko.utils.BulkHeadFactory;
import com.moshiko.utils.DaoFactory;

public class Testing {

	
	BulkHeadFactory change;

	public Testing() {
	}
	
	public Testing(BulkHeadFactory change) {
		this.change = change;
	}

	public BulkHeadFactory getChange() {
		return change;
	}

	public void setChange(BulkHeadFactory change) {
		this.change = change;
	}

	@Override
	public String toString() {
		return "Testing [change=" + change + "]";
	}
	
	
}
