package Schemas;

import java.util.List;

public class Ingredient {
    /*
    Defines the ingredient from JSON
     */


    private int id;
    private String nameClean;
    private String amount;
    private String unit;
    private List<String> meta;
    private String meta_String;
    /*
    private extendedIngredients extendedIngredients;
    public class extendedIngredients{

    }
     */

    public int getId() {
        return id;}
    public String getNameClean() {
        return nameClean;}
    public String getAmount() {
        return amount;}
    public String getUnit() {
        return unit;}
    public List<String> getMeta() {
        return meta;}

    public Ingredient(int id, String nameClean, String amount, String unit, String meta_String) {
        this.id = id;
        this.nameClean = nameClean;
        this.amount = amount;
        this.unit = unit;
        this.meta_String = meta_String;
    }
}
