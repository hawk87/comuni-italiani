package it.comuni.comuniitaliani.repository;

import it.comuni.comuniitaliani.model.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Integer> {
}
