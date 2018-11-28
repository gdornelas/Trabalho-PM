package controller;

/**
 * Excessão que indica se um argumento essencial para a execução do programa não foi encontrado
 */
public class ComandoNaoEncontradoException extends Exception {

    /**
     * Método da exceção que lança uma mensagem indicando o argumento não encontrado
     * @param mensagem
     */
    public ComandoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
