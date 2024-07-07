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
@Table(name = "user_mobile")
public class UserMobile {

	@Column(name = "id", updatable = false, nullable = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "mobile_type")
	private String mobileType;
	
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

	@Override
	public String toString() {
		return "UserMobile [id=" + id + ", mobileType=" + mobileType + ", mobileNo=" + mobileNo + "]";
	}
    
    
}
