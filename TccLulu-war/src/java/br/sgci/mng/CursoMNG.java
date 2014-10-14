/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso;
import br.sgci.dao.CursoDAORemote;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "cursoMNG")
@RequestScoped
public class CursoMNG {

    @EJB
    CursoDAORemote cursoDAO;
    private int id;
    private String nome, local, pub_alvo; 
    private int qtdVagas, duracao;
    private List<Curso> lista;
    private Date data_inicio;
    private Date data_fim;

    public void save(ActionEvent actionEvent) {
        Curso c = new Curso();
        c.setNome(this.getNome());
        c.setLocal (this.getLocal());
        c.setDuracao (this.getDuracao());
        c.setPub_alvo (this.getPub_alvo());
        c.setQtdVagas(this.getQtdVagas());
        c.setData_inicio(this.getData_inicio());
        c.setData_fim(this.getData_fim());

        cursoDAO.gravar(c);

        this.setQtdVagas(0);
        this.setNome(null);
    }

    public CursoDAORemote getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAORemote cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPub_alvo() {
        return pub_alvo;
    }

    public void setPub_alvo(String pub_alvo) {
        this.pub_alvo = pub_alvo;
    }
        

    public List<Curso> getLista() {
        return cursoDAO.listar();
    }

    public void setLista(List<Curso> lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    

}
