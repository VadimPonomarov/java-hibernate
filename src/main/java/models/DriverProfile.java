package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DriverProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "driverProfile")
    private Driver driver;
    private String innNumber;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    public DriverProfile(Driver driver, String innNumber, LocalDate dateOfBirth, Passport passport) {
        this.driver = driver;
        this.innNumber = innNumber;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
    }
}
