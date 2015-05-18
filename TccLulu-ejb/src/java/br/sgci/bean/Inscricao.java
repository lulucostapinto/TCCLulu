/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lulu
 */
@Entity
@Table(name = "inscricao")
public class Inscricao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id; 
    private int numero;
    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "ID")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "ID")
    private Pessoa pessoa;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

   
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
        

}