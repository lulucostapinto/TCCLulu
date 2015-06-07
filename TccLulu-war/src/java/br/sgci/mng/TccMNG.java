/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Trabalho;
import br.sgci.bean.Tcc;
import br.sgci.dao.TrabalhoDAORemote;
import br.sgci.dao.TccDAORemote;
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
@Named(value = "tccMNG")
@RequestScoped
public class TccMNG {

    @EJB
    TccDAORemote tccDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome, autor, descricao;
    private List<Trabalho> lista;

    public void save(ActionEvent actionEvent) {
        Tcc tcc = new Tcc();
        tcc.setNome(nome);
        tcc.setAutor(autor);
        tcc.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        tccDAO.gravar(tcc);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Tcc tcc = new Tcc();

            tcc.setId(id);
            tcc.setArquivo(arquivo.getContents());

            tccDAO.gravarArquivo(tcc);

            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //byte[] contents = arquivo.getContents();
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Tcc tcc = new Tcc();
        tcc.setId(index);
        tccDAO.deletar(tcc);
        this.clear();
    }

    public void clear() {
        Tcc tcc = new Tcc();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Tcc tcc = new Tcc();
        tcc.setId(index);
        tcc = tccDAO.retrieve(tcc);
        this.id = tcc.getId();
        this.nome = tcc.getNome();
        this.autor = tcc.getAutor();
        this.descricao = tcc.getDescricao();

        return "alterar_tcc";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoTcc";
    }

    public String update() {
        Tcc tcc = new Tcc();
        tcc.setId(id);
        tcc.setNome(nome);
        tcc.setAutor(autor);
        tcc.setDescricao(descricao);

        tccDAO.alterar(tcc);

        return "ok";
    }

    public TccDAORemote getTccDAO() {
        return tccDAO;
    }

    public void setTccDAO(TccDAORemote tccDAO) {
        this.tccDAO = tccDAO;
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

    public List<Tcc> getLista() {
        return tccDAO.listar();
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
