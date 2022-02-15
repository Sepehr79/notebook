package com.kucess.notebook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String lastName;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinTable(name = "person_authority")
	private List<Authority> authorities;
	
	public void addAuthority(Authority authority) {
		if (authority == null) {
			throw new IllegalArgumentException();
		}
		if (authorities == null) {
			authorities = new ArrayList<Authority>();
		}
		authorities.add(authority);
		
	}
}
