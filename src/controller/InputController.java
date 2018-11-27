package controller;

import java.util.Arrays;

public class InputController {
    public static void verificaParametros(String[] args) {

        //Verificação da indicação do arquivo de saída
        if(Arrays.stream(args).anyMatch("-o"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("ArroySaida");
        }else{
            IllegalArgumentException erro = new IllegalArgumentException();
            System.out.println("qqq"+erro.getMessage());
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
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1, Arrays.asList(args).indexOf("-o") + 2);
            System.out.println("ArroyLog");
        }else{
            IllegalArgumentException erro = new IllegalArgumentException();
            System.out.println("qqq"+erro.getMessage());
        }

        //Verificação de indicação do modo verboso
        if(Arrays.stream(args).anyMatch("-v"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }

        //Verificação de indicação do comando a ser processado

        //Saída completa
        if(Arrays.stream(args).anyMatch("-c"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }
        //Saída referente aos prêmios
        else if(Arrays.stream(args).anyMatch("-pr"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }
        //Saída referente aos artigos completos no Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-ar"::equals)){
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
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
            //main.setArquivoSaida(Arrays.asList(args).indexOf("-o") + 1);
            System.out.println("Verboso");
        }
        //Caso nenhum comando tenha sido indicado
        else{

        }


    }
}
