/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Biblioteca;
import br.sgci.dao.BibliotecaDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "bibliotecaMNG")
@RequestScoped
public class BibliotecaMNG {

    @EJB
    BibliotecaDAORemote bibliotecaDAO;
    private int id;
    private String titulo;
    private String autor;
    private List<Biblioteca>lista;

    public void save() {
        Biblioteca b = new Biblioteca();
        b.setTitulo(this.getTitulo());
        b.setAutor(this.getAutor());

        bibliotecaDAO.gravar(b);

        this.setTitulo(null);
        this.setAutor(null);

    }

    public List<Biblioteca> getLista() {
        return bibliotecaDAO.listar();
    }

    public void setLista(List<Biblioteca> lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
