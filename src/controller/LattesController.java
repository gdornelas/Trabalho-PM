package controller;

import model.Candidato;
import model.Premio;

import java.io.IOException;
import java.time.Year;
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


        //read.readMestrado(xml);

        return candidato;
    }

    public static void setPremios(List<Candidato> candidatoList){

        XmlReader read = new XmlReader();

        for (Candidato candidato : candidatoList){
            List<Premio> listaPremios = read.readPremio(candidato.getLattes());
            candidato.setPremios(listaPremios);
        }
    }

    public static void calculaPremios(List<Candidato> candidatoList) throws IOException {

        for (Candidato candidato : candidatoList) {
            int qtdPremios = 0;

            List<Premio> listaPremio = candidato.getPremios();

            for (Premio premios : listaPremio) {
                if (parseInt(premios.getAno()) > Year.now().getValue() - 11) {
                    qtdPremios += 1;
                }
            }

            FileWritterController file = new FileWritterController();
            file.escrevePremio(candidato.getNome(), Integer.toString(qtdPremios));
            //TODO remover print
            System.out.println(candidato.getNome() + ": " + qtdPremios + " prêmios, totalizando " + qtdPremios + " pontos.");
            if(true == true){
                for (Premio premios : listaPremio){
                    file.escrevePremioVerboso(premios.getAno(), premios.getNome());
                    System.out.println(premios.getAno() + ": " + premios.getNome() + ".");
                }
            }
        }
    }

}
