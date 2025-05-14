package Api;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RecipeTest extends TestCase {
    public RecipeTest() throws URISyntaxException, IOException, InterruptedException {
    }

    SRequest sr = new SRequest();
    URL url = new URL("321", "7722089f43bb44119b24d0cb3c6ea523");
    HttpRequest request= sr.Get(url.getAPIKEY(), url.getAPI_URL(), url.getRANDOM() + "?number=1");
    HttpResponse response  =  sr.Execute(request);

    Jsoon jsoon = new Jsoon();
    GetRecipes recipe = jsoon.Spoon_json(response);

    @Test
    public void test() throws IOException, InterruptedException {
        assertEquals(recipe.getRecipes().get(0).getClass(), Recipe.class);
    }


}