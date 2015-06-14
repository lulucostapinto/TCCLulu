/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Conteudo;
import br.sgci.dao.ConteudoDAORemote;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lulu
 */
@Named(value = "conteudoMNG")
@RequestScoped
public class ConteudoMNG {

    @EJB
    ConteudoDAORemote conteudoDAO;
    private UploadedFile arquivo;
    private int id;
   

    public void upload() {
        if (arquivo != null) {
            Conteudo conteudo = new Conteudo();
            conteudo.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                conteudo.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(ConteudoMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            conteudoDAO.gravarArquivo(conteudo);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir".toString()));
        Conteudo conteudo = new Conteudo();
        conteudo.setId(index);
        conteudoDAO.deletar(conteudo);
        this.clear();
    }

    public void clear() {
        Conteudo conteudo = new Conteudo();
    }

    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        this.id = index;
        return "incluir_conteudo";
    }

    public ConteudoDAORemote getConteudoDAO() {
        return conteudoDAO;
    }

    public void setConteudoDAO(ConteudoDAORemote conteudoDAO) {
        this.conteudoDAO = conteudoDAO;
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

}
