/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Permissao;
import br.sgci.bean.Usuario;
import br.sgci.dao.UsuarioDAORemote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lulu
 */
@Named(value = "usuarioMNG")
@RequestScoped
public class UsuarioMNG{

    @Dependent
    public static final String USER_SESSION_KEY = "sessaousuario";
    @EJB
    UsuarioDAORemote usuarioDAO;
    private int id;    
    private String login;
    private String senha;
    private Permissao permissao;
    private List<Usuario> lista;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

   

    public void valida() throws IOException {
        Usuario u = usuarioDAO.selecionarPorLogin(this.getLogin());
        if (u != null) {
            if (u.getSenha().equals(this.getSenha())) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, u);
                System.out.println("LOGADO");
                context.getCurrentInstance().getExternalContext().redirect("padrao.jsf");
            }
        }
    }
    
    public void invalida () throws IOException{
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }
    
    public List<Usuario> getLista() {
        return usuarioDAO.listar();
    }
}
