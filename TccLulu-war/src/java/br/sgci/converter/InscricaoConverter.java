/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.converter;

import br.sgci.bean.Inscricao;
import br.sgci.mng.CursoMNG;
import br.sgci.mng.InscricaoMNG;
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
@FacesConverter(value = "inscricaoConverter")
public class InscricaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{inscricaoMNG}", InscricaoMNG.class);
        InscricaoMNG inscricaoMNG = (InscricaoMNG) vExp.getValue(ctx.getELContext());
        Inscricao ins = inscricaoMNG.getInscricao(Integer.valueOf(value));
        if (ins == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Inscricao não foi encontrado");
            throw new ConverterException(msg);
        }
        return ins;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "0";
        } else {
            Inscricao inscricao=(Inscricao) value;
            return String.valueOf(inscricao.getId());
//            return ((Sala) value).getId().toString();
        }
    }
}