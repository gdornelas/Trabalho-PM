package model;

import java.util.ArrayList;
import java.util.List;

public class Lattes {
    private String nome;

    private List<Premio> premios = new ArrayList<>();
    private List<String> artigos = new ArrayList<>();
    private List<String> eventos = new ArrayList<>();
    private List<String> vinculos = new ArrayList<>();

    //----------- Getters and Setters -----------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public List<String> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<String> vinculos) {
        this.vinculos = vinculos;
    }
}
