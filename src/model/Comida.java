/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author robotica
 */
public class Comida extends Alimento {

    @Override
    public String exibirInfo() {
        return "Comida: " + getNome() + " - Preço: R$" + getPreco();
    }

}

