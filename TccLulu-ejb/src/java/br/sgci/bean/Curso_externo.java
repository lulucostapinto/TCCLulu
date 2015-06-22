/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lulu
 */
@Entity
@Table(name = "curso_externo")
//@NamedQuery(name = "Curso_externo.totalHoras", query = "select sum(c) from Curso_externo c where c.pessoa=:pessoa")
@NamedQuery(name = "Curso_externo.totalHorasPessoa", query = "select sum(c.qtd_horas) from Curso_externo c where c.pessoa=:pessoa")

public class Curso_externo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte[] arquivo;
    private int qtd_horas;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date data_inicio, data_fim;
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id_pessoa", nullable = false)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "ID")
    private Pessoa pessoa;

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
    

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getQtd_horas() {
        return qtd_horas;
    }

    public void setQtd_horas(int qtd_horas) {
        this.qtd_horas = qtd_horas;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
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
        final Curso_externo other = (Curso_externo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
