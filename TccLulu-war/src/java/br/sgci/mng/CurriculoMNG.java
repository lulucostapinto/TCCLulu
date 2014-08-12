/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Curriculo;
import br.sgci.dao.CurriculoDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "curriculoMNG")
@RequestScoped
public class CurriculoMNG {

    @EJB
    CurriculoDAORemote curriculoDAO;
    private int id;
    private int idUsuario;
    private String cursoExt;
    private List<Curriculo> lista;

    public void save() {
        Curriculo c = new Curriculo();
        c.setId(this.getId());
        c.setIdUsuario(this.getIdUsuario());
        c.setCursoExt(this.getCursoExt());

        curriculoDAO.gravar(c);

        //this.setIdUsuario(null);
        this.setCursoExt(null);

    }

    public List<Curriculo> getLista() {
        return curriculoDAO.listar();
    }

    public void setLista(List<Curriculo> lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    private String getCursoExt() {
        return cursoExt;
    }

    private void setCursoExt(String cursoExt) {
        this.cursoExt = cursoExt;
    }

}
