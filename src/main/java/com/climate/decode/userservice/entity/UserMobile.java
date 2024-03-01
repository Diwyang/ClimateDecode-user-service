package com.climate.decode.userservice.entity;

import com.climate.decode.userservice.enums.MobileType;
import com.climate.decode.userservice.enums.RoleType;

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
@Table(name = "user_mobile")
public class UserMobile {

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "mobile_type")
	private MobileType mobileType;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMobile)) return false;
        return user.getUserId() != null && user.getUserId().equals(((UserMobile) o).getUser().getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
