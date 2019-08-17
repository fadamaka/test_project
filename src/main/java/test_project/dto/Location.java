package test_project.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Location {
	

	@Id
	private UUID id;
	
	@Column(name = "manager_name")
	@JsonProperty("manager_name")
	private String managerName;
	
	private String phone;
	
	@Column(name = "address_primary")
	@JsonProperty("address_primary")
	private String addressPrimary;
	
	@Column(name = "address_secondary")
	@JsonProperty("address_secondary")
	private String addressSecondary;
	
	private String country;
	
	private String town;
	
	@Column(name = "postal_code")
	@JsonProperty("postal_code")
	private String postalCode;
	
	public Location() {
		
	}
	
	public Location(UUID id, String managerName, String phone, String addressPrimary, String addressSecondary,
			String country, String town, String postalCode) {
		this.id = id;
		this.managerName = managerName;
		this.phone = phone;
		this.addressPrimary = addressPrimary;
		this.addressSecondary = addressSecondary;
		this.town=town;
		this.country = country;
		this.postalCode = postalCode;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressPrimary() {
		return addressPrimary;
	}

	public void setAddressPrimary(String addressPrimary) {
		this.addressPrimary = addressPrimary;
	}

	public String getAddressSecondary() {
		return addressSecondary;
	}

	public void setAddressSecondary(String addressSecondary) {
		this.addressSecondary = addressSecondary;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
