package preproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import preproject.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll();

    @Query("SELECT c FROM Car c ORDER BY c.id ASC")
    List<Car> listCarsByCount(@Param("count") int count, Pageable pageable);
}
