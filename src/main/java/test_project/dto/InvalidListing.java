package test_project.dto;

import java.util.UUID;

public class InvalidListing {
	
	private UUID listingId;
	private String MarketplaceName;
	private String InvalidField;
	
	public InvalidListing() {
		
	}
	
	public InvalidListing(UUID listingId, String MarketplaceName, String InvalidField) {
		this.listingId=listingId;
		this.MarketplaceName=MarketplaceName;
		this.InvalidField=InvalidField;
	}
	
	public UUID getListingId() {
		return listingId;
	}
	public void setListingId(UUID listingId) {
		this.listingId = listingId;
	}
	public String getMarketplaceName() {
		return MarketplaceName;
	}
	public void setMarketplaceName(String marketplaceName) {
		MarketplaceName = marketplaceName;
	}
	public String getInvalidField() {
		return InvalidField;
	}
	public void setInvalidField(String invalidField) {
		InvalidField = invalidField;
	}
	
	
}
