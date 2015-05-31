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
import javax.faces.context.FacesContext;
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
    private List<Pessoa> lista;
    private Setor setor = new Setor();
    private List<Setor> setores;
    
    public void save(ActionEvent actionEvent) {
        Pessoa p = new Pessoa();
        p.setNome(this.getNome());
        p.setCargo(this.getCargo());

        setor = setorDAO.selecionar(setor.getId());
        p.setSetor(setor);
        
        pessoaDAO.gravar(p);

    }
    
    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Pessoa pessoa = new Pessoa();
        pessoa.setId(index);
        pessoaDAO.deletar(pessoa);
        this.clear();
    }

    public void clear() {
        Pessoa pessoa = new Pessoa();
    }
    
    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Pessoa pessoa = new Pessoa();
        pessoa.setId(index);
        pessoa = pessoaDAO.retrieve(pessoa);
        this.id = pessoa.getId();        
        this.nome = pessoa.getNome();
        this.cargo = pessoa.getCargo();
        this.setor = pessoa.getSetor();
        
        return "alterar_pessoa";

    }

    public String update() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setCargo(cargo);        
        pessoa.setSetor(setor);
        pessoaDAO.alterar(pessoa);

        return "ok";
    }

    public PessoaDAORemote getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAORemote pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
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
    
    public Pessoa getPessoa(int id) {
        Pessoa pes = new Pessoa();
        pes.setId(id);

        return pessoaDAO.retrieve(pes);
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }  
    
    public List<Pessoa> getLista() {
        return pessoaDAO.listar();
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }
    
    
    public List<Setor> getSetores() {
        return setorDAO.listar();
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }    

   
}
