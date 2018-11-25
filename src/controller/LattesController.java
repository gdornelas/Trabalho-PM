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

            List<Premio> premio = lattes.getPremios();

            for (Premio premios : premio) {
                if (parseInt(premios.getAno()) > Year.now().getValue() - 11) {
                    qtdPremios += 1;
                }
            }
            System.out.println(lattes.getNome() + ": " + qtdPremios + " prêmios, totalizando " + qtdPremios + " pontos.");
            /*if(verbose == true){
                for (Premio premio : lattes.getPremios()){
                    System.out.println(premio.getAno() + ", " + premio.getNome() + ".");
                }
            }*/
        }
    }

}
