package controller;

import model.Premio;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XmlReaderTest {

    /**
     * Testa se o primeiro prêmio é obtido
     */
    @Test
    public void readPremio() {

        XmlReader testeXML = new XmlReader();
        List<Premio> testeList = new ArrayList<>();

        Premio premio1 = new Premio();
        premio1.setNome("Prêmio Dorgival Brandão de Qualidade e Produtividade em Software (autor de capitulos do livro premiado)");
        premio1.setAno("2001");

        testeList.add(premio1);

        assertEquals(testeList.get(0).toString(), testeXML.readPremio("gleison.xml").get(0).toString());
    }

    /**
     * Testa se o nome do candidato é obtido
     */
    @Test
    public void readNome() {

        XmlReader teste = new XmlReader();

        assertEquals("Gleison dos Santos Souza", teste.readNome("gleison.xml"));
        assertEquals("Monica Anastassiu", teste.readNome("monica.xml"));
        assertEquals("Reisla d'Almeida Rodrigues", teste.readNome("reisla.xml"));
    }
}