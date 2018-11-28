package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileWritterControllerTest {

    @Test
    public void escreveArquivo() throws IOException {

        String nome = "JasponNome";
        String entrada = "Jaspon2";

        FileWritterController test = new FileWritterController();

        File teste;
        test.escrevePremio(nome, entrada, teste = new File("teste.txt"));
    }
}