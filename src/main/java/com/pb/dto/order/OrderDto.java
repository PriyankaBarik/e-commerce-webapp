package com.pb.dto.order;

public class OrderDto {
 private String productName;
 private Integer quentity;
 private double price;
 private long productId;
 private int userId;
public OrderDto() {
	super();
	// TODO Auto-generated constructor stub
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public Integer getQuentity() {
	return quentity;
}
public void setQuentity(Integer quentity) {
	this.quentity = quentity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public long getProductId() {
	return productId;
}
public void setProductId(long productId) {
	this.productId = productId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
 
}
