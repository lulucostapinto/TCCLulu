/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Solicitacao;
import br.sgci.dao.SolicitacaoDAORemote;
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
@Named(value = "solicitacaoMNG")
@RequestScoped
public class SolicitacaoMNG {

    @EJB
    private SolicitacaoDAORemote solicitacaoDAO;
    private int id;
    private String curso, justificativa;
    private List<Solicitacao> lista;

    public void save(ActionEvent actionEvent) {
        Solicitacao s = new Solicitacao();
        s.setCurso(this.getCurso());
        s.setJustificativa(this.getJustificativa());
        solicitacaoDAO.gravar(s);

        this.setCurso(null);
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(index);
        solicitacaoDAO.deletar(solicitacao);
        this.clear();
    }

    public void clear() {
        Solicitacao solicitacao = new Solicitacao();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(index);
        solicitacao = solicitacaoDAO.retrieve(solicitacao);
        this.id = solicitacao.getId();
        this.curso = solicitacao.getCurso();
        this.justificativa = solicitacao.getJustificativa();

        return "alterar_solicitacao";

    }

    public String update() {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(id);
        solicitacao.setCurso(curso);
        solicitacao.setJustificativa(justificativa);
        solicitacaoDAO.alterar(solicitacao);

        return "ok";
    }

    public SolicitacaoDAORemote getSolicitacaoDAO() {
        return solicitacaoDAO;
    }

    public void setSolicitacaoDAO(SolicitacaoDAORemote solicitacaoDAO) {
        this.solicitacaoDAO = solicitacaoDAO;
    }
    
    public Solicitacao getSolicitacao(int id) {
        Solicitacao sol = new Solicitacao();
        sol.setId(id);
        return solicitacaoDAO.retrieve(sol);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public List<Solicitacao> getLista() {
        return solicitacaoDAO.listar();
    }
    
    public void setLista(List<Solicitacao> lista) {
        this.lista = lista;
    }
}
