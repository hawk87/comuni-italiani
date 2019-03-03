package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ProvinciaNotFoundException;
import it.comuni.comuniitaliani.model.Provincia;
import it.comuni.comuniitaliani.repository.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Provincia add(Provincia provincia) {
        log.debug("add {}", provincia);
        return provinciaRepository.save(provincia);
    }

    @Override
    public Provincia get(Integer codice) throws ProvinciaNotFoundException {
        log.debug("get {}", codice);
        return provinciaRepository.findById(codice)
                .orElseThrow(() -> new ProvinciaNotFoundException(codice));
    }

    @Override
    public Provincia update(Integer codice, Provincia newProvincia) {
        return provinciaRepository.findById(codice)
                .map(p -> {
                    p.setCodice(newProvincia.getCodice());
                    p.setNome(newProvincia.getNome());

                    log.debug("update {}", p);
                    return provinciaRepository.save(p);
                }).orElseGet(() -> {
                    newProvincia.setCodice(codice);

                    log.debug("put {}", newProvincia);
                    return provinciaRepository.save(newProvincia);
                });
    }

    @Override
    public void delete(Integer codice) {
        log.debug("delete {}", codice);
        provinciaRepository.deleteById(codice);
    }

    @Override
    public void addAll(List<Provincia> province) {
        log.debug("addAll");
        provinciaRepository.saveAll(province);
    }

    @Override
    public List<Provincia> getAll() {
        log.debug("getAll");
        return provinciaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Provincia::getCodice))
                .collect(Collectors.toList());
    }
}
