package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de modelo que representa um candidato
 */
public class Candidato {

    private String nome;
    private String semestresSemReprovacao;
    private String lattes;
    private int pontuacao = 0;

    private List<Premio> premios = new ArrayList<>();
    private List<Artigo> artigos = new ArrayList<>();
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

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
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

    public String getSemestresSemReprovacao() {
        return semestresSemReprovacao;
    }

    public void setSemestresSemReprovacao(String semestresSemReprovacao) {
        this.semestresSemReprovacao = semestresSemReprovacao;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuação) {
        this.pontuacao = pontuação;
    }
}
