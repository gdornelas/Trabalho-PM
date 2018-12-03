package view;

import controller.ComandoNaoEncontradoException;
import model.ComparadorPontos;
import controller.LattesControllerSingleton;
import controller.SetFiles;
import model.Candidato;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Controlador de argumentos de entrada do programa
 */
public class VerificaEntradaSingleton {

    //--------------------------------------------------- Singleton ---------------------------------------------------

    //Criação do objeto
    private static VerificaEntradaSingleton instanciaVerificaEntradaSingleton;

    /**
     * Construtor privado pois a classe é um Singleton
     */
    private VerificaEntradaSingleton(){}

    /**
     * Método de instanciação do objeto
     * @return: instância única do objeto VerificaEntradaSingleton
     */
    public static VerificaEntradaSingleton getInstanciaVerificaEntradaSingleton(){
        if(instanciaVerificaEntradaSingleton == null){
            instanciaVerificaEntradaSingleton = new VerificaEntradaSingleton();
        }
        return instanciaVerificaEntradaSingleton;
    }

    //--------------------------------------------------- Métodos ---------------------------------------------------

    /**
     * Função que verifica os parâmetros fornecidos e executa suas funções
     * @param args: lista de argumentos fornecidos
     * @throws ComandoNaoEncontradoException: exceção caso algum argumento essencial não seja encontrado
     */
    public void verificaParametros(String[] args) throws ComandoNaoEncontradoException {

        List<Candidato> candidatoList = new ArrayList<>();
        boolean verboso;
        List<File> caminhos = new ArrayList<>();

        LattesControllerSingleton lattesController = LattesControllerSingleton.getInstanciaLattesControllerSingleton();


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

            for (Candidato candidato : candidatoList){
                candidato.setPremios(lattesController.setPremios(candidato));
                candidato.setVinculos(lattesController.setVinculos(candidato));
                candidato.setArtigos(lattesController.setArtigos(candidato));

                int pontuacaoTotal = 0;

                pontuacaoTotal += lattesController.calculaPremios(candidato);
                pontuacaoTotal += lattesController.calculaVinculos(candidato);
                pontuacaoTotal += lattesController.calculaReprovacoes(candidato);
                pontuacaoTotal += lattesController.calculaArtigosQualis(candidato);
                candidato.setPontuacao(pontuacaoTotal);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escreveTotal(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //Saída referente aos prêmios
        else if(Arrays.stream(args).anyMatch("-pr"::equals)){

            for (Candidato candidato : candidatoList){
                candidato.setPremios(lattesController.setPremios(candidato));
                int pontuacaoPremios = lattesController.calculaPremios(candidato);
                candidato.setPontuacao(pontuacaoPremios);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escrevePremio(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente aos artigos completos no Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-ar"::equals)){

            for (Candidato candidato : candidatoList){
                candidato.setArtigos(lattesController.setArtigos(candidato));
                int pontuacaoArtigos = lattesController.calculaArtigosQualis(candidato);
                candidato.setPontuacao(pontuacaoArtigos);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escreveArtigo(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente aos artigos completos fora do Qualis Restrito
        else if(Arrays.stream(args).anyMatch("-anr"::equals)){
            for (Candidato candidato : candidatoList){
                candidato.setArtigos(lattesController.setArtigos(candidato));
                int pontuacaoArtigos = lattesController.calculaArtigosForaQualis(candidato);
                candidato.setPontuacao(pontuacaoArtigos);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escreveArtigo(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Saída referente a participação de eventos classificados
        else if(Arrays.stream(args).anyMatch("-pe"::equals)){
            /*for (Candidato candidato : candidatoList){
                candidato.setEventos(lattesController.setEventos(candidato));
                int pontuacaoEventos = lattesController.calculaEventos(candidato);
                candidato.setPontuacao(pontuacaoEventos);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escreveEvento(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        //Saída referente a existência de vínculo com a UNIRIO
        else if(Arrays.stream(args).anyMatch("-vi"::equals)){

            for (Candidato candidato : candidatoList){
                candidato.setVinculos(lattesController.setVinculos(candidato));
                int pontuacaoVinculos = lattesController.calculaVinculos(candidato);
                candidato.setPontuacao(pontuacaoVinculos);
            }

            Collections.sort(candidatoList, new ComparadorPontos().reversed());

            FileWritter fileWritter = new FileWritter();
            try {
                fileWritter.escreveVinculo(candidatoList, verboso, caminhos.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Caso nenhum comando tenha sido indicado
        else{
            throw new ComandoNaoEncontradoException("Nenhum parâmetro foi informado!");
        }
    }
}
