package it.comuni.comuniitaliani.exception;

public class RegioneNotFoundException extends RuntimeException {
    public RegioneNotFoundException(Integer codice) {
        super("Regione non trovata: " + codice);
    }
}
