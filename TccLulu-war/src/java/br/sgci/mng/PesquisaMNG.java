/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Pesquisa;
import br.sgci.bean.Video;
import br.sgci.dao.PesquisaDAORemote;
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
@Named(value = "pesquisaMNG")
@RequestScoped
public class PesquisaMNG {

    @EJB
    PesquisaDAORemote pesquisaDAO;
    private UploadedFile arquivo;
    private StreamedContent file;
    private int id;
    private String nome, autor, descricao;
    private List<Pesquisa> lista;
    

    public void save(ActionEvent actionEvent) {
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setNome(nome);
        pesquisa.setAutor(autor);
        pesquisa.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        pesquisaDAO.gravar(pesquisa);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Pesquisa pesquisa = new Pesquisa();
            pesquisa.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                pesquisa.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(PesquisaMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            pesquisaDAO.gravarArquivo(pesquisa);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(index);
        pesquisaDAO.deletar(pesquisa);
        this.clear();
    }

    public void clear() {
        Pesquisa pesquisa = new Pesquisa();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(index);
        pesquisa = pesquisaDAO.retrieve(pesquisa);
        this.id = pesquisa.getId();
        this.nome = pesquisa.getNome();
        this.autor = pesquisa.getAutor();
        this.descricao = pesquisa.getDescricao();

        return "alterar_pesquisa";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoPesquisa";
    }

    public String update() {
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(id);
        pesquisa.setNome(nome);
        pesquisa.setAutor(autor);
        pesquisa.setDescricao(descricao);

        pesquisaDAO.alterar(pesquisa);

        return "ok";
    } 
    
    public void download() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream);
    }

    public StreamedContent getFile() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codDown".toString()));
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setId(index);
        pesquisa = pesquisaDAO.retrieve(pesquisa);
        if (pesquisa != null) {
            byte[] arquivo1 = pesquisa.getArquivo();
            ByteArrayInputStream bais = new ByteArrayInputStream(arquivo1);
            DefaultStreamedContent defaultStreamedContent = new DefaultStreamedContent(bais,"text/plain","teste.txt");
            return defaultStreamedContent;
        }
        return null;
    }
    

    public PesquisaDAORemote getPesquisaDAO() {
        return pesquisaDAO;
    }

    public void setPesquisaDAO(PesquisaDAORemote pesquisaDAO) {
        this.pesquisaDAO = pesquisaDAO;
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

    public List<Pesquisa> getLista() {
        return pesquisaDAO.listar();
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
