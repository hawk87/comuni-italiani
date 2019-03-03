package it.comuni.comuniitaliani.exception;

public class ProvinciaNotFoundException extends RuntimeException {
    public ProvinciaNotFoundException(Integer codice) {
        super("Provincia non trovata: " + codice);
    }
}
