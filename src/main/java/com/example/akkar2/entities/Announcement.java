package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Announcement implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAnnouncemenet;
	@Column(nullable = false)
	private String title ;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AnnouncementType announcementType ;
	@Column(nullable = false)
	private double  price ;
	@Column(nullable = false)
	private String description ;
	@Column(nullable = false)
	private double rate ;
	@Column(nullable = false)
	private int numberofrates  ;

	@ManyToOne
	@JsonIgnore
	private Client client ;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private RealEstate realestate ;
	@OneToMany(mappedBy ="announcement")
	@JsonIgnore
	private List<Reclamation> reclamation;

}
