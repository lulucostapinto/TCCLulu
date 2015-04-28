/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.converter;

import br.sgci.bean.Sala;
import br.sgci.mng.SalaMNG;
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
@FacesConverter(value = "salaConverter")
public class SalaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{salaMNG}", SalaMNG.class);
        SalaMNG salaMNG = (SalaMNG) vExp.getValue(ctx.getELContext());
        Sala sal = salaMNG.getSala(Integer.valueOf(value));
        if (sal == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Sala não foi encontrado");
            throw new ConverterException(msg);
        }
        return sal;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "0";
        } else {
            Sala sala=(Sala) value;
            return String.valueOf(sala.getId());
//            return ((Sala) value).getId().toString();
        }
    }
}
