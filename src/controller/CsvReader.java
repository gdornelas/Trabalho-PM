package controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controlador de leitura dos aquivos CSV
 */
public class CsvReader {

    /**
     * Função que define o qualis do artigo
     * @param nome : nome do evento ou periódico que o arquivo foi publicado
     * @return : qualis do artigo publicado
     * @throws IOException
     */
    public String setQualis(String nome) throws IOException {

        Scanner scanner = new Scanner(new File("qualis_periodicos.csv"));
        Scanner dataScanner;
        int index = 0;
        String qualis = null;

        while(scanner.hasNextLine()){
            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter(",");

            while (dataScanner.hasNext()){
                String data = dataScanner.next();
                if (index == 1){
                    if(data == nome){
                        data = dataScanner.next();
                        data = dataScanner.next();
                        qualis = data;
                    }
                }
                index++;
            }
            index = 0;
        }

        scanner.close();

        return qualis;
    }

}
