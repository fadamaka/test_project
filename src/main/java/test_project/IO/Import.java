package test_project.IO;

import test_project.dto.list.ListingList;
import test_project.dto.list.ListingStatusList;
import test_project.dto.list.LocationList;
import test_project.dto.list.MarketplaceList;
import test_project.web.Net;

public class Import {
	
	/*public static ResponseEntity<List<Marketplace>> getMarketplaceData(RestTemplate restTemplate, String url, ParameterizedTypeReference<List<Marketplace>> parameterizedTypeReference){
		return restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				parameterizedTypeReference);
	}*/
	

	
	public static MarketplaceList getMarketplaceList(){
		return (MarketplaceList)Net.getData("https://my.api.mockaroo.com/marketplace?key=63304c70",MarketplaceList.class);
	}
	
	public static ListingStatusList getListingStatusList(){
		return (ListingStatusList)Net.getData("https://my.api.mockaroo.com/listingStatus?key=63304c70",ListingStatusList.class);
	}
	
	public static ListingList getListingList(){
		return (ListingList)Net.getData("https://my.api.mockaroo.com/listing?key=63304c70",ListingList.class);
	}
	
	public static LocationList getLocationList(){
		return (LocationList)Net.getData("https://my.api.mockaroo.com/location?key=63304c70",LocationList.class);
	}
	

}
