package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.RegioneNotFoundException;
import it.comuni.comuniitaliani.model.Regione;

import java.util.List;

public interface RegioneService {
    Regione add(Regione regione);

    Regione get(Integer codice) throws RegioneNotFoundException;

    Regione update(Integer codice, Regione newRegione);

    void delete(Integer codice);

    void addAll(List<Regione> regioni);

    List<Regione> getAll();
}
