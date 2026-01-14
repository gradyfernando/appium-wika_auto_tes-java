package co.id.gradyfernando.api;

import java.util.HashMap;
import java.util.Map;

import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.model.BaseResponseModel;
import co.id.gradyfernando.model.User;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class LoginApiTest {
	
	public static User login(String username, String password, String device, String versi) {
		Map<String, String> formParams = new HashMap<>();
		// formParams.put("username", "4dminIntegra");
		// formParams.put("password", "k6JsDY2?qWSNy;U#");
		formParams.put("username", username);
		formParams.put("password", password);
		formParams.put("device", device);
		formParams.put("versi", versi);
		
		formParams.put("fcm_token", "");
		
        BaseResponseModel<User> response = RestAssured
            .given()
            	.log().all()
            	.header("Accept", "application/json")
            	.baseUri(ApiConfig.BASE_API_URL)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
            .post(Route.LOGIN)
            .then()
            	.log().all()
            	.assertThat()
                .statusCode(200)
                .using().defaultParser(Parser.JSON)
                .extract()
                .as(new TypeRef<BaseResponseModel<User>>() {});
        
        User user = response.getData();
        return user;
	}
}
