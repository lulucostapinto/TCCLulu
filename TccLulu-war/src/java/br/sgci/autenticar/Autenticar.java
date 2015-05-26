/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.autenticar;

import br.sgci.mng.UsuarioMNG;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Lulu
 */
public class Autenticar implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();

        if (!permite(fc)) {
            boolean paginaLogin = fc.getViewRoot().getViewId().lastIndexOf("index") > -1 ? true : false;
            if (!paginaLogin) {
                NavigationHandler nh = fc.getApplication().getNavigationHandler();
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(Autenticar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    

    private boolean permite(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(UsuarioMNG.USER_SESSION_KEY));
    }
}
