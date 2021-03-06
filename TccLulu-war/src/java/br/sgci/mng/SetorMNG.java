/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Setor;
import br.sgci.dao.SetorDAORemote;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "setorMNG")
@RequestScoped
public class SetorMNG {

    @EJB
    private SetorDAORemote setorDAO;
    private int id;
    private String nome;
    private List<Setor> lista;
    
    public void save(ActionEvent actionEvent) {
        Setor s = new Setor();
        s.setNome(this.getNome());

        setorDAO.gravar(s);

        this.setNome(null);
    }   
    
    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Setor setor = new Setor();
        setor.setId(index);
        setorDAO.deletar(setor);
        this.clear();
    }
    
    
    public void clear() {
        Setor setor = new Setor();
    }
    
    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Setor setor = new Setor();
        setor.setId(index);
        setor = setorDAO.retrieve(setor);
        this.id = setor.getId();        
        this.nome = setor.getNome();
      
        return "alterar_setor";

    }

    public String update() {
        Setor setor = new Setor();
        setor.setId(id);
        setor.setNome(nome);
        
        setorDAO.alterar(setor);

        return "ok";
    }
    

    public SetorDAORemote getSetorDAO() {
        return setorDAO;
    }

    public void setSetorDAO(SetorDAORemote setorDAO) {
        this.setorDAO = setorDAO;
    }

    public Setor getSetor(int id) {
        Setor set = new Setor();
        set.setId(id);
        return setorDAO.retrieve(set);
    }

    public List<Setor> getLista() {
        return setorDAO.listar();
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

    
}
