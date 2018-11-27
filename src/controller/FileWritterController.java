package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWritterController {

    public void escreveArquivo(String entrada) throws IOException {
        FileWriter fileWriter = new FileWriter("saida.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(entrada + ".");
        printWriter.close();
    }
}
