package it.comuni.comuniitaliani.rest;

import it.comuni.comuniitaliani.mapper.Mapper;
import it.comuni.comuniitaliani.model.Regione;
import it.comuni.comuniitaliani.service.RegioneService;
import it.comuni.comuniitaliani.vm.RegioneVM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static it.comuni.comuniitaliani.security.Roles.ROLE_ADMIN;
import static it.comuni.comuniitaliani.security.Roles.ROLE_USER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/regioni")
public class RegioneRestService {
    private final RegioneService regioneService;
    private final Mapper<RegioneVM, Regione> regioneMapper;

    public RegioneRestService(RegioneService regioneService, Mapper<RegioneVM, Regione> regioneMapper) {
        this.regioneService = regioneService;
        this.regioneMapper = regioneMapper;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> add(@Valid @RequestBody RegioneVM vm) {
        final Regione regione = regioneMapper.vm2dto(vm);
        final Regione dto = regioneService.add(regione);
        final RegioneVM saved = regioneMapper.dto2vm(dto);
        return ResponseEntity
                .created(URI.create("/api/regioni/" + saved.getCodice()))
                .body(saved);
    }

    @GetMapping("/{codice}")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> get(@PathVariable Integer codice) {
        final Regione regione = regioneService.get(codice);
        return ResponseEntity
                .ok(regione);
    }

    @PutMapping("/{codice}")
    @ResponseStatus(ACCEPTED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> update(@PathVariable Integer codice, @Valid @RequestBody RegioneVM vm) {
        final Regione newRegione = regioneMapper.vm2dto(vm);
        final Regione dto = regioneService.update(codice, newRegione);
        final RegioneVM updated = regioneMapper.dto2vm(dto);
        return ResponseEntity
                .accepted()
                .body(updated);
    }

    @DeleteMapping("/{codice}")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> delete(@PathVariable Integer codice) {
        regioneService.delete(codice);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/all")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> addAll(@RequestBody List<RegioneVM> provinceVM) {
        final List<Regione> regioni = provinceVM.stream().map(regioneMapper::vm2dto).collect(Collectors.toList());
        regioneService.addAll(regioni);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> getAll() {
        final List<RegioneVM> regioni = regioneService.getAll().stream().map(regioneMapper::dto2vm).collect(Collectors.toList());
        return ResponseEntity
                .ok(regioni);
    }
}
