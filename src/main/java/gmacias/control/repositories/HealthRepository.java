package gmacias.control.repositories;

import gmacias.model.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Integer> {
}
