package domain.model;

import java.util.List;

public class Recipe {
    public Recipe(String  id, String title, int readyInMinutes, int servings, String image, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean veryPopular, boolean sustainable, double healthScore, String summary,String extendedIngredients_Str) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.healthScore = healthScore;
        this.summary = summary;
        this.extendedIngredients_Str = extendedIngredients_Str;
    }

    /*
        Defines the Recipe Attributes
         */
    private String  id;
    private String title;
    private int readyInMinutes;
    private int servings;
    private String image;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean veryPopular;
    private boolean sustainable;
    private double healthScore;
    private String summary;
    private String extendedIngredients_Str;
    private List<Ingredient> extendedIngredients;



    private List<String> steps;


    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public int getReadyInMinutes() {
        return readyInMinutes;
    }
    public int getServings() {
        return servings;
    }
    public String getImage() {
        return image;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public boolean isVegan() {
        return vegan;
    }
    public boolean isGlutenFree() {
        return glutenFree;
    }
    public boolean isDairyFree() {
        return dairyFree;
    }
    public boolean isVeryHealthy() {
        return veryHealthy;
    }
    public boolean isVeryPopular() {
        return veryPopular;
    }
    public boolean isSustainable() {
        return sustainable;
    }
    public double getHealthScore() {
        return healthScore;
    }
    public String getSummary() {
        return summary;
    }
    public List<Ingredient> getextendedIngredients() {
        return extendedIngredients;
    }
    public String getextendedIngredients_Str() {return extendedIngredients_Str;}
    public List<String> getSteps() {return steps;}
}
