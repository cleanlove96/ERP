package com.pojo;

import java.util.List;

import com.model.WareHouse;

public class ListStoragePojo {

	private List<GoodsStoragePojo> gp;
	
	private List<WareHouse> warehouse;

	public List<GoodsStoragePojo> getGp() {
		return gp;
	}

	public void setGp(List<GoodsStoragePojo> gp) {
		this.gp = gp;
	}

	public List<WareHouse> getWarehouse() {
		return warehouse;
	}

	public void setWarehouseName(List<WareHouse> warehouse) {
		this.warehouse = warehouse;
	}
}
