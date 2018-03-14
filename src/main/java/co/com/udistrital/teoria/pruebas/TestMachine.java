/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.udistrital.teoria.pruebas;

import co.com.udistrital.teoria.vo.MachineStatesVO;
import co.com.udistrital.teoria.vo.StatesVO;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class TestMachine {

   public String ecuacion1,ecuacion2,ecuacion3;
   Integer numMemories;
    public void RecibeCircuitos(String c1,String c2, String c3){
    
         this.ecuacion1= c1;
         this.ecuacion2= c2;
         this.ecuacion3= c3;
        
    }
    public void recibeMemorias(int n){
        //System.out.println("Ingrese numero de elementos de memoria: ");
        numMemories = n;
    }
    
    public void execute(){
        String responseHead = "E\t";
        String responseBody = "";
        Scanner sc = new Scanner(System.in);
                
        Integer numEstados = (int) Math.pow(2, numMemories);
        System.out.println("Numero de estados: " + numEstados);

        
        MachineStatesVO machine = new MachineStatesVO();
        machine.addOperacion(this.ecuacion1);
        machine.addOperacion(this.ecuacion2);
        machine.addOperacion(this.ecuacion3);
        for (int i = 0; i < numEstados; i++) {
            if ((numMemories - Integer.toBinaryString(i).length()) > 0) {
                String regex = "%0" + (numMemories - Integer.toBinaryString(i).length()) + "d";
                machine.addEstado(new StatesVO("S" + i, String.format(regex, 0) + Integer.toBinaryString(i)));
            } else {
                machine.addEstado(new StatesVO("S" + i, Integer.toBinaryString(i)));
            }
        }

        for (int i = 0; i < numMemories; i++) {
            responseHead += "M" + (i + 1) + "\t";
        }
        for (int i = 0; i < numMemories; i++) {
            responseHead += "M" + (i + 1) + "+\t";
        }
        for (int i = 0; i < machine.getOperaciones().size(); i++) {
            responseHead += "O" + (i + 1) + "\t";
        }
        responseHead += "S" + "\t";
        responseHead += "S+" + "\t";

        Integer[] entrada = {0, 1};
        for (StatesVO estado : machine.getEstados()) {
            for (Integer in : entrada) {
                responseBody += in + "\t";
                char[] auxC1 = estado.valuesToString().toCharArray();
                for (char c : auxC1) {
                    responseBody += c + "\t";
                }
                char[] auxC2 = estado.nextValues(in).toCharArray();
                for (char c : auxC2) {
                    responseBody += c + "\t";
                }
                for (Integer salida : estado.getSalidas(in)) {
                    responseBody += salida + "\t";
                }
                responseBody += estado.getName() + "\t";
                for (StatesVO est : machine.getEstados()) {
                    if (est.valuesToString().equals(estado.nextValues(in))) {
                        responseBody += est.getName() + "\t";
                        break;
                    }
                }
                responseBody += "\n";
            }
        }
        System.out.println(responseHead);
        System.out.println(responseBody);

        System.out.println("Ingrese una palabra: ");
        String palabra = sc.next();
        String palabraBinario = wordToBinary(palabra);
        System.out.println("Palabra en Binario: " + palabraBinario);

        //TODO
        char[] bin = palabraBinario.toCharArray();
        // Codificacion de la palabra
        String codificacion = "";
        String printEstados = "Estado Actual: ";
        String estInicial = String.format("%0" + (numMemories) + "d", 0);
        for (char c : bin) {
            Integer input = Integer.parseInt("" + c);
            for (StatesVO estado : machine.getEstados()) {
                if (estado.valuesToString().equals(estInicial)) {
                    printEstados += estado.getName() + ",";
                    codificacion += estado.salidasToString(input);
                    estInicial = estado.nextValues(input);
                    break;
                }
            }
        }
        System.out.println(printEstados);
        System.out.println("Palabra codificada: " + codificacion);
    }
    
    
  /*  public static void main(String[] args) {
     /*   
        String responseHead = "E\t";
        String responseBody = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese numero de elementos de memoria: ");
        Integer numMemories = sc.nextInt();

        

//        System.out.println("Ingrese la ecuacion del sistema, si desea termina digite 0:");
//        String ecuacion = sc.nextLine();
//        Integer numMemories = 0;
//        String[] elemEcuacion = ecuacion.toUpperCase().replaceAll(" +", "").trim().split("XOR");
//        for (String element : elemEcuacion) {
//            System.out.println("Elemento: " + element.trim());
//            if(element.contains("M")) {
//                Integer aux = Integer.parseInt(element.replace("M", ""));
//                if(aux > numMemories) {
//                    numMemories = aux;
//                }
//            }
//        }
        Integer numEstados = (int) Math.pow(2, numMemories);
        System.out.println("Numero de estados: " + numEstados);

        MachineStatesVO machine = new MachineStatesVO();
        machine.addOperacion(this.ecuacion1);
        machine.addOperacion(ecuacion2);
        machine.addOperacion(ecuacion3);
        for (int i = 0; i < numEstados; i++) {
            if ((numMemories - Integer.toBinaryString(i).length()) > 0) {
                String regex = "%0" + (numMemories - Integer.toBinaryString(i).length()) + "d";
//                System.out.println("Prueba: " + String.format(regex, 0) + Integer.toBinaryString(i));
                machine.addEstado(new StatesVO("S" + i, String.format(regex, 0) + Integer.toBinaryString(i)));
            } else {
//                System.out.println("Prueba: " + Integer.toBinaryString(i));
                machine.addEstado(new StatesVO("S" + i, Integer.toBinaryString(i)));
            }
        }

//        Integer cont = 0;
//        while(cont < 3) {
//            System.out.println("Ingrese la ecuacion de su "+(cont+1)+" ecuacion:");
//        }
        for (int i = 0; i < numMemories; i++) {
            responseHead += "M" + (i + 1) + "\t";
        }
        for (int i = 0; i < numMemories; i++) {
            responseHead += "M" + (i + 1) + "+\t";
        }
        for (int i = 0; i < machine.getOperaciones().size(); i++) {
            responseHead += "O" + (i + 1) + "\t";
        }
        responseHead += "S" + "\t";
        responseHead += "S+" + "\t";

        Integer[] entrada = {0, 1};
        for (StatesVO estado : machine.getEstados()) {
            for (Integer in : entrada) {
                responseBody += in + "\t";
                char[] auxC1 = estado.valuesToString().toCharArray();
                for (char c : auxC1) {
                    responseBody += c + "\t";
                }
                char[] auxC2 = estado.nextValues(in).toCharArray();
                for (char c : auxC2) {
                    responseBody += c + "\t";
                }
                for (Integer salida : estado.getSalidas(in)) {
                    responseBody += salida + "\t";
                }
                responseBody += estado.getName() + "\t";
                for (StatesVO est : machine.getEstados()) {
                    if (est.valuesToString().equals(estado.nextValues(in))) {
                        responseBody += est.getName() + "\t";
                        break;
                    }
                }
                responseBody += "\n";
            }
        }
        System.out.println(responseHead);
        System.out.println(responseBody);

        System.out.println("Ingrese una palabra: ");
        String palabra = sc.next();
        String palabraBinario = wordToBinary(palabra);
        System.out.println("Palabra en Binario: " + palabraBinario);

        //TODO
        char[] bin = palabraBinario.toCharArray();
        // Codificacion de la palabra
        String codificacion = "";
        String printEstados = "Estado Actual: ";
        String estInicial = String.format("%0" + (numMemories) + "d", 0);
        for (char c : bin) {
            Integer input = Integer.parseInt("" + c);
            for (StatesVO estado : machine.getEstados()) {
                if (estado.valuesToString().equals(estInicial)) {
                    printEstados += estado.getName() + ",";
                    codificacion += estado.salidasToString(input);
                    estInicial = estado.nextValues(input);
                    break;
                }
            }
        }
        System.out.println(printEstados);
        System.out.println("Palabra codificada: " + codificacion);
    }*/

    public static String wordToBinary(String word) {
        String response = "";
        char[] array = word.toCharArray();
        for (char c : array) {
//            System.out.println("ASCII: " + (int) c);
//            System.out.println("BINARY: " + Integer.toBinaryString(c));
            response += Integer.toBinaryString(c);
        }
//        System.out.println("Palabra original: " + word);
//        System.out.println("Palabra en Binario: " + response);
        return response;
    }
}
