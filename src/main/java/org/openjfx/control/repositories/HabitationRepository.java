package org.openjfx.control.repositories;

import org.openjfx.model.entity.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Integer> {
}
