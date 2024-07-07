package com.climate.decode.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_address")
public class UserAddress {
	
	@Column(name = "id", updatable = false, nullable = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	

	@Column(name = "address_type")
	private String addressType;
	
	@Column(name = "address_1")
	private String address1;
	
	@Column(name = "address_2")
	private String address2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAddress)) return false;
        return user.getUserId() != null && user.getUserId().equals(((UserAddress) o).getUser().getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", addressType=" + addressType + ", address1=" + address1 + ", address2="
				+ address2 + ", city=" + city + ", state=" + state + ", country=" + country + ", pincode=" + pincode
				+ "]";
	}
    
    
}
