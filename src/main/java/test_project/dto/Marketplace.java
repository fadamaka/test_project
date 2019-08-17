package test_project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Marketplace {
	
	@Id
	private Integer id;
	
	@Column(name = "marketplace_name")
	@JsonProperty("marketplace_name")
	private String marketplaceName;
	
	public Marketplace() {
		
	}
	
	public Marketplace(Integer id, String marketplaceName) {
		this.id = id;
		this.marketplaceName = marketplaceName;
	}

	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMarketplaceName() {
		return marketplaceName;
	}
	
	public void setMarketplaceName(String marketplaceName) {
		this.marketplaceName = marketplaceName;
	}
	
	@Override
    public String toString() {
        return "mp{" +
                "id=" + id +
                ", marketplace_name=" + marketplaceName +
                '}';
    }
}
