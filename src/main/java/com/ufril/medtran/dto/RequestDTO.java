package com.ufril.medtran.dto;

import com.ufril.medtran.dto.account.GetProfileDTO;
import com.ufril.medtran.enumeration.RequestType;

public class RequestDTO {

	private Integer id;
	private GetProfileDTO sender;
	private GetProfileDTO receiver;
	private RequestType status;
	private String productID;
	private String productTitle;
	private String productDetails;
	private String productURL;
	private String imageURL;
	private String marketPlace;
	private String marketPlaceCountry;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetProfileDTO getSender() {
		return sender;
	}

	public void setSender(GetProfileDTO sender) {
		this.sender = sender;
	}

	public GetProfileDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(GetProfileDTO receiver) {
		this.receiver = receiver;
	}

	public RequestType getStatus() {
		return status;
	}

	public void setStatus(RequestType status) {
		this.status = status;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

	public String getMarketPlaceCountry() {
		return marketPlaceCountry;
	}

	public void setMarketPlaceCountry(String marketPlaceCountry) {
		this.marketPlaceCountry = marketPlaceCountry;
	}
}

