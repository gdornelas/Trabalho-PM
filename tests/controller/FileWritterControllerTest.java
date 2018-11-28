package controller;

import org.junit.Test;

import java.io.IOException;

public class FileWritterControllerTest {

    @Test
    public void escreveArquivo() throws IOException {

        String nome = "JasponNome";
        String entrada = "Jaspon2";

        FileWritterController test = new FileWritterController();

        test.escrevePremio(nome, entrada);
    }
}