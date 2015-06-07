/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Trabalho;
import br.sgci.dao.TrabalhoDAORemote;
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
@Named(value = "trabalhoMNG")
@RequestScoped
public class TrabalhoMNG {

    @EJB
    TrabalhoDAORemote trabalhoDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome, autor, descricao;
    private List<Trabalho> lista;

    public void save(ActionEvent actionEvent) {
        Trabalho trabalho = new Trabalho();
        trabalho.setNome(nome);
        trabalho.setAutor(autor);
        trabalho.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        trabalhoDAO.gravar(trabalho);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Trabalho trabalho = new Trabalho();

            trabalho.setId(id);
            trabalho.setArquivo(arquivo.getContents());

            trabalhoDAO.gravarArquivo(trabalho);

            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //byte[] contents = arquivo.getContents();
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Trabalho trabalho = new Trabalho();
        trabalho.setId(index);
        trabalhoDAO.deletar(trabalho);
        this.clear();
    }

    public void clear() {
        Trabalho trabalho = new Trabalho();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Trabalho trabalho = new Trabalho();
        trabalho.setId(index);
        trabalho = trabalhoDAO.retrieve(trabalho);
        this.id = trabalho.getId();
        this.nome = trabalho.getNome();
        this.autor = trabalho.getAutor();
        this.descricao = trabalho.getDescricao();

        return "alterar_trabalho";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoTrabalho";
    }

    public String update() {
        Trabalho trabalho = new Trabalho();
        trabalho.setId(id);
        trabalho.setNome(nome);
        trabalho.setAutor(autor);
        trabalho.setDescricao(descricao);

        trabalhoDAO.alterar(trabalho);

        return "ok";
    }

    public TrabalhoDAORemote getTrabalhoDAO() {
        return trabalhoDAO;
    }

    public void setTrabalhoDAO(TrabalhoDAORemote trabalhoDAO) {
        this.trabalhoDAO = trabalhoDAO;
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

    public List<Trabalho> getLista() {
        return trabalhoDAO.listar();
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
