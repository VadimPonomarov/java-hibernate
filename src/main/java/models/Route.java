package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private Address from;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private Address to;
    private float length;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_route",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private TruckOrder truckOrder;

    public Route(Address from, Address to, float length, TruckOrder truckOrder) {
        this.from = from;
        this.to = to;
        this.length = length;
        this.truckOrder = truckOrder;
    }
}
