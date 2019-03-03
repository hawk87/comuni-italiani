package it.comuni.comuniitaliani.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComuneVM {
    private Integer codice;
    private String nome;

    private ZonaVM zona;
    private RegioneVM regione;
    private ProvinciaVM provincia;

    private String sigla;
    private String codiceCatastale;
    private List<String> cap;
    private Integer popolazione;
}
