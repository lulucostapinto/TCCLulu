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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int id;
    private UploadedFile arquivo;
    private String nome;
    private int qtd_horas;
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;
    private List<Curso_externo> lista;
    private Date data_inicio, data_fim;

    public void save(ActionEvent actionEvent) {
        Curso_externo ce = new Curso_externo();
        ce.setNome(nome);
        ce.setQtd_horas(qtd_horas);
        ce.setData_inicio(data_inicio);
        ce.setData_fim(data_fim);
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

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
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

    public List<Curso_externo> getLista() {
        return curso_externoDAO.listar();
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

    public void upload() {
        if (arquivo != null) {
            Curso_externo ce = new Curso_externo();
            ce.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                ce.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(VideoMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            curso_externoDAO.gravarArquivo(ce);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Curso_externo ce = new Curso_externo();
        ce.setId(index);
        ce = curso_externoDAO.retrieve(ce);
        this.id = ce.getId();
        this.nome = ce.getNome();

        return "alterar_cursoExterno";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoCursoExterno";
    }

   
}
