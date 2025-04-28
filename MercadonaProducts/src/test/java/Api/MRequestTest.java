package Api;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class MRequestTest {
    URL urlapi = new URL("49173", "112");
    MRequest mRequest = new MRequest();
    @Test
    void getRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest var = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() + urlapi.getCATEGORY_ID());
        assertEquals(var.method(), "GET");
        assertEquals(var.uri(), urlapi.getAPI_URL() + urlapi.getCATEGORY() + urlapi.getCATEGORY_ID());
    }
}