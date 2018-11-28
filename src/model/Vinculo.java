package model;

/**
 * Classe de modelo que representa um vínculo do candidato com a UNIRIO
 */
public class Vinculo {

    private String tipo;
    private String nome;
    private String ano;

    //----------- Getters and Setters -----------

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
