package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controlador com funções de escrita nos arquivos texto de saída
 */
public class FileWritterController {

    /**
     * Função de escrita de prêmios no arquivo de saída
     * @param nome: nome do candidato
     * @param entrada: quantidade de prêmios do candidato
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escrevePremio(String nome, String entrada, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s: %s prêmios, totalizando %s pontos.", nome, entrada, entrada);
        printWriter.close();
    }

    /**
     * Função de escrita do modo verboso de prêmios no arquivo de saída
     * @param ano: ano do prêmio
     * @param nome: nome do prêmio
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escrevePremioVerboso(String ano, String nome, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("\n%s: %s.", ano, nome);
        printWriter.close();
    }

    /**
     * Função de escrita de vínculos no arquivo de saída
     * @param nome: nome do candidato
     * @param entrada: quantidade de vínculos
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escreveVinculo(String nome, String entrada, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s: %s vínculos, totalizando %s pontos.", nome, entrada, entrada);
        printWriter.close();
    }

    /**
     * Função de escrita do modo verboso para vínculos no arquivo de saída
     * @param ano: ano do vínculo
     * @param nome: nome do vínculo
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escreveVinculoVerboso(String ano, String nome, File saida) throws IOException {
        FileWriter fileWriter = new FileWriter(saida, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("\n%s: %s.", ano, nome);
        printWriter.close();
    }
}
