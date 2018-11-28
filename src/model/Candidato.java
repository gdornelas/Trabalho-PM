package model;

import java.util.ArrayList;
import java.util.List;

public class Candidato {
    private String nome;
    private String reprovacoes;
    private String lattes;

    private List<Premio> premios = new ArrayList<>();
    private List<String> artigos = new ArrayList<>();
    private List<String> eventos = new ArrayList<>();
    private List<Vinculo> vinculos = new ArrayList<>();

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

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

    public String getReprovacoes() {
        return reprovacoes;
    }

    public void setReprovacoes(String reprovacoes) {
        this.reprovacoes = reprovacoes;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }
}
