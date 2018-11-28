package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWritterController {

    public void escrevePremio(String nome, String entrada, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s: %s prêmios, totalizando %s pontos.", nome, entrada, entrada);
        printWriter.close();
    }

    public void escrevePremioVerboso(String ano, String nome, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("\n%s: %s.", ano, nome);
        printWriter.close();
    }
}
