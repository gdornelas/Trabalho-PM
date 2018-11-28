package model;

/**
 * Classe de modelo que representa um artigo do candidato
 */
public class Artigo {

    private String ano;
    private String nome;
    private String local;
    private String qualis;

    //----------- Getters and Setters -----------

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getQualis() {
        return qualis;
    }

    public void setQualis(String qualis) {
        this.qualis = qualis;
    }
}
