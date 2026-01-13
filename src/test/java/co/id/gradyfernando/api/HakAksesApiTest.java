package co.id.gradyfernando.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.BaseRecords;
import co.id.gradyfernando.model.BaseResponseModel;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class HakAksesApiTest {
    
	public static List<Akses> getAkses(String token) {
		Map<String, String> formParams = new HashMap<>();
		formParams.put("token", token);
		
        BaseResponseModel<BaseRecords<List<Akses>>> response = RestAssured
            .given()
            	.log().all()
            	.header("Accept", "application/json")
            	.baseUri(ApiConfig.BASE_URL)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
            .post(Route.USER_GET_AKSES)
            .then()
            	.log().all()
            	.assertThat()
                .statusCode(200)
                .using().defaultParser(Parser.JSON)
                .extract()
                .as(new TypeRef<BaseResponseModel<BaseRecords<List<Akses>>>>() {});
        
        List<Akses> aksesList = response.getData().getRecords();
        return aksesList;
	}

}