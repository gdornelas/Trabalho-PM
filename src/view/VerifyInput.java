package view;

import controller.ComandoNaoEncontradoException;
import controller.LattesController;
import controller.SetFiles;
import model.Candidato;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controlador de argumentos de entrada do programa
 */
public class VerifyInput {

    /**
     * Função que verifica os parâmetros forncecidos e executa suas funções
     * @param args: lista de argumentos fornecidos
     * @throws ComandoNaoEncontradoException : exceção caso algum argumento essencial não seja encontrado
     */
    public void verificaParametros(String[] args) throws ComandoNaoEncontradoException {

        List<Candidato> candidatoList = new ArrayList<>();
        boolean verboso;
        List<File> caminhos = new ArrayList<>();
        LattesController lattesController = new LattesController();


        //Verificação da indicação do arquivo de saída (ESSENCIAL)
        if(Arrays.stream(args).anyMatch("-o"::equals)){

            SetFiles setSaida = new SetFiles();
            caminhos.add(setSaida.setArquivoSaida(args[Arrays.asList(args).indexOf("-o") + 1]));
        }else{
            throw new ComandoNaoEncontradoException("Comando -o não encontrado!");
        }

        //Verificação de indicação do arquivo de log
        if(Arrays.stream(args).anyMatch("-l"::equals)){
            SetFiles setSaida = new SetFiles();
            caminhos.add(setSaida.setArquivoSaida(args[Arrays.asList(args).indexOf("-l") + 1]));
        }else{
            throw new ComandoNaoEncontradoException("Comando -l não encontrado!");
        }

        //Verificação de indicação de pelo menos um candidato (ESSENCIAL)
        if(Arrays.stream(args).anyMatch("-a"::equals)){

            for (int i = 0; i < args.length; i++){
                if (args[i].equals("-a")){
                    candidatoList.add(lattesController.constroiCandidato(args[i + 1], args[i + 2]));
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
            lattesController.setPremios(candidatoList);
            lattesController.setArtigos(candidatoList);
            lattesController.setVinculo(candidatoList);
            try {
                lattesController.calculaPremios(candidatoList, verboso, caminhos.get(0));
                lattesController.calculaArtigosQualis(candidatoList, verboso, caminhos);
                lattesController.calculaVinculo(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente aos prêmios
        else if(Arrays.stream(args).anyMatch("-pr"::equals)){
            lattesController.setPremios(candidatoList);
            try {
                lattesController.calculaPremios(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente aos artigos completos no Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-ar"::equals)){
            lattesController.setArtigos(candidatoList);
            lattesController.calculaArtigosQualis(candidatoList, verboso, caminhos);
        }

        //Saída referente aos artigos completos fora do Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-anr"::equals)){
            lattesController.setArtigos(candidatoList);
            //lattesController.calculaArtigosQualisFora(candidatoList, verboso, caminhos);

        }

        //Saída referente a participação de eventos classificados
        else if(Arrays.stream(args).anyMatch("-pe"::equals)){
            //lattesController.setEventos(candidatoList);
            //lattesController.calculaEventos(candidatoList, verboso, caminhos);
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