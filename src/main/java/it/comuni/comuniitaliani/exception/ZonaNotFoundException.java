package it.comuni.comuniitaliani.exception;

public class ZonaNotFoundException extends RuntimeException {
    public ZonaNotFoundException(Integer codice) {
        super("Zona non trovata: " + codice);
    }
}
