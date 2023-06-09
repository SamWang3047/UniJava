package COMP90041.Week9.Cake_Inc;

import java.util.ArrayList;
import java.util.Collections;

public class Factory {
    private Machine[][] machines;

    private String[] ingredientNames = {"chocolate sponge base", "whipped cream", "chocolate shavings", "cherries"};

    public Factory(String[] layout){
        int size = layout.length;
        this.machines = new Machine[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                char machineCode = layout[i].charAt(j);
                this.machines[i][j] = Machine.fromCode(machineCode);
            }
        }
    }
    public void render() {
        int size = this.machines.length;
        String border = "+" + "-".repeat(size) + "+";
        System.out.println(border);
        for(Machine[] row: this.machines){
            System.out.print("|");
            for(Machine machine: row){
                System.out.print(machine != null ? machine.code : ' ');
            }
            System.out.println("|");
        }
        System.out.println(border);
        System.out.println();
    }

    public void printIngredients(){
        ArrayList<String> ingredients = new ArrayList<>();
        for(Machine[] row: this.machines){
            for(Machine machine: row){
                if(machine != null){
                    ingredients.add(this.ingredientNames[machine.ordinal()]);
                }
            }
        }
        Collections.sort(ingredients);
        for(String ingredient: ingredients){
            System.out.println(ingredient);
        }
    }

}
