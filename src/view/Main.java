package view;

import controller.ComandoNaoEncontradoException;
import controller.InputController;
import controller.LattesController;
import model.Candidato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Chama função que verifica parâmetros do programa
    public void verificaEntrada(String[] args) throws ComandoNaoEncontradoException {
        InputController.verificaParametros(args);
    }

    /*public Candidato candidatos(String xml){

        xml = "monica.xml";
        return LattesController.constroiCandidato(xml);
    }*/

    public void saidaCompleta(){

    }

    public void artigosQualis(){

    }

    public void artigosForaQualis(){

    }

    public void eventos(){

    }

    public void vinculos(){

    }

    public static void main(String[] args) {

        Main main = new Main();

        try{
            main.verificaEntrada(args);
        }catch (ComandoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
        /*
        for(int indice = 0; indice < args.length; indice++){
            if(args[indice].equals("-v")){
                boolean verboso = true;
            }
            if (args[indice].equals("-o")){

            }else{
                throw new Exception() e;
            }
        }
        */

        System.out.println("Hello World!");
    }
}
