package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ComuneNotFoundException;
import it.comuni.comuniitaliani.model.Comune;
import it.comuni.comuniitaliani.repository.ComuneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ComuneServiceImpl implements ComuneService {

    private final ComuneRepository comuneRepository;

    public ComuneServiceImpl(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    @Override
    public Comune add(Comune comune) {
        log.debug("add {}", comune);
        return comuneRepository.save(comune);
    }

    @Override
    public Comune get(Integer codice) throws ComuneNotFoundException {
        log.debug("get {}", codice);
        return comuneRepository.findById(codice)
                .orElseThrow(() -> new ComuneNotFoundException(codice));
    }

    @Override
    public Comune update(Integer codice, Comune newComune) {
        return comuneRepository.findById(codice)
                .map(c -> {
                    c.setCodice(newComune.getCodice());
                    c.setNome(newComune.getNome());
                    c.setZona(newComune.getZona());
                    c.setRegione(newComune.getRegione());
                    c.setProvincia(newComune.getProvincia());
                    c.setSigla(newComune.getSigla());
                    c.setCodiceCatastale(newComune.getCodiceCatastale());
                    c.setCap(newComune.getCap());
                    c.setPopolazione(newComune.getPopolazione());

                    log.debug("update {}", c);
                    return comuneRepository.save(c);
                }).orElseGet(() -> {
                    newComune.setCodice(codice);

                    log.debug("put {}", newComune);
                    return comuneRepository.save(newComune);
                });
    }

    @Override
    public void delete(Integer codice) {
        log.debug("delete {}", codice);
        comuneRepository.deleteById(codice);
    }

    @Override
    public void addAll(List<Comune> comuni) {
        log.debug("addAll");
        comuneRepository.saveAll(comuni);
    }

    @Override
    public List<Comune> getAll() {
        log.debug("getAll");
        return comuneRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Comune::getCodice))
                .collect(Collectors.toList());
    }
}
