package Api;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
class CategoryTest {

    URL urlapi = new URL("49173", "112");
    MRequest mRequest = new MRequest();
    Jsoon jsoon = new Jsoon();

  @Test
    void ClassAttTest() throws URISyntaxException, IOException, InterruptedException {
      HttpResponse response = mRequest.Execute(mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() + urlapi.getCATEGORY_ID()));
      Category category = jsoon.Mercadonato_json(response);
      assertFalse(category.getCategories().isEmpty());
      assertFalse(category.getCategories().get(0).getProducts().isEmpty());
      //assertTrue(category.getCategories().get(0).getProducts().contains(NProduct.class));
  }
}