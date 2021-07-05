package overtimehours.entities;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "records")
public class Overtime {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 100)
    private LocalDate date;
    @Column(nullable = false, length = 100)
    private Double hours;
    @Column(nullable = false, length =100 )
    private Double breakTime;

    public Overtime() {
    }

    public Overtime(Integer id, LocalDate date, Double hours,Double breakTime) {
        this.id = id;
        this.date = date;
        this.hours = hours;
        this.breakTime = breakTime;
    }
}
