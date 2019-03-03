package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ZonaNotFoundException;
import it.comuni.comuniitaliani.model.Zona;
import it.comuni.comuniitaliani.repository.ZonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZonaServiceImpl implements ZonaService {
    
    private final ZonaRepository zonaRepository;
    
    public ZonaServiceImpl(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }
    
    @Override
    public Zona add(Zona zona) {
        log.debug("add {}", zona);
        return zonaRepository.save(zona);
    }

    @Override
    public Zona get(Integer codice) throws ZonaNotFoundException {
        log.debug("get {}", codice);
        return zonaRepository.findById(codice)
                .orElseThrow(() -> new ZonaNotFoundException(codice));
    }

    @Override
    public Zona update(Integer codice, Zona newZona) {
        return zonaRepository.findById(codice)
                .map(p -> {
                    p.setCodice(newZona.getCodice());
                    p.setNome(newZona.getNome());

                    log.debug("update {}", p);
                    return zonaRepository.save(p);
                }).orElseGet(() -> {
                    newZona.setCodice(codice);

                    log.debug("put {}", newZona);
                    return zonaRepository.save(newZona);
                });
    }

    @Override
    public void delete(Integer codice) {
        log.debug("delete {}", codice);
        zonaRepository.deleteById(codice);
    }

    @Override
    public void addAll(List<Zona> zone) {
        log.debug("addAll");
        zonaRepository.saveAll(zone);
    }

    @Override
    public List<Zona> getAll() {
        log.debug("getAll");
        return zonaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Zona::getCodice))
                .collect(Collectors.toList());
    }
}
