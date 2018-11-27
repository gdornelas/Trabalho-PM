package controller;

import model.Candidato;
import model.Premio;

import java.time.Year;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LattesController {

    /**
     * @param xml
     * @return
     */
    public static Candidato buildLattes(String xml){

        XmlReader read = new XmlReader();

        Candidato candidato = new Candidato();
        List<Premio> listaPremios = read.readPremio(xml);
        candidato.setPremios(listaPremios);
        read.readMestrado(xml);

        return candidato;
    }

    public static void calculaPremios(List<Candidato> candidatoList){

        for (Candidato candidato : candidatoList) {
            int qtdPremios = 0;

            List<Premio> listaPremio = candidato.getPremios();

            for (Premio premios : listaPremio) {
                if (parseInt(premios.getAno()) > Year.now().getValue() - 11) {
                    qtdPremios += 1;
                }
            }

            System.out.println(candidato.getNome() + ": " + qtdPremios + " prêmios, totalizando " + qtdPremios + " pontos.");
            if(true == true){
                for (Premio premios : listaPremio){
                    System.out.println(premios.getAno() + ", " + premios.getNome() + ".");
                }
            }
        }
    }

}
