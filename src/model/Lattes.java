package model;

import java.util.ArrayList;
import java.util.List;

public class Lattes {
    List<Premio> premios = new ArrayList<>();
    List<String> artigos = new ArrayList<>();
    List<String> eventos = new ArrayList<>();

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }

    public List<String> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<String> artigos) {
        this.artigos = artigos;
    }

    public List<String> getEventos() {
        return eventos;
    }

    public void setEventos(List<String> eventos) {
        this.eventos = eventos;
    }
}
