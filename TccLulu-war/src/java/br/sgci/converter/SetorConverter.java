/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.converter;

import br.sgci.bean.Setor;
import br.sgci.mng.SetorMNG;
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
@FacesConverter(value = "setorConverter")
public class SetorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{setorMNG}", SetorMNG.class);
        SetorMNG setorMNG = (SetorMNG) vExp.getValue(ctx.getELContext());
        Setor set = setorMNG.getSetor(Integer.valueOf(value));
        if (set == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Setor não foi encontrado");
            throw new ConverterException(msg);
        }
        return set;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "0";
        } else {
            Setor setor=(Setor) value;
            return String.valueOf(setor.getId());
//            return ((Sala) value).getId().toString();
        }
    }
}
