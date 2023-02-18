package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Long> {
    boolean existsById(int sectionid);
}
