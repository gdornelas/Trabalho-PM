package controller;

import model.Lattes;
import model.Premio;

import java.time.Year;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LattesController {

    public static Lattes buildLattes(String xml){

        XmlReader read = new XmlReader();

        Lattes lattes = new Lattes();
        List<Premio> listaPremios = read.readPremio(xml);
        lattes.setPremios(listaPremios);

        return lattes;
    }

    public static void calculaPremios(List<Lattes> lattesList){

        for (Lattes lattes : lattesList) {
            int qtdPremios = 0;

            List<Premio> listaPremio = lattes.getPremios();

            for (Premio premios : listaPremio) {
                if (parseInt(premios.getAno()) > Year.now().getValue() - 11) {
                    qtdPremios += 1;
                }
            }

            System.out.println(lattes.getNome() + ": " + qtdPremios + " prêmios, totalizando " + qtdPremios + " pontos.");
            if(true == true){
                for (Premio premios : listaPremio){
                    System.out.println(premios.getAno() + ", " + premios.getNome() + ".");
                }
            }
        }
    }

}
