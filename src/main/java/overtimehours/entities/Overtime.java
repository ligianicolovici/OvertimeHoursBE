package overtimehours.entities;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
public class Overtime {
    private Integer id;
    private LocalDate date;
    private Double hours;
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
