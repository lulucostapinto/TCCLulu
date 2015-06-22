/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Certificado;
import br.sgci.bean.Pessoa;
import br.sgci.bean.Video;
import br.sgci.dao.CertificadoDAORemote;
import br.sgci.dao.PessoaDAORemote;
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
@Named(value = "certificadoMNG")
@RequestScoped
public class CertificadoMNG {

    @EJB
    CertificadoDAORemote certificadoDAO;
    @EJB
    PessoaDAORemote pessoaDAO;
    private UploadedFile arquivo;
    private int id;
    private String nome;
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;
    private List<Certificado> lista;    
    

    public void save(ActionEvent actionEvent) {
        Certificado certificado = new Certificado();
        certificado.setNome(this.getNome());
        pessoa = pessoaDAO.selecionar(pessoa.getId());
        certificado.setPessoa(pessoa);
        certificadoDAO.gravar(certificado);

    }

    public void upload() {
        if (arquivo != null) {
            Certificado certificado = new Certificado();
            certificado.setId(id);
            try {
                InputStream is = arquivo.getInputstream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                certificado.setArquivo(buffer.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(VideoMNG.class.getName()).log(Level.SEVERE, null, ex);
            }
            certificadoDAO.gravarArquivo(certificado);
            FacesMessage message = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded. Size: " + arquivo.getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void remove() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir"));
        Certificado certificado = new Certificado();
        certificado.setId(index);
        certificadoDAO.deletar(certificado);
        this.clear();
    }

    public void clear() {
        Certificado certificado = new Certificado();
    }

    public String prepUpdate() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar".toString()));
        Certificado certificado = new Certificado();
        certificado.setId(index);
        certificado = certificadoDAO.retrieve(certificado);
        this.id = certificado.getId();
        this.nome = certificado.getNome();
   

        return "alterar_certificado";

    }
    public String prepUpdate2() {
        Integer index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar"));
        this.id = index;
        return "incluir_arquivoCertificado";
    }
    
    public String update() {
        Certificado certificado = new Certificado();
        certificado.setId(id);
        certificado.setNome(nome);
        

        certificadoDAO.alterar(certificado);

        return "ok";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CertificadoDAORemote getCertificadoDAO() {
        return certificadoDAO;
    }

    public void setCertificadoDAO(CertificadoDAORemote certificadoDAO) {
        this.certificadoDAO = certificadoDAO;
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

    public List<Certificado> getLista() {
        return certificadoDAO.listar();
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

}
