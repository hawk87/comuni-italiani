package it.comuni.comuniitaliani.exception;

public class ComuneNotFoundException extends RuntimeException {
    public ComuneNotFoundException(Integer codice) {
        super("Comune non trovato: " + codice);
    }
}
