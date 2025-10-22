package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String nome;
    private final List<Jogo> jogos = new ArrayList<>();

    public Empresa() {
    }

    public void adicionarJogo(Jogo jogo) throws JogoInvalidoException {

        if (jogo == null) {
            throw new JogoInvalidoException("Jogo inválido!");
        } else if (jogo.getCodigo() == null || jogo.getCodigo() == "") {
            throw new JogoInvalidoException("Código inválido!");
        } else if (jogo.getNome() == null || jogo.getNome() == "") {
            throw new JogoInvalidoException("Nome inválido!");
        } else if (jogo.getGenero() == null || jogo.getGenero() == "") {
            throw new JogoInvalidoException("Gênero inválido!");
        } else if (jogo.getPreco() == null || jogo.getPreco() <= 0) {
            throw new JogoInvalidoException("Preço inválido!");
        } else if (jogo.getAvaliacao() == null || jogo.getAvaliacao() < 0 ||
                jogo.getAvaliacao() > 5) {
            throw new JogoInvalidoException("Avaliação inválida!");
        } else if (jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(LocalDate.now())) {
            throw new JogoInvalidoException("Data inválida!");
        }

        jogos.add(jogo);
        System.out.println("Jogo: " + jogo.getNome() + " adicionado com sucesso!");
    }

    public Jogo buscarJogoPorCodigo(String codigo) throws ArgumentoInvalidoException,
            JogoInvalidoException {

        Jogo jogo_encontrado = null;

        for(Jogo j : jogos) {
            if(j.getCodigo().equalsIgnoreCase(codigo)) {
                jogo_encontrado = j;
                break;
            }
            if(codigo == null || codigo.isEmpty()) {
                throw new ArgumentoInvalidoException("Código inválido");
            }
            if (!codigo.equals(j.getCodigo())) {
                throw new JogoNaoEncontradoException("Jogo não encontrado!");
            }
        }
        return jogo_encontrado;
    }

    public void removerJogoPorCodigo(String codigo) throws ArgumentoInvalidoException, JogoInvalidoException{

        for(Jogo j : jogos) {

            if(j.getCodigo().equalsIgnoreCase(codigo)){
                jogos.remove(j);
                break;
            }

            if(codigo == null || codigo.isEmpty() || !j.getCodigo().equals(codigo)) {
                throw new ArgumentoInvalidoException("Código inválido");
            }

            if (!codigo.equals(j.getCodigo())) {
                throw new JogoNaoEncontradoException("Jogo não encontrado!");
            }
        }
    }

    public Jogo buscarJogoComMelhorAvaliacao() {

        Jogo melhor_avaliacao = null;

        for(Jogo j : jogos) {
            if(j.getAvaliacao() > j.getAvaliacao() + 1) {
                melhor_avaliacao = j;
            }
        }

        return melhor_avaliacao;
    }

    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ArgumentoInvalidoException{

    List<Jogo> jogos_periodo = null;

        for(Jogo j : jogos) {

            if(dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)) {
                throw new ArgumentoInvalidoException("Datas inválidas!");
            }

            if(j.getDataLancamento().isBefore(dataFim) && j.getDataLancamento().isAfter(dataInicio)){
                jogos_periodo.add(j);
            }
        }

    return jogos_periodo;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
