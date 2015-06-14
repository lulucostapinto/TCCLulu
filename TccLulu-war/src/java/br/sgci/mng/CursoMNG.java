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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "cursoMNG")
@RequestScoped
public class CursoMNG implements Serializable {

    @EJB
    CursoDAORemote cursoDAO;
    @EJB
    SalaDAORemote salaDAO;
    private int id;
    private String nome, pub_alvo;
    private int qtd_vagas, duracao;
    private List<Curso> lista;
    private Date data_inicio, data_fim;   
    private Sala sala = new Sala();
    private List<Sala> salas;
    private Curso curso;

    public void save(ActionEvent actionEvent) {
        Curso c = new Curso();
        c.setNome(this.getNome());
        c.setDuracao(this.getDuracao());
        c.setPub_alvo(this.getPub_alvo());
        c.setQtd_vagas(this.getQtd_vagas());
        c.setData_inicio(this.getData_inicio());
        c.setData_fim(this.getData_fim());

        sala = salaDAO.selecionar(sala.getId());
        c.setSala(sala);
        cursoDAO.gravar(c);

    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Curso curso = new Curso();
        curso.setId(index);
        cursoDAO.deletar(curso);
        this.clear();
    }

    public void clear() {
        Curso curso = new Curso();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Curso curso = new Curso();
        curso.setId(index);
        curso = cursoDAO.retrieve(curso);
        this.id = curso.getId();
        this.data_inicio = curso.getData_inicio();
        this.data_fim = curso.getData_fim();
        this.nome = curso.getNome();
        this.sala = curso.getSala();
        this.duracao = curso.getDuracao();
        this.qtd_vagas = curso.getQtd_vagas();
        this.pub_alvo = curso.getPub_alvo();
        
        return "alterar_curso";

    }

    public String update() {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNome(nome);
        curso.setPub_alvo(pub_alvo);
        curso.setQtd_vagas(qtd_vagas);
        curso.setDuracao(duracao);
        curso.setData_inicio(data_inicio);
        curso.setData_fim(data_fim);
        curso.setSala(sala);
        cursoDAO.alterar(curso);

        return "ok";
    }

   public Curso getCurso(int id) {
        Curso cur = new Curso();
        cur.setId(id);
//        curso.setId(id);
        return cursoDAO.retrieve(cur);
    }
   
    public CursoDAORemote getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAORemote cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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

    public int getQtd_vagas() {
        return qtd_vagas;
    }

    public void setQtd_vagas(int qtd_vagas) {
        this.qtd_vagas = qtd_vagas;
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
