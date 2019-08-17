package test_project.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@Table(name = "listing_test")
public class Listing {

	@Id
	private UUID id;
	
	private String title;
	
	private String description;
	
	@Column(name = "location_id")
	@JsonProperty("location_id")
	private UUID locationId;
	
	@Column(name = "listing_price")
	@JsonProperty("listing_price")
	private Float listingPrice;
	
	private String currency;
	
	private Integer quantity;
	
	@Column(name = "listing_status")
	@JsonProperty("listing_status")
	private Integer listingStatus;
	
	private Integer marketplace;
	
	@Column(name = "upload_time")
	@JsonProperty("upload_time")
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date uploadTime;
	
	@Column(name = "owner_email_address")
	@JsonProperty("owner_email_address")
	private String ownerEmailAddress;
	
	public Listing() {
		
	}
	
	public Listing(UUID id, String title, String description, UUID locationId, Float listingPrice, String currency,
			Integer quantity, Integer listingStatus, Integer marketplace, Date uploadTime, String ownerEmailAddress) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.locationId = locationId;
		this.listingPrice = listingPrice;
		this.currency = currency;
		this.quantity = quantity;
		this.listingStatus = listingStatus;
		this.marketplace = marketplace;
		this.uploadTime = uploadTime;
		this.ownerEmailAddress = ownerEmailAddress;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public Boolean validateId(List<String> idList) {
		if(idList.contains(id.toString()))
			return false;
		else
			return true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public Boolean validateLocationId(List<String> locationIdList) {
		if(locationIdList.contains(locationId.toString()))
			return true;
		else
			return false;
	}

	public Float getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(Float listingPrice) {
		this.listingPrice = listingPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getListingStatus() {
		return listingStatus;
	}

	public void setListingStatus(Integer listingStatus) {
		this.listingStatus = listingStatus;
	}
	
	public Boolean validatelistingStatus(List<Integer> listingStatusIdList) {
		if(listingStatusIdList.contains(listingStatus))
			return true;
		else
			return false;
	}

	public Integer getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Integer marketplace) {
		this.marketplace = marketplace;
	}
	
	public Boolean validateMarketplace(List<Integer> marketplaceIdList) {
		if(marketplaceIdList.contains(marketplace))
			return true;
		else
			return false;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getOwnerEmailAddress() {
		return ownerEmailAddress;
	}

	public void setOwnerEmailAddress(String ownerEmailAddress) {
		this.ownerEmailAddress = ownerEmailAddress;
	}
	
	
	
}
