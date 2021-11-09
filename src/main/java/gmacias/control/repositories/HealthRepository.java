package org.openjfx.control.repositories;

import org.openjfx.model.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Integer> {
}
