package Api;

import java.util.List;

public class Recipe {
    /*
    Defines the Recipe Attributes
     */
    private int id;
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
    private List<Ingredient> extendedIngredients;


    public int getId() {
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
}
