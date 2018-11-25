package view;

import controller.Reader;
import model.Lattes;
import model.Premio;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Reader read = new Reader();
        List<Lattes> lattesList = new ArrayList<>();

        Lattes lattes = new Lattes();

        List<Premio> readPremio = read.readPremio("gleison.xml");

        lattes.setPremios(readPremio);

        lattesList.add(lattes);

        for (Lattes lattes1 : lattesList){
            System.out.println(lattes1.getPremios());
            //System.out.println(premio);
        }

        System.out.println("Hello World!");
    }
}
