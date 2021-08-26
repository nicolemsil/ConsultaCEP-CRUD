package model;

import java.io.Reader;

public class Livraria {

    private int certificado;
    private String nome;
    private String horario;
    private String cep;

    public Livraria() {
    }

    public Livraria(int certificado, String nome, String horario, String cep) {
        this.certificado = certificado;
        this.nome = nome;
        this.horario = horario;
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCertificado() {
        return certificado;
    }

    public void setCertificado(int certificado) {
        this.certificado = certificado;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "Informações dessa livraria: \n" +
                "certificado: " + certificado + "\n"+
                "nome: " + nome + "\n" +
                "horario: " + horario + "\n" +
                "cep: " + cep;
    }
}
