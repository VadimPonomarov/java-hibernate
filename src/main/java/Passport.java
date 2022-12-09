import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String series;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "passport", fetch = FetchType.LAZY)
    private User user;

    public Passport(String number, String series) {
        this.number = number;
        this.series = series;
    }
}
