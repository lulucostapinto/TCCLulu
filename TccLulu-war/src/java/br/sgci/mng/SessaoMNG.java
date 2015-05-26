/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Usuario;
import java.io.Serializable;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lulu
 */
@Named(value = "sessaoMNG")
@SessionScoped
public class SessaoMNG implements Serializable {

    private Usuario usuario;

    public SessaoMNG() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String logout() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessao.invalidate();
        return "sair";
    }

    
}
