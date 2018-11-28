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

public class LattesController {

    /**
     * @param xml
     * @return
     */
    public static Candidato constroiCandidato(String xml, String reprovacoes){

        XmlReader read = new XmlReader();

        Candidato candidato = new Candidato();
        candidato.setNome(read.readNome(xml));
        candidato.setLattes(xml);
        candidato.setReprovacoes(reprovacoes);

        return candidato;
    }

    public static void setPremios(List<Candidato> candidatoList){

        XmlReader read = new XmlReader();

        for (Candidato candidato : candidatoList){
            List<Premio> listaPremios = read.readPremio(candidato.getLattes());
            candidato.setPremios(listaPremios);
        }
    }

    public static void calculaPremios(List<Candidato> candidatoList, boolean verboso, File saida) throws IOException {

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

    public void setVinculo(List<Candidato> candidatoList){
        XmlReader reader = new XmlReader();

        for (Candidato candidato : candidatoList){
            List<Vinculo> listaVinculo = new ArrayList<>();
            listaVinculo.add(reader.readMestrado(candidato.getLattes()));

        }

    }

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

    public void setArtigos(List<Candidato> candidatoList) {
        XmlReader reader = new XmlReader();

        for(Candidato candidato : candidatoList){
            List<Artigo> listaArtigos= reader.readArtigo(candidato.getLattes());
            candidato.setArtigos(listaArtigos);
        }
    }
}
