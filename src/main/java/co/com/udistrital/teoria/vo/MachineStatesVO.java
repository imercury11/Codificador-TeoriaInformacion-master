/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.udistrital.teoria.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class MachineStatesVO {
    
    private List<StatesVO> estados;
    private List<String> operaciones;

    public MachineStatesVO() {
    }

    public List<StatesVO> getEstados() {
        if(estados == null) {
            estados = new ArrayList<>();
        }
        return estados;
    }

    public List<String> getOperaciones() {
        return operaciones;
    }
    
    public void addEstado(StatesVO estado) {
        if(estados == null) {
            estados = new ArrayList<>();
        }
        estado.setOperaciones(operaciones);
        estados.add(estado);
    }
    
    public void addOperacion(String operacion) {
        if(this.operaciones == null) {
            this.operaciones = new ArrayList<>();
        }
        this.operaciones.add(operacion);
    }
}
