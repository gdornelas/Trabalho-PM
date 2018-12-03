package view;

import model.Candidato;
import model.Premio;
import model.Vinculo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Controlador com funções de escrita nos arquivos texto de saída
 */
public class FileWritterSingleton {

    /**
     * Função de escrita de prêmios no arquivo de saída
     * @param candidatoList : lista de candidatos com prêmios que serão impressos
     * @param verboso : indicação se o modo verboso está ativo ou não
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escrevePremio(List<Candidato> candidatoList, boolean verboso, File saida) throws IOException {

        FileWriter fileWriter;

        if (saida.length() == 0){
            fileWriter = new FileWriter(saida);
        }else{
            fileWriter = new FileWriter(saida, true);
        }

        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Candidato candidato : candidatoList){
            printWriter.printf("%s: %s prêmios, totalizando %s pontos.", candidato.getNome(), candidato.getPremios().size(), candidato.getPontuacao());
            if (verboso == true){
                for (Premio premio : candidato.getPremios()){
                    printWriter.printf("\n%s: %s.", premio.getAno(), premio.getNome());
                }
            }
        }

        printWriter.close();
    }

    /**
     * Função de escrita de vínculos no arquivo de saída
     * @param candidatoList : lista de candidatos com vínculos que serão impressos
     * @param verboso : indicação se o modo verboso está ativo ou não
     * @param saida: indicação do arquivo de saída
     * @throws IOException
     */
    public void escreveVinculo(List<Candidato> candidatoList, boolean verboso, File saida) throws IOException {
        FileWriter fileWriter;

        if (saida.length() == 0){
            fileWriter = new FileWriter(saida);
        }else{
            fileWriter = new FileWriter(saida, true);
        }

        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Candidato candidato : candidatoList) {
            printWriter.printf("%s: %s vínculos, totalizando %s pontos.", candidato.getNome(), candidato.getVinculos().size(), candidato.getPontuacao());
            if (verboso == true){
                for (Vinculo vinculo : candidato.getVinculos()){
                    printWriter.printf("\n%s: %s.", vinculo.getAno(), vinculo.getNome());
                }
            }
        }
        printWriter.close();
    }
}
