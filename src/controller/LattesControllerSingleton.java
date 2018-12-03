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

    }

    /**
     * Função de set dos vínculos de cada candidato
     * @param candidato : candidato em que se procura os vínculos
     * @return : lista de vínculos do candidato
     */
    public List<Vinculo> setVinculos(Candidato candidato){
        XmlReader reader = new XmlReader();

        List<Vinculo> listaVinculo = new ArrayList<>();
        if (reader.readMestrado(candidato.getLattes()) != null){
            listaVinculo.add(reader.readMestrado(candidato.getLattes()));
        }

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
     * @param candidato : candidato em que se procura os artigos publicados
     * @return : lista de artigos do candidato
     */
    public List<Artigo> setArtigos(Candidato candidato) {
        XmlReader reader = new XmlReader();
        CsvReader csvReader = new CsvReader();

        List<Artigo> listaArtigos= new ArrayList<>();

        if (reader.readTrabalhosEventos(candidato.getLattes()) != null){
            listaArtigos.addAll(reader.readTrabalhosEventos(candidato.getLattes()));
        }

        if (reader.readArtigoPublicado(candidato.getLattes()) != null) {
            listaArtigos.addAll(reader.readArtigoPublicado(candidato.getLattes()));
        }

        for (Artigo artigo : listaArtigos) {
            try {
                artigo.setQualis(csvReader.setQualis(artigo.getNome()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return listaArtigos;
    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos artigos completos no Qualis Restrito
     * @param candidato : candidato a ser avaliado
     * @return : pontuação referente aos artigos do candidato
     */
    public int calculaArtigosQualis(Candidato candidato) {

        final int ultimosAnosConsiderados = 10;
        final int valorArtigoQualis = 3;

        int qtdArtigosQualis = 0;

        List<Artigo> listaArtigo = candidato.getArtigos();
        List<Artigo> listaArtigoUltimosAnos = new ArrayList<>();

        for (Artigo artigo : listaArtigo){
            if(parseInt(artigo.getAno()) > Year.now().getValue() - ultimosAnosConsiderados){
                qtdArtigosQualis += 1;
                if(artigo.getQualis() != null){
                    if (artigo.getQualis().equals("A1") || artigo.getQualis().equals("A2") || artigo.getQualis().equals("B1")) {
                        listaArtigoUltimosAnos.add(artigo);
                    }
                }
            }
        }

        candidato.setArtigos(listaArtigoUltimosAnos);

        return qtdArtigosQualis * valorArtigoQualis;
    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos artigos completos no Qualis Restrito
     * @param candidato : candidato a ser avaliado
     * @return : pontuação referente aos artigos do candidato
     */
    public int calculaArtigosForaQualis(Candidato candidato){
        final int ultimosAnosConsiderados = 10;
        final int valorArtigoForaQualis = 1;
        int qtdArtigosForaQualis = 0;

        List<Artigo> listaArtigo = candidato.getArtigos();
        List<Artigo> listaArtigoUltimosAnos = new ArrayList<>();

        for (Artigo artigo : listaArtigo){
            if(parseInt(artigo.getAno()) > Year.now().getValue() - ultimosAnosConsiderados){
                qtdArtigosForaQualis += 1;
                if(artigo.getQualis() != null){
                    if (artigo.getQualis().equals("B2") || artigo.getQualis().equals("B3") || artigo.getQualis().equals("B4") || artigo.getQualis().equals("B5")){
                        listaArtigoUltimosAnos.add(artigo);
                    }
                }
            }
        }

        candidato.setArtigos(listaArtigoUltimosAnos);

        return qtdArtigosForaQualis * valorArtigoForaQualis;
    }
}
