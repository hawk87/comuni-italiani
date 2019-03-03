package it.comuni.comuniitaliani.mapper;

import it.comuni.comuniitaliani.model.Provincia;
import it.comuni.comuniitaliani.vm.ProvinciaVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProvinciaMapper implements Mapper<ProvinciaVM, Provincia> {

    @Override
    public ProvinciaVM dto2vm(Provincia dto) {
        log.debug("dto2vm {}", dto);
        final ProvinciaVM vm = new ProvinciaVM();
        vm.setCodice(dto.getCodice());
        vm.setNome(dto.getNome());
        return vm;
    }

    @Override
    public Provincia vm2dto(ProvinciaVM vm) {
        log.debug("vm2dto {}", vm);
        final Provincia dto = new Provincia();
        dto.setCodice(vm.getCodice());
        dto.setNome(vm.getNome());
        return dto;
    }
}
