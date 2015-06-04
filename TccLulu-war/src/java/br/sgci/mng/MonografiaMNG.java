/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Monografia;
import br.sgci.dao.MonografiaDAORemote;
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
@Named(value = "monografiaMNG")
@RequestScoped
public class MonografiaMNG {

    @EJB
    MonografiaDAORemote monografiaDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome, autor, descricao;
    private List<Monografia> lista;

    public void save(ActionEvent actionEvent) {
        Monografia m = new Monografia();
        m.setNome(nome);
        m.setAutor(autor);
        m.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        monografiaDAO.gravar(m);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Monografia monografia = new Monografia();

            monografia.setId(id);
            monografia.setArquivo(arquivo.getContents());

            monografiaDAO.gravarArquivo(monografia);

            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //byte[] contents = arquivo.getContents();
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Monografia monografia = new Monografia();
        monografia.setId(index);
        monografiaDAO.deletar(monografia);
        this.clear();
    }

    public void clear() {
        Monografia monografia = new Monografia();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Monografia monografia = new Monografia();
        monografia.setId(index);
        monografia = monografiaDAO.retrieve(monografia);
        this.id = monografia.getId();
        this.nome = monografia.getNome();
        this.autor = monografia.getAutor();
        this.descricao = monografia.getDescricao();

        return "alterar_monografia";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoMonografia";
    }

    public String update() {
        Monografia monografia = new Monografia();
        monografia.setId(id);
        monografia.setNome(nome);
        monografia.setAutor(autor);
        monografia.setDescricao(descricao);

        monografiaDAO.alterar(monografia);

        return "ok";
    }

    public MonografiaDAORemote getMonografiaDAO() {
        return monografiaDAO;
    }

    public void setMonografiaDAO(MonografiaDAORemote monografiaDAO) {
        this.monografiaDAO = monografiaDAO;
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

    public List<Monografia> getLista() {
        return monografiaDAO.listar();
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
