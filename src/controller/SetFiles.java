package controller;

import java.io.File;
import java.io.IOException;

/**
 * Controlador de indicação de arquivos de saída
 */
public class SetFiles {

    /**
     * Função de set do arquivo de saída
     * @param endereco: endereço do arquivo de saída
     * @return: indicação do arquivo de saída
     */
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

    /**
     * Função set do arquivo de log
     * @param endereco: endereço do arquivo de saída
     * @return: indicação do arquivo de saída
     */
    public File setArquivoLog(String endereco){
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
