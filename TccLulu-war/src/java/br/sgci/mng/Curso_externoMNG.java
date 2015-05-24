/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso_externo;
import br.sgci.bean.Pessoa;
import br.sgci.dao.Curso_externoDAORemote;
import br.sgci.dao.PessoaDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lulu
 */
@Named(value = "curso_externoMNG")
@RequestScoped
public class Curso_externoMNG {
    
    
    @EJB
    Curso_externoDAORemote curso_externoDAO;
    @EJB
    PessoaDAORemote pessoaDAO;
    private UploadedFile arquivo;
    private int qtd_horas;
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;
    
    
    public void save(ActionEvent actionEvent) {
        Curso_externo ce = new Curso_externo();
       

        pessoa = pessoaDAO.selecionar(pessoa.getId());
        ce.setPessoa(pessoa);
        curso_externoDAO.gravar(ce);

    }

    public Curso_externoDAORemote getCurso_externoDAO() {
        return curso_externoDAO;
    }

    public void setCurso_externoDAO(Curso_externoDAORemote curso_externoDAO) {
        this.curso_externoDAO = curso_externoDAO;
    }
    
    
 
    public UploadedFile getArquivo() {
        return arquivo;
    }
 
    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }
     
    public void upload() {
        if(arquivo != null) {
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            //arquivo.getContents();
        }
    }

    public int getQtd_horas() {
        return qtd_horas;
    }

    public void setQtd_horas(int qtd_horas) {
        this.qtd_horas = qtd_horas;
    }

    
    public PessoaDAORemote getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAORemote pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
     public List<Pessoa> getPessoas() {
        return pessoaDAO.listar();
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }    
    
    
}
    

