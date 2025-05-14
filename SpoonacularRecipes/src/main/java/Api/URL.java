package Api;

public class URL {
    /*
    Defines the API URL
     */
    private String API_URL = "https://api.spoonacular.com/recipes/";
    private String INGREDIENTS = "/findByIngredients";
    private String RECIPE_ID;
    private String INFO = "/information";
    private String SIMILAR = "/similar";
    private String SIMILAR_ID;
    private String RANDOM = "/random";
    private String APIKEY;
    private String INFORMATION = "/information";

    public URL (String RECIPE_ID, String APIKEY) {
        this.RECIPE_ID = RECIPE_ID;
        this.APIKEY = APIKEY;
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
    public String getSIMILAR_ID() {
        return SIMILAR_ID;}
    public String getAPIKEY() {
        return APIKEY;}
    public String getINFORMATION() {
        return INFORMATION;}
    public String getRECIPE_ID() {
        return RECIPE_ID;
    }
}
