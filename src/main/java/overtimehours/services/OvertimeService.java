package overtimehours.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import overtimehours.dtos.OvertimeDto;
import overtimehours.entities.Overtime;
import overtimehours.mappers.OvertimeMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OvertimeService {
    private final OvertimeMapper overtimeMapper;
    private final static Integer DAILY_QUANTUM = 8;
    public static final String COL_NAME = "records";

    public OvertimeDto createRecord(OvertimeDto overtimeDTO) throws InterruptedException, ExecutionException {
        getAllDbKeys();
        OvertimeDto newOvertimeRecord;
        LocalDate recordDate;
        Double recordHours = calculateOverTimeAmount(overtimeDTO.getDailyHours(), overtimeDTO.getDaybreak());
        Double recordBreak = overtimeDTO.getDaybreak();

        if (overtimeDTO.getDate() != null) {
            recordDate = LocalDate.parse(overtimeDTO.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else {
            recordDate = LocalDate.now();
        }
        List<OvertimeDto> allCurrentRecords = getAllRecords();
        boolean existsRecordForThisDay = false;
        Overtime createNewOvertimeRecord = Overtime.builder()
                .date(recordDate)
                .hours(recordHours)
                .breakTime(recordBreak)
                .build();

        if (allCurrentRecords != null) {
            for (OvertimeDto record : allCurrentRecords) {
                if (record.getDate().equals(overtimeDTO.getDate())) {
                    existsRecordForThisDay = true;
                }
            }
        }

        if (!existsRecordForThisDay) {

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(overtimeDTO.getDate()).set(overtimeMapper.overtimeToOvertimeDto(createNewOvertimeRecord));
            collectionsApiFuture.get().getUpdateTime().toString();
            newOvertimeRecord = overtimeMapper.overtimeToOvertimeDto(createNewOvertimeRecord);
        } else {
            newOvertimeRecord = null;
        }
        return newOvertimeRecord;
    }

    public OvertimeDto updateRecord(OvertimeDto overtimeDTO) throws InterruptedException, ExecutionException  {
        OvertimeDto updatedOvertimeRecord;
        if(getAllDbKeys().contains(overtimeDTO.getDate())){
            Firestore dbFirestore = FirestoreClient.getFirestore();
            overtimeDTO.setDailyHours(calculateOverTimeAmount(overtimeDTO.getDailyHours(),overtimeDTO.getDaybreak()));
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(overtimeDTO.getDate()).set(overtimeDTO);
            collectionsApiFuture.get().getUpdateTime().toString();
            updatedOvertimeRecord = overtimeDTO;
        }else{
            updatedOvertimeRecord = null;
        }
        return updatedOvertimeRecord;
    }

    public OvertimeDto getRecordByDate(String givenDate) throws InterruptedException, ExecutionException {
        boolean recordExistsInDB =checkIfRecordExists(givenDate);
        if(recordExistsInDB){
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(givenDate);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            OvertimeDto recordFromDb = null;
            if(document.exists()) {
                recordFromDb = document.toObject(OvertimeDto.class);
                if(recordFromDb!=null){
                    recordFromDb.setDailyHours(calculateWorkTimeAmount(recordFromDb.getDailyHours(),recordFromDb.getDaybreak()));
                }
                return recordFromDb;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    public List<OvertimeDto> getAllRecords() throws InterruptedException, ExecutionException {
        List<OvertimeDto> resultedOvertimeRecords = new ArrayList<>();
        for (String key :  getAllDbKeys()) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(key);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            OvertimeDto overtimeDtoFromDb = null;
            if (document.exists()) {
                overtimeDtoFromDb = document.toObject(OvertimeDto.class);
                overtimeDtoFromDb.setDate(key);
                resultedOvertimeRecords.add(overtimeDtoFromDb);
            }
        }
        return resultedOvertimeRecords;
    }

    public List<String> getAllRecordsDates() throws InterruptedException, ExecutionException {
        return getAllDbKeys();
    }

    public void deleteRecordByDate(String givenDate) throws InterruptedException, ExecutionException {
        boolean recordExistsInDB =false;
       if(getAllDbKeys().contains(givenDate)){
           recordExistsInDB=true;
       }
       if(recordExistsInDB){
           Firestore dbFirestore = FirestoreClient.getFirestore();
           ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(givenDate).delete();
       }
    }

    public double calculateTotalNrOfOvertimeHours() throws InterruptedException, ExecutionException {
        Double sum = Double.valueOf(0);
        List<OvertimeDto> allRecords = getAllRecords();
        for (OvertimeDto record : allRecords) {
            sum = sum + record.getDailyHours();
        }
        return sum;
    }

    public double calculateTotalDaysForOvertimeHours() throws InterruptedException, ExecutionException {
        final Double hoursAccumulated = calculateTotalNrOfOvertimeHours();
        if ((hoursAccumulated / 8) < 0) {
            return 0;
        } else {
            return Math.round((hoursAccumulated / 8) * 10) / 10.0;
        }
    }

    private double calculateOverTimeAmount(Double dailyRecord, Double dayBreak) {
        return dailyRecord - DAILY_QUANTUM - dayBreak;
    }

    private double calculateWorkTimeAmount(Double dailyRecord, Double dayBreak) {
        return dailyRecord + DAILY_QUANTUM + dayBreak;
    }

    private List<String> getAllDbKeys()throws InterruptedException, ExecutionException {
        List<String> dbKeys = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("records").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            dbKeys.add(document.getId());
        }
        return dbKeys;
    }
    private boolean checkIfRecordExists(String recordDate)throws  InterruptedException, ExecutionException {
        boolean recordExistsInDB =false;
        if(getAllDbKeys().contains(recordDate)){
            recordExistsInDB=true;
        }
        return recordExistsInDB;
    }

}
