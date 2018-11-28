package controller;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FileWritterControllerTest {

    @Test
    public void testeEscreveArquivo() throws IOException {

        String entrada1 = "Entrada 1";
        String entrada2 = "Entrada 2";
        File arquivo = new File("teste.txt");

        FileWritterController test = new FileWritterController();
        test.escrevePremio(entrada1, entrada2, arquivo);

        Scanner scanner = new Scanner(arquivo);

        assertEquals("Entrada 1: Entrada 2 prêmios, totalizando Entrada 2 pontos.", scanner.nextLine());
    }
}