package com.pojo;

public class materialOutPojo {

	private String ropId;	
	
	private Integer ropUnit;
	
    private String materialId;

    private String materialName;
    
    private Integer formulaCount;

    private double price;
    
	public String getRopId() {
		return ropId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRopId(String ropId) {
		this.ropId = ropId;
	}


	public Integer getRopUnit() {
		return ropUnit;
	}

	public void setRopUnit(Integer ropUnit) {
		this.ropUnit = ropUnit;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getFormulaCount() {
		return formulaCount;
	}

	public void setFormulaCount(Integer formulaCount) {
		this.formulaCount = formulaCount;
	}

}
