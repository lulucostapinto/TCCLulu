/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Pessoa;
import br.sgci.bean.Setor;
import br.sgci.dao.PessoaDAORemote;
import br.sgci.dao.SetorDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "pessoaMNG")
@RequestScoped
public class PessoaMNG {
    
    
    @EJB
    PessoaDAORemote pessoaDAO;
    @EJB
    SetorDAORemote setorDAO;  
    private int id;
    private String nome, cargo;
    private Setor setor = new Setor();
    private List<Setor> setores;


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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }  

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public SetorDAORemote getSetorDAO() {
        return setorDAO;
    }

    public void setSetorDAO(SetorDAORemote setorDAO) {
        this.setorDAO = setorDAO;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }
    
        
    
    public PessoaDAORemote getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAORemote salaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public Pessoa getPessoa(int id) {
        Pessoa pes = new Pessoa();
        pes.setId(id);

        return pessoaDAO.retrieve(pes);
    }

    public List<Pessoa> getLista() {
        return pessoaDAO.listar();
    }

    public void save(ActionEvent actionEvent) {
        Pessoa p = new Pessoa();
        p.setNome(this.getNome());
        p.setCargo(this.getCargo());

        setor = setorDAO.selecionar(setor.getId());
        p.setSetor(setor);
        pessoaDAO.gravar(p);  

        this.setNome(null);
    }

}

    

