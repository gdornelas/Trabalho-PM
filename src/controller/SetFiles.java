package controller;

import java.io.File;
import java.io.IOException;

public class SetFiles {

    public File setArquivoSaida(String endereco){
        File saida = new File(endereco);

        if (!saida.exists()) {
            try {
                saida.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return saida;
    }
}
