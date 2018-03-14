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
public class StatesVO {

    private String name;
    private List<Integer> values;
    private List<String> operaciones;
    private List<Integer> salidas;

    public StatesVO(String name, String values) {
        this.name = name;
        this.values = new ArrayList<>();
        if (values != null && !values.equals("")) {
            char[] auxValues = values.toCharArray();
            for (char val : auxValues) {
                this.values.add(Integer.parseInt("" + val));
            }
        }
        this.salidas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void addValue(Integer value) {
        values.add(value);
    }

    public String valuesToString() {
        String response = "";
        for (Integer value : this.values) {
            response += value.toString();
        }
        return response;
    }
    
    public String salidasToString(Integer input) {
        String response = "";
        for (Integer value : getSalidas(input)) {
            response += value.toString();
        }
        return response;
    }

    public String nextValues(Integer input) {
        String response = "";
        // Se corre la secuencia del estado
        String aux = input.toString();
        for (Integer value : this.values) {
            response += aux;
            aux = value.toString();
        }
        return response;
    }

    public void setOperaciones(List<String> operaciones) {
        this.operaciones = operaciones;
    }

    public Integer getXORResult(Integer numberOne, Integer numberTwo) {
        return numberOne ^ numberTwo;
    }

    public Integer getValueByElement(Integer input, String elemEcuacion) {
        Integer response = 0;
        if (elemEcuacion.contains("E")) {
            response = input;
        } else {
            for (int i = 0; i < this.values.size(); i++) {
                if (elemEcuacion.contains("M" + (i + 1))) {
                    response = this.values.get(i);
                }
            }
        }
        return response;
    }

    public List<Integer> getSalidas(Integer input) {
        // Calculo de las Salidas
        Integer val = 0;
        if(this.salidas.size() > 0) {
            this.salidas.clear();
        }
        for (String operacion : this.operaciones) {
            String[] elemEcuacion = operacion.toUpperCase().replaceAll(" +", "").trim().split("XOR");
            if (elemEcuacion.length > 1) {
                Integer val1 = 0;
                Integer val2 = 0;
                for (int i = 1; i < elemEcuacion.length; i++) {
                    if (i == 1) {
                        val1 = getValueByElement(input, elemEcuacion[i-1]);
                        val2 = getValueByElement(input, elemEcuacion[i]);
                        val = getXORResult(val1, val2);
                    } else {
                        val = getXORResult(val, getValueByElement(input, elemEcuacion[i]));
                    }
                }
            } else {
                val = getValueByElement(input, elemEcuacion[0]);
            }
            this.salidas.add(val);
        }
        return salidas;
    }
}
