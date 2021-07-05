package overtimehours.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;


@Builder
@Data
@Component
public class OvertimeDto {
    private String date;
    private Double dailyHours;
    private Double daybreak;

    public OvertimeDto() {

    }

    public OvertimeDto(String date, Double hours, Double daybreak) {
        this.date = date;
        this.dailyHours = hours;
        this.daybreak = daybreak;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(Double dailyHours) {
        this.dailyHours = dailyHours;
    }

    public Double getDaybreak() {
        return daybreak;
    }

    public void setDaybreak(Double daybreak) {
        this.daybreak = daybreak;
    }
}
