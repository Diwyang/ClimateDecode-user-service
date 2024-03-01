package com.climate.decode.userservice.entity;

import com.climate.decode.userservice.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_role")
public class UserRole {

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "role_id")
	private RoleType roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        return user.getUserId() != null && user.getUserId().equals(((UserRole) o).getUser().getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
