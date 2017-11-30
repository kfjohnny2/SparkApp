package sonnesoft.com.spark.model;

import java.io.Serializable;

/**
 * Created by johnnylee on 30/11/17.
 */

public class Docente implements Serializable{
    private int idServidor;
    private String siape;
    private String nome;
    private String formacao;
    private String tipoJornadaTrabalho;
    private String vinculo;
    private String categoria;
    private String classeFuncional;
    private int idUnidadeLotacao;
    private String lotacao;
    private String admissao;

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getTipoJornadaTrabalho() {
        return tipoJornadaTrabalho;
    }

    public void setTipoJornadaTrabalho(String tipoJornadaTrabalho) {
        this.tipoJornadaTrabalho = tipoJornadaTrabalho;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClasseFuncional() {
        return classeFuncional;
    }

    public void setClasseFuncional(String classeFuncional) {
        this.classeFuncional = classeFuncional;
    }

    public int getIdUnidadeLotacao() {
        return idUnidadeLotacao;
    }

    public void setIdUnidadeLotacao(int idUnidadeLotacao) {
        this.idUnidadeLotacao = idUnidadeLotacao;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        this.admissao = admissao;
    }
}
