package com.yxiao23.bean;

public class Products {
	private String productId;
	private String productInfo;
	private String productStatus;//default "1" : for sale; "0": sold out
	private String productName;
	private String productCategory;
	private String productPrice;
	private String productStock;
	private String imageUrl;
	private String additionalOption;
	
	
	public Products() {
		
	}
	
	public Products(String productId, String productInfo, String productStatus, String productName,
			String productCategory, String productPrice, String productStock, String imageUrl,
			String additionalOption) {
		super();
		this.productId = productId;
		this.productInfo = productInfo;
		this.productStatus = productStatus;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.imageUrl = imageUrl;
		this.additionalOption = additionalOption;
	}


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductStock() {
		return productStock;
	}
	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAdditionalOption() {
		return additionalOption;
	}

	public void setAdditionalOption(String additionalOption) {
		this.additionalOption = additionalOption;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productInfo=" + productInfo + ", productStatus=" + productStatus
				+ ", productName=" + productName + ", productCategory=" + productCategory + ", productPrice="
				+ productPrice + ", productStock=" + productStock + ", imageUrl=" + imageUrl + ", additionalOption="
				+ additionalOption + "]";
	}
	
	
}
