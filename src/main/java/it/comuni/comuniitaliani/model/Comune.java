package it.comuni.comuniitaliani.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comune {
    @Id
    private Integer codice;
    private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Zona zona;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Regione regione;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Provincia provincia;

    private String sigla;
    private String codiceCatastale;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Cap> cap;

    private Integer popolazione;
}
