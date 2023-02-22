package com.example.akkar2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
public abstract class User implements Serializable{

	
	// Basic For All users 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private int id;
	
	@Column(nullable = false)
	private String firstname ;
	
	@Column(nullable = false)
	private String lastname ; 
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthday ;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	
	@Column(nullable = false)
	private String email ; 

	
	@Column(nullable = false)
	private String password ; 



    private Boolean banned;

	// For Admin Only


	// For Expert Only


	// For Driver


	// Relations with Other Entities 






	//command
    @OneToMany(mappedBy ="user")
	@JsonIgnore
	private List<Command> command;

	//post
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private List<Command> Commands;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	private List<Furniture> furniture;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	private List<Comment> comment;
	

}