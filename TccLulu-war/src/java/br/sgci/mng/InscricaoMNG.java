/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso;
import br.sgci.bean.Inscricao;
import br.sgci.bean.Pessoa;
import br.sgci.dao.CursoDAORemote;
import br.sgci.dao.InscricaoDAORemote;
import br.sgci.dao.PessoaDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "inscricaoMNG")
@RequestScoped
public class InscricaoMNG {

    @EJB
    InscricaoDAORemote inscricaoDAO;
    @EJB
    CursoDAORemote cursoDAO;
    @EJB
    PessoaDAORemote pessoaDAO;
    private int id;    
    private Pessoa pessoa = new Pessoa();
    private Curso curso = new Curso();
    private List<Pessoa> pessoas;
    private List<Curso> cursos;
    private List<Inscricao> lista;

    public void save(ActionEvent actionEvent) {
        Inscricao i = new Inscricao();
        pessoa = pessoaDAO.selecionar(pessoa.getId());
        i.setPessoa(pessoa);
        curso = cursoDAO.selecionar(curso.getId());
        i.setCurso(curso);
        inscricaoDAO.gravar(i);
    }

    public InscricaoDAORemote getInscricaoDAO() {
        return inscricaoDAO;
    }

    public void setInscricaoDAO(InscricaoDAORemote inscricaoDAO) {
        this.inscricaoDAO = inscricaoDAO;
    }

    public CursoDAORemote getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAORemote cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public PessoaDAORemote getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAORemote pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Inscricao getInscricao(int id) {
        Inscricao ins = new Inscricao();
        ins.setId(id);
        return inscricaoDAO.retrieve(ins);
    }
    
    public List<Curso> getCursos() {
        return cursoDAO.listar();
    }

    public void setSetores(List<Curso> cursos) {
        this.cursos = cursos;
    } 
    
    public List<Pessoa> getPessoas() {
        return pessoaDAO.listar();
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    } 

    public List<Inscricao> getLista() {
        return inscricaoDAO.listar();
    }
    
    

}
