package test_project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "listing_status")
public class ListingStatus {

	@Id
	private Integer id;
	
	@Column(name = "status_name")
	@JsonProperty("status_name")
	private String statusName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	

	public ListingStatus() {
		
	}

	public ListingStatus(Integer id, String statusName) {
		this.id = id;
		this.statusName = statusName;
	}
	
	@Override
    public String toString() {
        return "ls{" +
                "id=" + id +
                ", status_name=" + statusName +
                '}';
    }
}
