package com.productservice.microservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    private String name;
	    private String email;
	    private String password;
	    private String roles;
}
