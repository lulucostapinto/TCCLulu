/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso;
import br.sgci.dao.CursoDAORemote;
import br.sgci.bean.Sala;
import br.sgci.dao.SalaDAORemote;
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
    @EJB
    SalaDAORemote salaDAO;
    private int id;
    private String nome, pub_alvo; 
    private int qtdVagas, duracao;
    private List<Curso> lista;
    private Date data_inicio;
    private Date data_fim;   
    private Sala sala = new Sala();
    private List<Sala> salas;
    

    public void save(ActionEvent actionEvent) {
        Curso c = new Curso();
        c.setNome(this.getNome());      
        c.setDuracao (this.getDuracao());
        c.setPub_alvo (this.getPub_alvo());
        c.setQtdVagas(this.getQtdVagas());
        c.setData_inicio(this.getData_inicio());
        c.setData_fim(this.getData_fim());

        
        sala = salaDAO.selecionar(sala.getId());
        c.setSala(sala);
        cursoDAO.gravar(c);       
     
    }

    public CursoDAORemote getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAORemote cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public SalaDAORemote getSalaDAO() {
        return salaDAO;
    }

    public void setSalaDAO(SalaDAORemote salaDAO) {
        this.salaDAO = salaDAO;
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

    public String getPub_alvo() {
        return pub_alvo;
    }

    public void setPub_alvo(String pub_alvo) {
        this.pub_alvo = pub_alvo;
    }
 

    public int getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<Curso> getLista() {
        return cursoDAO.listar();
    }

    public void setLista(List<Curso> lista) {
        this.lista = lista;
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

    public List<Sala> getSalas() {
        return salaDAO.listar();
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

   
  
   

}
