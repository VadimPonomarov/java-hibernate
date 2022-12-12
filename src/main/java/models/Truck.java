package models;

import enums.Mark;
import enums.TruckType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Mark truckMark;
    private TruckType truckType;
    private int length;
    private int width;
    private float weight;
    private float weightFull;
    private String numberReg;
    private String vinCode;
    private String techPassport;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "truck_driver",
            joinColumns = @JoinColumn(name = "truck_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> drivers;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_truck",
            joinColumns = @JoinColumn(name = "truck_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private TruckOrder truckOrder;

    public Truck(Mark truckMark, TruckType truckType, int length, int width, float weight, float weightFull, String numberReg, String vinCode, String techPassport, List<Driver> drivers) {
        this.truckMark = truckMark;
        this.truckType = truckType;
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.weightFull = weightFull;
        this.numberReg = numberReg;
        this.vinCode = vinCode;
        this.techPassport = techPassport;
        this.drivers = drivers;
    }
}
