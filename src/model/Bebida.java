/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author robotica
 */

public class Bebida extends Alimento implements ImpostoAlcool {

    private boolean alcoolica;

    public boolean isAlcoolica() {
        return alcoolica;
    }

    public void setAlcoolica(boolean alcoolica) {
        this.alcoolica = alcoolica;
    }

    @Override
    public String exibirInfo() {
        return "Bebida: " + getNome() + " - Preço: R$" + getPreco() + (alcoolica ? " (Alcoólica)" : "");
    }

    @Override
    public double calcImposto() {
        if (alcoolica) {
            return getPreco() * 1;
        }
        return 0;
    }
}

