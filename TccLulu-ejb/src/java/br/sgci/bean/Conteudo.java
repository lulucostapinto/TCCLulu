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
import javax.persistence.Table;

/**
 *
 * @author Lulu
 */
@Entity
@Table(name = "conteudo")
public class Conteudo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
    private byte[] arquivo;   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
       
    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
    
     
   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conteudo other = (Conteudo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}