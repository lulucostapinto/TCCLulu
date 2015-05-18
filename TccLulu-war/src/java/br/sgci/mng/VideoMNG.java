/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curso_externo;
import br.sgci.bean.Pessoa;
import br.sgci.bean.Setor;
import br.sgci.bean.Video;
import br.sgci.dao.Curso_externoDAORemote;
import br.sgci.dao.PessoaDAORemote;
import br.sgci.dao.SetorDAORemote;
import br.sgci.dao.VideoDAORemote;
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
@Named(value = "videoMNG")
@RequestScoped
public class VideoMNG {

    @EJB
    VideoDAORemote videoDAO;
    private UploadedFile arquivo;
    private String nome;

    public void save(ActionEvent actionEvent) {

        Video v = new Video();
        v.setNome(this.getNome());
        //v.setArquivo(this.getArquivo());

        videoDAO.gravar(v);

        this.setNome(null);

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
