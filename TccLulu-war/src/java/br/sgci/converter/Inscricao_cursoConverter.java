/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.converter;

import br.sgci.bean.Curso;
import br.sgci.mng.CursoMNG;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Lulu
 */
@FacesConverter(value = "inscricao_cursoConverter")
public class Inscricao_cursoConverter implements Converter{
    //vai ter o mesmo do curso, porém para a inscrição contendo o curso

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{cursoMNG}", CursoMNG.class);
        CursoMNG cursoMNG = (CursoMNG) vExp.getValue(ctx.getELContext());
        Curso cur = cursoMNG.getCurso(Integer.valueOf(value));
        if (cur == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Curso não foi encontrado");
            throw new ConverterException(msg);
        }
        return cur;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "0";
        } else {
            Curso curso = (Curso) value;
            return String.valueOf(curso.getId());
//            return ((Sala) value).getId().toString();
        }
    }
}
