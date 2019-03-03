package it.comuni.comuniitaliani.rest;

import it.comuni.comuniitaliani.mapper.Mapper;
import it.comuni.comuniitaliani.model.Provincia;
import it.comuni.comuniitaliani.service.ProvinciaService;
import it.comuni.comuniitaliani.vm.ProvinciaVM;
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

import static it.comuni.comuniitaliani.Roles.ROLE_ADMIN;
import static it.comuni.comuniitaliani.Roles.ROLE_USER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/province")
public class ProvinciaRestService {
    private final ProvinciaService provinciaService;
    private final Mapper<ProvinciaVM, Provincia> provinciaMapper;

    public ProvinciaRestService(ProvinciaService provinciaService, Mapper<ProvinciaVM, Provincia> provinciaMapper) {
        this.provinciaService = provinciaService;
        this.provinciaMapper = provinciaMapper;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> add(@Valid @RequestBody ProvinciaVM vm) {
        final Provincia provincia = provinciaMapper.vm2dto(vm);
        final Provincia dto = provinciaService.add(provincia);
        final ProvinciaVM saved = provinciaMapper.dto2vm(dto);
        return ResponseEntity
                .created(URI.create("/api/province/" + saved.getCodice()))
                .body(saved);
    }

    @GetMapping("/{codice}")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> get(@PathVariable Integer codice) {
        final Provincia provincia = provinciaService.get(codice);
        return ResponseEntity
                .ok(provincia);
    }

    @PutMapping("/{codice}")
    @ResponseStatus(ACCEPTED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> update(@PathVariable Integer codice, @Valid @RequestBody ProvinciaVM vm) {
        final Provincia newProvincia = provinciaMapper.vm2dto(vm);
        final Provincia dto = provinciaService.update(codice, newProvincia);
        final ProvinciaVM updated = provinciaMapper.dto2vm(dto);
        return ResponseEntity
                .accepted()
                .body(updated);
    }

    @DeleteMapping("/{codice}")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> delete(@PathVariable Integer codice) {
        provinciaService.delete(codice);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/all")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> addAll(@RequestBody List<ProvinciaVM> provinceVM) {
        final List<Provincia> province = provinceVM.stream().map(provinciaMapper::vm2dto).collect(Collectors.toList());
        provinciaService.addAll(province);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> getAll() {
        final List<ProvinciaVM> province = provinciaService.getAll().stream().map(provinciaMapper::dto2vm).collect(Collectors.toList());
        return ResponseEntity
                .ok(province);
    }
}
