package it.comuni.comuniitaliani.rest;

import it.comuni.comuniitaliani.mapper.Mapper;
import it.comuni.comuniitaliani.model.Comune;
import it.comuni.comuniitaliani.service.ComuneService;
import it.comuni.comuniitaliani.vm.ComuneVM;
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
@RequestMapping("/api/comuni")
public class ComuneRestService {
    private final ComuneService comuneService;
    private final Mapper<ComuneVM, Comune> comuneMapper;

    public ComuneRestService(ComuneService comuneService, Mapper<ComuneVM, Comune> comuneMapper) {
        this.comuneService = comuneService;
        this.comuneMapper = comuneMapper;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> add(@Valid @RequestBody ComuneVM vm) {
        final Comune comune = comuneMapper.vm2dto(vm);
        final Comune dto = comuneService.add(comune);
        final ComuneVM saved = comuneMapper.dto2vm(dto);
        return ResponseEntity
                .created(URI.create("/api/comuni/" + saved.getCodice()))
                .body(saved);
    }

    @GetMapping("/{codice}")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> get(@PathVariable Integer codice) {
        final Comune comune = comuneService.get(codice);
        return ResponseEntity
                .ok(comune);
    }

    @PutMapping("/{codice}")
    @ResponseStatus(ACCEPTED)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> update(@PathVariable Integer codice, @Valid @RequestBody ComuneVM vm) {
        final Comune newComune = comuneMapper.vm2dto(vm);
        final Comune dto = comuneService.update(codice, newComune);
        final ComuneVM updated = comuneMapper.dto2vm(dto);
        return ResponseEntity
                .accepted()
                .body(updated);
    }

    @DeleteMapping("/{codice}")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> delete(@PathVariable Integer codice) {
        comuneService.delete(codice);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/all")
    @ResponseStatus(NO_CONTENT)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<?> addAll(@RequestBody List<ComuneVM> comuniVM) {
        final List<Comune> comuni = comuniVM.stream().map(comuneMapper::vm2dto).collect(Collectors.toList());
        comuneService.addAll(comuni);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("")
    @ResponseStatus(OK)
    @Secured(ROLE_USER)
    public ResponseEntity<?> getAll() {
        final List<ComuneVM> comuni = comuneService.getAll().stream().map(comuneMapper::dto2vm).collect(Collectors.toList());
        return ResponseEntity
                .ok(comuni);
    }
}
