package it.comuni.comuniitaliani.repository;

import it.comuni.comuniitaliani.model.Regione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegioneRepository extends JpaRepository<Regione, Integer> {
}
