package com.climate.decode.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(name = "id", updatable = false, nullable = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "role_type")
	private String roleType;

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

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleType=" + roleType + "]";
	}
    
    
}
