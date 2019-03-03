package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ProvinciaNotFoundException;
import it.comuni.comuniitaliani.model.Provincia;

import java.util.List;

public interface ProvinciaService {
    Provincia add(Provincia provincia);

    Provincia get(Integer codice) throws ProvinciaNotFoundException;

    Provincia update(Integer codice, Provincia newProvincia);

    void delete(Integer codice);

    void addAll(List<Provincia> province);

    List<Provincia> getAll();
}
