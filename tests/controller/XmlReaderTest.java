package controller;

import model.Premio;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XmlReaderTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Teste de verificação se o arquivo existe
     */
    @Test
    public void testReadPremioSemArquivo(){
        XmlReader testeXML = new XmlReader();

        exception.expect(FileNotFoundException.class);
        testeXML.readPremio("naoexiste.xml");
    }

    /**
     * Testa a função de obtenção de prêmios
     */
    @Test
    public void readPremio() {

        XmlReader testeXML = new XmlReader();
        List<Premio> testeList = new ArrayList<>();

        Premio premio1 = new Premio();
        premio1.setNome("Prêmio Dorgival Brandão de Qualidade e Produtividade em Software (autor de capitulos do livro premiado)");
        premio1.setAno("2001");

        testeList.add(premio1);

        //Testa se o primeiro prêmio é obtido corretamente
        assertEquals(testeList.get(0).toString(), testeXML.readPremio("gleison.xml").get(0).toString());

        //Testa se o candidato não possui nenhum prêmio
        assertTrue(testeXML.readPremio("reisla.xml").isEmpty());

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