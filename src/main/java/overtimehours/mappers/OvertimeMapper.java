package overtimehours.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import overtimehours.dtos.OvertimeDto;
import overtimehours.entities.Overtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OvertimeMapper {
    public OvertimeDto overtimeToOvertimeDto(Overtime overtime) {
    return OvertimeDto.builder()
            .date(overtime.getDate().format(DateTimeFormatter.ofPattern("d.MM.yyyy")).toString())
            .dailyHours(overtime.getHours())
            .daybreak(overtime.getBreakTime())
            .build();
}

    public Overtime overtimeDtoToOvertime(OvertimeDto overtimeDto) {
        return Overtime.builder()
                .date(LocalDate.parse(overtimeDto.getDate(), DateTimeFormatter.ofPattern("d.MM.yyyy")))
                .hours(overtimeDto.getDailyHours())
                .breakTime(overtimeDto.getDaybreak())
                .build();
    }

    public List<OvertimeDto> overtimeListToOvertimeDtoList(List<Overtime> overtimeList) {
        List<OvertimeDto> result = new ArrayList<>();
        for (Overtime overtime : overtimeList) {
            result.add(overtimeToOvertimeDto(overtime));
        }
        return result;
    }

}
