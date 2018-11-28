package view;

import controller.ComandoNaoEncontradoException;
import controller.InputController;

public class Main {

    /**
     * Chama função que verifica os argumentos passados na execução do programa
     * @param args: lista de argumentos passados para a main
     * @throws ComandoNaoEncontradoException: exceção caso algum argumento essencial não seja encontrado
     */
    public void verificaEntrada(String[] args) throws ComandoNaoEncontradoException {

        InputController inputController = new InputController();
        inputController.verificaParametros(args);
    }

    /**
     * Função Main
     * @param args: argumentos passados na chamada do programa
     */
    public static void main(String[] args) {

        Main main = new Main();

        try{
            main.verificaEntrada(args);
        }catch (ComandoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
