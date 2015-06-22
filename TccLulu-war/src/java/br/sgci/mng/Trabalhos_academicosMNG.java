/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Trabalhos_academicos;
import br.sgci.bean.Video;
import br.sgci.dao.Trabalhos_academicosDAORemote;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
    private StreamedContent file;
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

    public void upload() {
        if (arquivo != null) {
            Trabalhos_academicos ta = new Trabalhos_academicos();
            ta.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                ta.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(VideoMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            trabalhos_academicosDAO.gravarArquivo(ta);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void download() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream);
    }

    public StreamedContent getFile() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codDown".toString()));
        Trabalhos_academicos trabalhos_academicos = new Trabalhos_academicos();
        trabalhos_academicos.setId(index);
        trabalhos_academicos = trabalhos_academicosDAO.retrieve(trabalhos_academicos);
        if (trabalhos_academicos != null) {
            byte[] arquivo1 = trabalhos_academicos.getArquivo();
            ByteArrayInputStream bais = new ByteArrayInputStream(arquivo1);
            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent(bais,"text/plain","teste.txt");
            return defaultStreamedContent;
        }
        return null;
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
        Trabalhos_academicos ta = new Trabalhos_academicos();
        ta.setId(id);
        ta.setNome(nome);
        ta.setAutor(autor);
        ta.setDescricao(descricao);

        trabalhos_academicosDAO.alterar(ta);

        return "ok";
    }
}
