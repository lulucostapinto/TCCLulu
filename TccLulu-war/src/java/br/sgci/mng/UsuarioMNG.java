/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Usuario;
import br.sgci.dao.UsuarioDAORemote;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "usuarioMNG")
@RequestScoped
public class UsuarioMNG {

    public static final String USER_SESSION_KEY = "sessaousuario";
    @EJB
    UsuarioDAORemote dao;
    private String login;
    private String senha;

    /** Creates a new instance of UsuarioMNG */
    public UsuarioMNG() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String valida() {
        System.out.println("Tentando logar");
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario = dao.findByLogin(login);
        if (usuario != null) {
            if (!usuario.getSenha().equals(senha)) {
                this.msgInvalidLogin(context);
                return null;
            }
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, usuario);
            System.out.println("Logado");
            return "paginainicial";
        } else {
            this.msgInvalidLogin(context);
            return null;
        }
    }

    private void msgInvalidLogin(FacesContext context) {
        System.out.println("não deu para logar");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação!", "Usuário ou senha inválidos");
        context.addMessage(null, message);
    }
}
