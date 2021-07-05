package overtimehours.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import overtimehours.entities.Overtime;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface RecordsRepository extends JpaRepository<Overtime, Integer> {
    Optional<Overtime> findByDate(LocalDate givenDate);
    List<Overtime> findAllByIdIsNotNull();
    void deleteById(Integer id);
}
