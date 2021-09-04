package overtimehours.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import overtimehours.dtos.OvertimeDto;
import overtimehours.services.OvertimeService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/records")
public class OvertimeController {

    private final OvertimeService overtimeService;

    @CrossOrigin
    @PostMapping(value = "/create", produces = {"application/json"})
    @ResponseBody
    public OvertimeDto createProduct(@RequestBody OvertimeDto overtimeDTO) throws InterruptedException, ExecutionException  {
        return overtimeService.createRecord(overtimeDTO);
    }

    @CrossOrigin()
    @DeleteMapping(value = "/delete/{targetRecordDate}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String targetRecordDate) throws InterruptedException, ExecutionException{
        overtimeService.deleteRecordByDate(targetRecordDate);
    }

    @CrossOrigin
    @GetMapping(value = "/all", produces = {"application/json"})
    @ResponseBody
    public List<OvertimeDto> getAllRecords()throws InterruptedException, ExecutionException {
        return overtimeService.getAllRecords();
    }

    @CrossOrigin
    @GetMapping(value = "/date/{targetRecordDate}" , produces = {"application/json"})
    @ResponseBody
    public OvertimeDto getRecordByDate(@PathVariable String targetRecordDate) throws InterruptedException, ExecutionException {
        return overtimeService.getRecordByDate(targetRecordDate);
    }

    @CrossOrigin
    @GetMapping(value = "/hours" , produces = {"application/json"})
    @ResponseBody
    public double getTotalNrOfOvertimeHours() throws InterruptedException, ExecutionException{
        return overtimeService.calculateTotalNrOfOvertimeHours();
    }

    @CrossOrigin
    @GetMapping(value = "/days" , produces = {"application/json"})
    @ResponseBody
    public double getTotalNrOfDaysForOvertime()throws InterruptedException, ExecutionException {
        return overtimeService.calculateTotalDaysForOvertimeHours();
    }

    @CrossOrigin
    @GetMapping(value = "/dates" , produces = {"application/json"})
    @ResponseBody
    public List<String> getAllRegisteredDates() throws InterruptedException, ExecutionException {
        return overtimeService.getAllRecordsDates();
    }


    @CrossOrigin
    @PostMapping(value = "/update", produces = {"application/json"})
    @ResponseBody
    public OvertimeDto updateRecord(@RequestBody OvertimeDto overtimeDTO) throws InterruptedException, ExecutionException {
        return overtimeService.updateRecord(overtimeDTO);
    }
}
