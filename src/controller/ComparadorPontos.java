package controller;

import model.Candidato;

import java.util.Comparator;

public class ComparadorPontos implements Comparator<Candidato> {
    @Override
    public int compare(Candidato o1, Candidato o2) {

        final int pontuacaoCandidato1 = o1.getPontuacao();
        final int pontuacaoCandidato2 = o2.getPontuacao();
        return pontuacaoCandidato1 < pontuacaoCandidato2 ? -1 : pontuacaoCandidato1 > pontuacaoCandidato2 ? 1 : 0;
    }
}
