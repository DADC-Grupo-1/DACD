package Api;

public class URL {
    /*
    Defines the API URL
     */
    private String API_URL = "https://api.spoonacular.com/recipes";
    private String INGREDIENTS = "/findByIngredients";
    private String INGREDIENTS_ID;
    private String INFO = "/information";
    private String SIMILAR = "/similar";
    private String SIMILAR_ID;
    private String RANDOM = "/random";
    private String APIKEY;

    public URL (String INGREDIENTS_ID, String SIMILAR_ID, String APIKEY) {
        this.INGREDIENTS_ID = INGREDIENTS_ID;
        this.SIMILAR_ID = SIMILAR_ID;
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
    public String getINGREDIENTS_ID() {
        return INGREDIENTS_ID;}
    public String getSIMILAR_ID() {
        return SIMILAR_ID;}
    public String getAPIKEY() {
        return APIKEY;}



}
