package controller;

import model.Artigo;
import model.Candidato;
import model.Premio;
import model.Vinculo;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Controlador de construção do candidato e cálculo de suas informações
 */
public class LattesControllerSingleton {

    //--------------------------------------------------- Singleton ---------------------------------------------------

    //Criação do objeto
    public static LattesControllerSingleton instanciaLattesControllerSingletonSingleton;

    /**
     * Construtor privado pois a classe é um Singleton
     */
    private LattesControllerSingleton(){}

    /**
     * Método de instanciação do objeto
     * @return : instância única do objeto
     */
    public static LattesControllerSingleton getInstanciaLattesControllerSingleton(){
        if(instanciaLattesControllerSingletonSingleton == null){
            instanciaLattesControllerSingletonSingleton = new LattesControllerSingleton();
        }
        return instanciaLattesControllerSingletonSingleton;
    }

    //--------------------------------------------------- Métodos ---------------------------------------------------

    /**
     * Função de construção de um novo candidato para ranqueamento
     * @param xml : endereço do lattes do candidato
     * @param reprovacoes : quantidade de reprovações do candidato
     * @return
     */
    public Candidato constroiCandidato(String xml, String reprovacoes){

        XmlReader read = new XmlReader();

        Candidato candidato = new Candidato();
        candidato.setNome(read.readNome(xml));
        candidato.setLattes(xml);
        candidato.setSemestresSemReprovacao(parseInt(reprovacoes));

        return candidato;
    }

    public int calculaReprovacoes(Candidato candidato){

        final int valorReprovacoes = 1;

        return valorReprovacoes * candidato.getSemestresSemReprovacao();
    }

    /**
     * Função de set dos prêmios de cada candidato
     * @param candidato : candidato em que se procura os prêmios
     * @return : lista de prêmios do candidato
     */
    public List<Premio> setPremios(Candidato candidato){

        XmlReader read = new XmlReader();
        List<Premio> listaPremios = read.readPremio(candidato.getLattes());
        return listaPremios;
    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos prêmios recebidos nos últimos anos
     * @param candidato : candidato a ser avaliado
     * @return : pontuação referente a quantidade de prêmios do candidato
     */
    public int calculaPremios(Candidato candidato){

        final int ultimosAnosConsiderados = 10;
        final int valorPrêmio = 1;

        int qtdPremios = 0;

        List<Premio> listaPremio = candidato.getPremios();
        List<Premio> listaPremioUltimosAnos = new ArrayList<>();

        for (Premio premio : listaPremio) {
            if (parseInt(premio.getAno()) > Year.now().getValue() - ultimosAnosConsiderados) {
                qtdPremios += 1;

                listaPremioUltimosAnos.add(premio);
            }
        }

        candidato.setPremios(listaPremioUltimosAnos);

        return valorPrêmio * qtdPremios;

        /*
        FileWritterController file = new FileWritterController();
        file.escrevePremio(candidato.getNome(), Integer.toString(qtdPremios), saida);
        //TODO remover print
        System.out.println(candidato.getNome() + ": " + qtdPremios + " prêmios, totalizando " + qtdPremios + " pontos.");
        if(verboso == true){
            for (Premio premios : listaPremio){
                file.escrevePremioVerboso(premios.getAno(), premios.getNome(), saida);
                System.out.println(premios.getAno() + ": " + premios.getNome() + ".");
            }
        }
        */
    }

    /**
     * Função de set dos vínculos de cada candidato
     * @param candidato : candidato em que se procura os vínculos
     * @return : lista de vínculos do candidato
     */
    public List<Vinculo> setVinculos(Candidato candidato){
        XmlReader reader = new XmlReader();

        List<Vinculo> listaVinculo = new ArrayList<>();
        listaVinculo.add(reader.readMestrado(candidato.getLattes()));

        return listaVinculo;
    }

    /**
     * Função de cálculo da pontuação do candidatos em relação aos seus vínculos com a UNIRIO nos últimos anos
     * @param candidato : candidato a ser avaliado
     * @return : pontuação referente aos vínculos do candidato
     */
    public int calculaVinculos(Candidato candidato){

        final int ultimosAnosConsiderados = 10;
        final int valorVinculo = 1;

        int qtdVinculos = 0;

        List<Vinculo> listaVinculo = candidato.getVinculos();
        List<Vinculo> listaVinculoUltimosAnos = new ArrayList<>();

        for(Vinculo vinculo : listaVinculo){
            if(parseInt(vinculo.getAno()) > Year.now().getValue() - ultimosAnosConsiderados){
                qtdVinculos += 1;
                listaVinculoUltimosAnos.add(vinculo);
                if(qtdVinculos == 2){
                    break;
                }
            }
        }

        candidato.setVinculos(listaVinculoUltimosAnos);

        return valorVinculo * qtdVinculos;
    }

    /**
     * Função de set dos artigos de cada candidato
     * @param candidatoList: lista de candidatos a serem avaliados
     */
    public void setArtigos(List<Candidato> candidatoList) {
        XmlReader reader = new XmlReader();

        for(Candidato candidato : candidatoList){
            List<Artigo> listaArtigos= reader.readTrabalhosEventos(candidato.getLattes());
            listaArtigos.addAll(reader.readArtigoPublicado(candidato.getLattes()));
            candidato.setArtigos(listaArtigos);
        }
    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos artigos completos no Qualis Restrito
     * @param candidatoList : lista de candidatos a serem avaliados
     * @param verboso : boolean de indicação se o modo verboso está ativo
     * @param saida : indicação do arquivo de saída
     */
    public void calculaArtigosQualis(List<Candidato> candidatoList, boolean verboso, List<File> saida) {
        for(Candidato candidato : candidatoList){
            int qtdArtigoQualis = 0;

            List<Artigo> listaArtigo = candidato.getArtigos();

            for(Artigo artigo : listaArtigo){

            }

            FileWritterController file = new FileWritterController();
            try {
                file.escreveVinculo(candidato.getNome(), Integer.toString(qtdArtigoQualis), saida.get(0));
                if(verboso == true){
                    for(Artigo artigo : listaArtigo){

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
