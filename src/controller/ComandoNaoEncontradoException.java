package controller;

public class ComandoNaoEncontradoException extends Exception {

    public ComandoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
