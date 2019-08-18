package test_project.bll;

import java.util.ArrayList;
import java.util.List;

import test_project.dal.DAO;
import test_project.dto.InvalidListing;
import test_project.dto.Listing;
import test_project.dto.Marketplace;
import test_project.dto.list.ListingList;

public class Validator {
		
	public static List<InvalidListing> validateListing(ListingList listingList) {
		
		String invalidField="";
		
		List<InvalidListing> invalidList = new ArrayList<>();
		
		List<String> locationIdList = DAO.getIdListUUIDAsString("location");
		List<String> idList = DAO.getIdListUUIDAsString("listing");
		List<Integer> listingStatusIdList = DAO.getIdListInteger("listing_status");
		List<Integer> marketplaceIdList = DAO.getIdListInteger("marketplace");
		@SuppressWarnings("unchecked")
		List<Marketplace> marketplaceList = (List<Marketplace>)(Object)DAO.getTableAsList("marketplace", Marketplace.class);
		
		
		for(int i=0; i<listingList.size(); i++) {
			Listing listing = listingList.get(i);
			String marketplaceName = null;
			
			if(!listing.validateId(idList))
				invalidField+=" ID needs to be unique UUID.";
			if(listing.getTitle()==null) 
				invalidField+=" Title cannot be null";
			if(listing.getDescription()==null) 
				invalidField+=" Description cannot be null";
			if(!listing.validateLocationId(locationIdList)) 
				invalidField+=" Violates location_id foreign key.";
			if(listing.getListingPrice()<=0 || (listing.getListingPrice()*100)%1>0) 
				invalidField+=" Listing_price needs to be greater than 0 and can only have 2 decimals.";
			if(listing.getCurrency().length()!=3) 
				invalidField+=" Currency lenght needs to be 3.";
			if(listing.getQuantity()<=0) 
				invalidField+=" Quantity needs to be greater than 0.";
			if(!listing.validatelistingStatus(listingStatusIdList)) 
				invalidField+=" Violates listing_status_id foreign key.";
			if(!listing.validateMarketplace(marketplaceIdList))
				invalidField+=" Violates marketplace_id foreign key.";
			else marketplaceName=marketplaceList.get(listing.getMarketplace()-1).getMarketplaceName();
			if(listing.getUploadTime()==null) 
				invalidField+=" Upload_time cannot be null.";
			if(!listing.getOwnerEmailAddress().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) 
				invalidField+=" Email address needs to be valid.";
			
			
			if(invalidField.length()>0) {
				invalidList.add(new InvalidListing(listing.getId(),marketplaceName,invalidField));
				listingList.remove(i--);
			}
			invalidField="";
			
		}
		
		return invalidList;
	}
}
