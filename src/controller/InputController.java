package controller;

import model.Candidato;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputController {
    public static void verificaParametros(String[] args) throws ComandoNaoEncontradoException {

        List<Candidato> candidatoList = new ArrayList<>();
        boolean verboso;
        List<File> caminhos = new ArrayList<>();
        LattesController lattesController = new LattesController();


        //Verificação da indicação do arquivo de saída
        if(Arrays.stream(args).anyMatch("-o"::equals)){

            SetFiles setSaida = new SetFiles();
            caminhos.add(setSaida.setArquivoSaida(args[Arrays.asList(args).indexOf("-o") + 1]));
            System.out.println("ArroySaida");
        }else{
            throw new ComandoNaoEncontradoException("Comando -o não encontrado!");
        }

        //Verificação de indicação do arquivo de log
        if(Arrays.stream(args).anyMatch("-l"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("ArroyLog");
        }else{
            IllegalArgumentException erro = new IllegalArgumentException();
            System.out.println("qqq"+erro.getMessage());
        }

        //Verificação de indicação de pelo menos um candidato
        if(Arrays.stream(args).anyMatch("-a"::equals)){
            //TODO
            //List<Candidato> candidatoList = new ArrayList<>();
            for (int i = 0; i < args.length; i++){
                if (args[i].equals("-a")){
                    candidatoList.add(LattesController.constroiCandidato(args[i + 1], args[i + 2]));
                }
            }
        }else{
            throw new ComandoNaoEncontradoException("Comando -a não encontrado");
        }

        //Verificação de indicação do modo verboso
        if(Arrays.stream(args).anyMatch("-v"::equals)){
            verboso = true;
            System.out.println("Modo verboso ativado");
        }else{
            verboso = false;
        }

        //Verificação de indicação do comando a ser processado

        //Saída completa
        if(Arrays.stream(args).anyMatch("-c"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }

        //Saída referente aos prêmios
        else if(Arrays.stream(args).anyMatch("-pr"::equals)){
            LattesController.setPremios(candidatoList);
            try {
                LattesController.calculaPremios(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente aos artigos completos no Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-ar"::equals)){
            lattesController.setArtigos();
            lattesController.calculaArtigosQualis();
        }

        //Saída referente aos artigos completos fora do Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-anr"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }

        //Saída referente a participação de eventos classificados
        else if(Arrays.stream(args).anyMatch("-pe"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }

        //Saída referente a existência de vínculo com a UNIRIO
        else if(Arrays.stream(args).anyMatch("-vi"::equals)){
            lattesController.setVinculo(candidatoList);
            lattesController.calculaVinculo(candidatoList, verboso, caminhos.get(0));
        }

        //Caso nenhum comando tenha sido indicado
        else{
            throw new ComandoNaoEncontradoException("Nenhum parâmetro foi informado!");
        }


    }
}
