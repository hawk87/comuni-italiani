package it.comuni.comuniitaliani.rest;

import it.comuni.comuniitaliani.mapper.Mapper;
import it.comuni.comuniitaliani.model.Zona;
import it.comuni.comuniitaliani.service.ZonaService;
import it.comuni.comuniitaliani.vm.ZonaVM;
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
@RequestMapping("/api/zone")
public class ZonaRestService {
    private final ZonaService zonaService;
    private final Mapper<ZonaVM, Zona> zonaMapper;

    public ZonaRestService(ZonaService zonaService, Mapper<ZonaVM, Zona> zonaMapper) {
        this.zonaService = zonaService;
        this.zonaMapper = zonaMapper;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> add(@Valid @RequestBody ZonaVM vm) {
        final Zona zona = zonaMapper.vm2dto(vm);
        final Zona dto = zonaService.add(zona);
        final ZonaVM saved = zonaMapper.dto2vm(dto);
        return ResponseEntity
                .created(URI.create("/api/zone/" + saved.getCodice()))
                .body(saved);
    }

    @GetMapping("/{codice}")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> get(@PathVariable Integer codice) {
        final Zona zona = zonaService.get(codice);
        return ResponseEntity
                .ok(zona);
    }

    @PutMapping("/{codice}")
    @ResponseStatus(ACCEPTED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> update(@PathVariable Integer codice, @Valid @RequestBody ZonaVM vm) {
        final Zona newZona = zonaMapper.vm2dto(vm);
        final Zona dto = zonaService.update(codice, newZona);
        final ZonaVM updated = zonaMapper.dto2vm(dto);
        return ResponseEntity
                .accepted()
                .body(updated);
    }

    @DeleteMapping("/{codice}")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> delete(@PathVariable Integer codice) {
        zonaService.delete(codice);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/all")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> addAll(@RequestBody List<ZonaVM> provinceVM) {
        final List<Zona> zone = provinceVM.stream().map(zonaMapper::vm2dto).collect(Collectors.toList());
        zonaService.addAll(zone);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> getAll() {
        final List<ZonaVM> zone = zonaService.getAll().stream().map(zonaMapper::dto2vm).collect(Collectors.toList());
        return ResponseEntity
                .ok(zone);
    }
}
