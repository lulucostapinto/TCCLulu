/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Permissao;
import br.sgci.bean.Usuario;
import br.sgci.dao.UsuarioDAORemote;
import br.sgci.util.CriptografarSenha;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lulu
 */
@Named(value = "usuarioMNG")
@SessionScoped
public class UsuarioMNG implements Serializable {

    @Dependent
    public static final String USER_SESSION_KEY = "sessaousuario";
    @EJB
    UsuarioDAORemote usuarioDAO;
    private int id;
    private String login;
    private String senha;
    private Permissao permissao;
    private List<Usuario> lista;

    private boolean perfilAdm = false;

    public String save() {
        Usuario usu = new Usuario();
        usu.setLogin(this.login);
        usu.setPerfilAdm(perfilAdm);
        usu.setLogin(this.login);
        usu.setSenha(CriptografarSenha.md5(this.senha));
        usuarioDAO.gravar(usu);

        return "ok";

    }

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

    public Boolean getPerfilAdm() {
        return perfilAdm;
    }

    public void setPerfilAdm(Boolean perfilAdm) {
        this.perfilAdm = perfilAdm;
    }

    public void valida() throws IOException {
        Usuario u = usuarioDAO.selecionarPorLogin(this.getLogin());
        if (u != null) {
            if (u.getSenha().equals(this.getSenha())) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, u);
                System.out.println("LOGADO");
                this.perfilAdm = u.getPerfilAdm();
                context.getCurrentInstance().getExternalContext().redirect("padrao.jsf");
            }
        }
    }

    public boolean isPerfilAdm() {
        return perfilAdm;
    }

    public void setPerfilAdm(boolean perfilAdm) {
        this.perfilAdm = perfilAdm;
    }

    public void invalida() throws IOException {
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
