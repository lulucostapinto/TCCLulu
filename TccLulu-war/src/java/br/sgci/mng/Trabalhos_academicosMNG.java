/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Trabalhos_academicos;
import br.sgci.dao.Trabalhos_academicosDAORemote;
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
@Named(value = "trabalhos_academicosMNG")

@RequestScoped
public class Trabalhos_academicosMNG {

    @EJB
    Trabalhos_academicosDAORemote trabalhos_academicosDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome, autor, descricao;
    private List<Trabalhos_academicos> lista;

    public void save(ActionEvent actionEvent) {
        Trabalhos_academicos ta = new Trabalhos_academicos();
        ta.setNome(nome);
        ta.setAutor(autor);
        ta.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        trabalhos_academicosDAO.gravar(ta);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();

            trabalhos_academicos.setId(id);
            trabalhos_academicos.setArquivo(arquivo.getContents());

            trabalhos_academicosDAO.gravarArquivo(trabalhos_academicos);

            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //byte[] contents = arquivo.getContents();
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();
        trabalhos_academicos.setId(index);
        trabalhos_academicosDAO.deletar(trabalhos_academicos);
        this.clear();
    }

    public void clear() {
        Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();
        trabalhos_academicos.setId(index);
        trabalhos_academicos = trabalhos_academicosDAO.retrieve(trabalhos_academicos);
        this.id = trabalhos_academicos.getId();
        this.nome = trabalhos_academicos.getNome();
        this.autor = trabalhos_academicos.getAutor();
        this.descricao = trabalhos_academicos.getDescricao();

        return "alterar_trabalhosAcademicos";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoTrabalhosAcademicos";
    }

    public String update() {
        Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();
        trabalhos_academicos.setId(id);
        trabalhos_academicos.setNome(nome);
        trabalhos_academicos.setAutor(autor);
        trabalhos_academicos.setDescricao(descricao);

        trabalhos_academicosDAO.alterar(trabalhos_academicos);

        return "ok";
    }

    public Trabalhos_academicosDAORemote gettrabalhos_academicosDAO() {
        return trabalhos_academicosDAO;
    }

    public void settrabalhos_academicosDAO(Trabalhos_academicosDAORemote trabalhos_academicosDAO) {
        this.trabalhos_academicosDAO = trabalhos_academicosDAO;
    }

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
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

    public List<Trabalhos_academicos> getLista() {
        return trabalhos_academicosDAO.listar();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
