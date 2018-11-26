package view;

import controller.LattesController;
import model.Lattes;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public Lattes candidatos(String xml){

        xml = "gleison.xml";
        return LattesController.buildLattes(xml);
    }

    public void premios(List<Lattes> lattesList){
        LattesController.calculaPremios(lattesList);
    }

    public static void main(String[] args) {

        List<Lattes> lattesList = new ArrayList<>();
        String xml = "";
        Main main = new Main();

        lattesList.add(main.candidatos(xml));
        main.premios(lattesList);

        System.out.println("Hello World!");
    }
}
