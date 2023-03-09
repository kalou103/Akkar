package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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
	@Column(name = "id")
	private Long id;
	
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
	
	@Column(nullable = false,unique = true)
	private String email ; 

	
	@Column(nullable = false)
	private String password ; 

	@JsonIgnore
	private LocalDate CreateAt= LocalDate.now();
	@JsonIgnore
	private Boolean banned=Boolean.FALSE;
	@Transient
	@JsonIgnore
	public String getDecriminatorValue() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}


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
	@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "postId", referencedColumnName = "postId")
	@JsonIgnore
	private Post post;


	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	private List<Comment> comment;




}