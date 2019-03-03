package it.comuni.comuniitaliani.mapper;

import it.comuni.comuniitaliani.model.Zona;
import it.comuni.comuniitaliani.vm.ZonaVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ZonaMapper implements Mapper<ZonaVM, Zona> {

    @Override
    public ZonaVM dto2vm(Zona dto) {
        log.debug("dto2vm {}", dto);
        final ZonaVM vm = new ZonaVM();
        vm.setCodice(dto.getCodice());
        vm.setNome(dto.getNome());
        return vm;
    }

    @Override
    public Zona vm2dto(ZonaVM vm) {
        log.debug("vm2dto {}", vm);
        final Zona dto = new Zona();
        dto.setCodice(vm.getCodice());
        dto.setNome(vm.getNome());
        return dto;
    }
}
