package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
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
public class RealEstate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRealEstate;

    @Column(nullable = false)
    private String location;
    @Lob
    private byte[] pictures;
<<<<<<< Updated upstream
   private double pricePerNight;
// A verifier le type des images
=======
    private double pricePerNight;
    // A verifier le type des images
>>>>>>> Stashed changes
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RealEstateType type;

    @Column(nullable = false)
<<<<<<< Updated upstream
    private  String services;
=======
    private String services;
>>>>>>> Stashed changes
    @Column(nullable = false)
    private int surface;
    @Column(nullable = false)
    private int rooms;
    @Column(nullable = false)
    private int floors;
    @Column(nullable = false)
<<<<<<< Updated upstream
    private  String description;
=======
    private String description;
  //  @Column(nullable = false)
    //private double longitude;
    //@Column(nullable = false)
    //private double latitude;

>>>>>>> Stashed changes

    @OneToOne
    @JsonIgnore
    private Announcement announcement;
    @OneToOne
    @JsonIgnore
    private Papers paper;
    @OneToMany(mappedBy = "realEstate")
    @JsonIgnore
    private List<Contract> contracts;
    @OneToMany(mappedBy = "realEstate")
    @JsonIgnore
<<<<<<< Updated upstream
    private  List<Reservation> reservations;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Rating> ratings;
    

}
=======
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
    private List<Rating> ratings;


  /*  public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public int getdistance(double longitude, double latitude) {
        System.out.println(latitude);
        System.out.println(longitude);
        double latDistance = Math.toRadians(latitude - 36.24255502127699);
        double lngDistance = Math.toRadians(longitude - 9.476723745103257);

        double a = Math.sin(latDistance/2) * Math.sin(latDistance/2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(36.24255502127699))
                + Math.sin((lngDistance / 2 )) * Math.sin(lngDistance/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c ));
    }
      private int distance = getdistance(longitude,latitude);
}
*/
}
>>>>>>> Stashed changes
