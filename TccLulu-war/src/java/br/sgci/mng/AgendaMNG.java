/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Agenda;
import br.sgci.bean.Curso;
import br.sgci.dao.AgendaDAORemote;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "agendaMNG")
@RequestScoped
public class AgendaMNG {

    @EJB
    AgendaDAORemote agendaDAO;
    private int id;
    private String sala;
    private int idCurso;
    private Timestamp data;
    private List<Curso> lista;

    public void save() {
        Agenda a = new Agenda();
        a.setSala(this.getSala());
        a.setIdCurso(this.getIdCurso());
        a.setData(this.getData());

        agendaDAO.gravar(a);

        this.setSala(null);
        //this.setIdCurso(null);

    }

    public List<Agenda> getLista() {
        return agendaDAO.listar();
    }

    public void setLista(List<Curso> lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    private int getIdCurso() {
        return idCurso;
    }

    private void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    
    private Timestamp getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
