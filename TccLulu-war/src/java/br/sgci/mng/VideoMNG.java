/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Video;
import br.sgci.dao.VideoDAORemote;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lulu
 */
@Named(value = "videoMNG")
@RequestScoped
public class VideoMNG {

    @EJB
    VideoDAORemote videoDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome, autor, descricao;
    private List<Video> lista;

    public void save(ActionEvent actionEvent) {
        Video v = new Video();
        v.setNome(nome);
        v.setAutor(autor);
        v.setDescricao(descricao);
        // v.setArquivo(arquivo.getContents());
        videoDAO.gravar(v);
        this.setNome(null);

    }

    public void upload() {
        if (arquivo != null) {
            Video video = new Video();
            video.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                video.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(VideoMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            videoDAO.gravarArquivo(video);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Video video = new Video();
        video.setId(index);
        videoDAO.deletar(video);
        this.clear();
    }

    public void clear() {
        Video video = new Video();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Video video = new Video();
        video.setId(index);
        video = videoDAO.retrieve(video);
        this.id = video.getId();
        this.nome = video.getNome();
        this.autor = video.getAutor();
        this.descricao = video.getDescricao();

        return "alterar_video";

    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_arquivoVideo";
    }

    public String update() {
        Video video = new Video();
        video.setId(id);
        video.setNome(nome);
        video.setAutor(autor);
        video.setDescricao(descricao);

        videoDAO.alterar(video);

        return "ok";
    }

    public VideoDAORemote getVideoDAO() {
        return videoDAO;
    }

    public void setVideoDAO(VideoDAORemote videoDAO) {
        this.videoDAO = videoDAO;
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

    public List<Video> getLista() {
        return videoDAO.listar();
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
