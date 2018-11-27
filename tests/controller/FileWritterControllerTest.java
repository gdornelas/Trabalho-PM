package controller;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileWritterControllerTest {

    @Test
    public void escreveArquivo() throws IOException {

        String entrada = "Jasgfdpion";

        FileWritterController test = new FileWritterController();

        test.escreveArquivo(entrada);
    }
}