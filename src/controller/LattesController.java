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
public class LattesController {

    /**
     * Função de construção de um novo candidato para ranqueamento
     * @param xml: endereço do lattes do candidato
     * @param reprovacoes: quantidade de reprovações do candidato
     * @return
     */
    public Candidato constroiCandidato(String xml, String reprovacoes){

        XmlReader read = new XmlReader();

        Candidato candidato = new Candidato();
        candidato.setNome(read.readNome(xml));
        candidato.setLattes(xml);
        candidato.setReprovacoes(reprovacoes);

        return candidato;
    }

    /**
     * Função de set dos prêmios de cada candidato
     * @param candidatoList: lista de candidatos a serem avaliados
     */
    public void setPremios(List<Candidato> candidatoList){

        XmlReader read = new XmlReader();

        for (Candidato candidato : candidatoList){
            List<Premio> listaPremios = read.readPremio(candidato.getLattes());
            candidato.setPremios(listaPremios);
        }
    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos prêmios
     * @param candidatoList: lista de candidatos a serem avaliados
     * @param verboso: boolean de indicação se o modo verboso está ativo
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void calculaPremios(List<Candidato> candidatoList, boolean verboso, File saida) throws IOException {

        for (Candidato candidato : candidatoList) {
            int qtdPremios = 0;

            List<Premio> listaPremio = candidato.getPremios();

            for (Premio premios : listaPremio) {
                if (parseInt(premios.getAno()) > Year.now().getValue() - 11) {
                    qtdPremios += 1;
                }
            }

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
        }
    }

    /**
     * Função de set dos vínculos de cada candidato
     * @param candidatoList: lista de candidatos a serem avaliados
     */
    public void setVinculo(List<Candidato> candidatoList){
        XmlReader reader = new XmlReader();

        for (Candidato candidato : candidatoList){
            List<Vinculo> listaVinculo = new ArrayList<>();
            listaVinculo.add(reader.readMestrado(candidato.getLattes()));

        }

    }

    /**
     * Função de cálculo da pontuação dos candidatos em relação aos vínculos
     * @param candidatoList: lista de candidatos a serem avaliados
     * @param verboso: boolean de indicação se o modo verboso está ativo
     * @param saida: indicação do arquivo de saída
     */
    public void calculaVinculo(List<Candidato> candidatoList, boolean verboso, File saida){
        for(Candidato candidato : candidatoList){
            int qtdVinculos = 0;

            List<Vinculo> listaVinculo = candidato.getVinculos();

            for(Vinculo vinculo : listaVinculo){
                if(parseInt(vinculo.getAno()) > Year.now().getValue() - 11){
                    qtdVinculos += 1;
                    if(qtdVinculos == 2){
                        break;
                    }
                }else{
                    listaVinculo.remove(vinculo);
                }
            }

            FileWritterController file = new FileWritterController();
            try {
                file.escreveVinculo(candidato.getNome(), Integer.toString(qtdVinculos), saida);
                if(verboso == true){
                    for(Vinculo vinculo : listaVinculo){
                        file.escreveVinculoVerboso(vinculo.getAno(), vinculo.getTipo(), saida);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
     * @param candidatoList: lista de candidatos a serem avaliados
     * @param verboso: boolean de indicação se o modo verboso está ativo
     * @param saida: indicação do arquivo de saída
     */
    public void calculaArtigosQualis(List<Candidato> candidatoList, boolean verboso, File saida) {
        for(Candidato candidato : candidatoList){
            int qtdArtigoQualis = 0;

            List<Artigo> listaArtigo = candidato.getArtigos();

            for(Artigo artigo : listaArtigo){

            }

            FileWritterController file = new FileWritterController();
            try {
                file.escreveVinculo(candidato.getNome(), Integer.toString(qtdArtigoQualis), saida);
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
