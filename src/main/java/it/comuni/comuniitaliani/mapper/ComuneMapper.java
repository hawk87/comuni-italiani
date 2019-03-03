package it.comuni.comuniitaliani.mapper;

import it.comuni.comuniitaliani.model.Cap;
import it.comuni.comuniitaliani.model.Comune;
import it.comuni.comuniitaliani.model.Provincia;
import it.comuni.comuniitaliani.model.Regione;
import it.comuni.comuniitaliani.model.Zona;
import it.comuni.comuniitaliani.vm.ComuneVM;
import it.comuni.comuniitaliani.vm.ProvinciaVM;
import it.comuni.comuniitaliani.vm.RegioneVM;
import it.comuni.comuniitaliani.vm.ZonaVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ComuneMapper implements Mapper<ComuneVM, Comune> {

    private final Mapper<ZonaVM, Zona> zonaMapper;
    private final Mapper<RegioneVM, Regione> regioneMapper;
    private final Mapper<ProvinciaVM, Provincia> provinciaMapper;

    public ComuneMapper(Mapper<ZonaVM, Zona> zonaMapper, Mapper<RegioneVM, Regione> regioneMapper, Mapper<ProvinciaVM, Provincia> provinciaMapper) {
        this.zonaMapper = zonaMapper;
        this.regioneMapper = regioneMapper;
        this.provinciaMapper = provinciaMapper;
    }

    @Override
    public ComuneVM dto2vm(Comune dto) {
        log.debug("dto2vm {}", dto);
        final ComuneVM vm = new ComuneVM();

        vm.setCodice(dto.getCodice());
        vm.setNome(dto.getNome());

        vm.setZona(zonaMapper.dto2vm(dto.getZona()));
        vm.setRegione(regioneMapper.dto2vm(dto.getRegione()));
        vm.setProvincia(provinciaMapper.dto2vm(dto.getProvincia()));

        vm.setSigla(dto.getSigla());
        vm.setCodiceCatastale(dto.getCodiceCatastale());
        vm.setCap(dto.getCap().stream().map(Cap::getCodice).collect(Collectors.toList()));
        vm.setPopolazione(dto.getPopolazione());

        return vm;
    }

    @Override
    public Comune vm2dto(ComuneVM vm) {
        log.debug("vm2dto {}", vm);
        final Comune dto = new Comune();

        dto.setCodice(vm.getCodice());
        dto.setNome(vm.getNome());

        dto.setZona(zonaMapper.vm2dto(vm.getZona()));
        dto.setRegione(regioneMapper.vm2dto(vm.getRegione()));
        dto.setProvincia(provinciaMapper.vm2dto(vm.getProvincia()));

        dto.setSigla(vm.getSigla());
        dto.setCodiceCatastale(vm.getCodiceCatastale());
        dto.setCap(vm.getCap().stream().map(Cap::new).collect(Collectors.toSet()));
        dto.setPopolazione(vm.getPopolazione());

        return dto;
    }
}
