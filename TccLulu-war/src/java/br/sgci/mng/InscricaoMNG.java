/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.mng;

import br.sgci.bean.Inscricao;
import br.sgci.dao.InscricaoDAORemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Lulu
 */
@Named(value = "inscricaoMNG")
@RequestScoped
public class InscricaoMNG {

    @EJB
    InscricaoDAORemote inscricaoDAO;
    private int id;
    private String nome, funcao, setor;
    private int cracha, centro_custo;
    private List<Inscricao> lista;

    public void save(ActionEvent actionEvent) {
        Inscricao i = new Inscricao();
        i.setNome(this.getNome());
        i.setFuncao(this.getFuncao());
        i.setSetor(this.getSetor());
        i.setCentro_custo(this.getCentro_custo());
        i.setCracha(this.getCracha());

        inscricaoDAO.gravar(i);

    }

    public InscricaoDAORemote getInscricaoDAO() {
        return inscricaoDAO;
    }

    public void setInscricaoDAO(InscricaoDAORemote inscricaoDAO) {
        this.inscricaoDAO = inscricaoDAO;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getCracha() {
        return cracha;
    }

    public void setCracha(int cracha) {
        this.cracha = cracha;
    }

    public int getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(int centro_custo) {
        this.centro_custo = centro_custo;
    }

    public List<Inscricao> getLista() {
        return inscricaoDAO.listar();
    }

    public void setLista(List<Inscricao> lista) {
        this.lista = lista;
    }

}
