package model;

public class url {
    /*
    Defines the API URL
     */
    private String API_URL = "https://api.spoonacular.com/recipes/";
    private String INGREDIENTS = "/findByIngredients";
    private String INFO = "/information";
    private String SIMILAR = "/similar";
    private String RANDOM = "/random";
    private String APIKEY;
    private int NUMBER_RECIPES;

    public url(String APIKEY, int NUMBER_RECIPES) {
        this.APIKEY = APIKEY;
        this.NUMBER_RECIPES = NUMBER_RECIPES;
    }

    public String getAPI_URL() {
        return API_URL;}
    public String getINGREDIENTS() {
        return INGREDIENTS;}
    public String getINFO() {
        return INFO;}
    public String getSIMILAR() {
        return SIMILAR;}
    public String getRANDOM() {
        return RANDOM;}
    public String getAPIKEY() {
        return APIKEY;}
    public int getNUMBER_RECIPES() {
        return NUMBER_RECIPES;}
}
