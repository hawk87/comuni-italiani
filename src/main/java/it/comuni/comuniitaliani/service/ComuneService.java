package it.comuni.comuniitaliani.service;

import it.comuni.comuniitaliani.exception.ComuneNotFoundException;
import it.comuni.comuniitaliani.model.Comune;

import java.util.List;


public interface ComuneService {
    Comune add(Comune comune);

    Comune get(Integer codice) throws ComuneNotFoundException;

    Comune update(Integer codice, Comune newComune);

    void delete(Integer codice);

    void addAll(List<Comune> comuni);

    List<Comune> getAll();
}
