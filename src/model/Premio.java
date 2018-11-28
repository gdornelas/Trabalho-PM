package model;

/**
 * Classe de modelo que representa um prêmio do candidato
 */
public class Premio {

    private String nome;
    private String ano;

    //----------- Getters and Setters -----------

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

    @Override
    public String toString(){
        return "Premio [nome= " + nome + " ano = " + ano +"]";
    }
}
