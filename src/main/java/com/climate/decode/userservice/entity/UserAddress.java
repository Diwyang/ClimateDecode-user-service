package com.climate.decode.userservice.entity;

import com.climate.decode.userservice.enums.AddressType;
import com.climate.decode.userservice.enums.MobileType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	

	@Column(name = "address_type")
	private AddressType addressType;
	
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
}
