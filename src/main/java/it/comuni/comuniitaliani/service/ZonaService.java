package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ZonaNotFoundException;
import it.comuni.comuniitaliani.model.Zona;

import java.util.List;

public interface ZonaService {
    Zona add(Zona zona);

    Zona get(Integer codice) throws ZonaNotFoundException;

    Zona update(Integer codice, Zona newZona);

    void delete(Integer codice);

    void addAll(List<Zona> zone);

    List<Zona> getAll();
}
