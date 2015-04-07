/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Sala;
import br.sgci.dao.SalaDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "salaMNG")
@RequestScoped
public class SalaMNG {

    @EJB
    SalaDAORemote salaDAO;
    private int id;
    private String nome;
    private List<Sala> lista;

    public void save(ActionEvent actionEvent) {
        Sala s = new Sala();
        s.setNome(this.getNome());       

        salaDAO.gravar(s);

        this.setNome(null);
    }

    public SalaDAORemote getSalaDAO() {
        return salaDAO;
    }

    public void setSalaDAO(SalaDAORemote salaDAO) {
        this.salaDAO = salaDAO;
    }
    
        

    public List<Sala> getLista() {
      return salaDAO.listar();
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

       

}
