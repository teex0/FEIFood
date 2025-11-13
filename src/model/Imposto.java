/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tex
 */
public class Imposto {
    public static final double TAXA_ALCOOL = 0.80; 

    public static double calcularPreco(double precoBase, boolean alcool) {
        if (alcool) {
            return precoBase * (1 + TAXA_ALCOOL);
        }
        return precoBase;
    }
}

