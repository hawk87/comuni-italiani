package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.RegioneNotFoundException;
import it.comuni.comuniitaliani.model.Regione;
import it.comuni.comuniitaliani.repository.RegioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegioneServiceImpl implements RegioneService {

    private final RegioneRepository regioneRepository;

    public RegioneServiceImpl(RegioneRepository regioneRepository) {
        this.regioneRepository = regioneRepository;
    }

    @Override
    public Regione add(Regione regione) {
        log.debug("add {}", regione);
        return regioneRepository.save(regione);
    }

    @Override
    public Regione get(Integer codice) throws RegioneNotFoundException {
        log.debug("get {}", codice);
        return regioneRepository.findById(codice)
                .orElseThrow(() -> new RegioneNotFoundException(codice));
    }

    @Override
    public Regione update(Integer codice, Regione newRegione) {
        return regioneRepository.findById(codice)
                .map(p -> {
                    p.setCodice(newRegione.getCodice());
                    p.setNome(newRegione.getNome());

                    log.debug("update {}", p);
                    return regioneRepository.save(p);
                }).orElseGet(() -> {
                    newRegione.setCodice(codice);

                    log.debug("put {}", newRegione);
                    return regioneRepository.save(newRegione);
                });
    }

    @Override
    public void delete(Integer codice) {
        log.debug("delete {}", codice);
        regioneRepository.deleteById(codice);
    }

    @Override
    public void addAll(List<Regione> regioni) {
        log.debug("addAll");
        regioneRepository.saveAll(regioni);
    }

    @Override
    public List<Regione> getAll() {
        log.debug("getAll");
        return regioneRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Regione::getCodice))
                .collect(Collectors.toList());
    }
}
