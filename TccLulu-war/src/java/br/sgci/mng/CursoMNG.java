/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso;
import br.sgci.dao.CursoDAORemote;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    private String nome;
    private int qtdVagas;
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private List<Curso> lista;

    public String save() {
        Curso c = new Curso();
        c.setNome(this.getNome());
        c.setQtdVagas(this.getQtdVagas());
        c.setData_inicio(this.getData_inicio());
        c.setData_fim(this.getData_fim());

        cursoDAO.gravar(c);

        this.setQtdVagas(0);
        this.setNome(null);
        return null;
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

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

}
