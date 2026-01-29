package co.id.gradyfernando.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.BaseRecords;
import co.id.gradyfernando.model.BaseResponseModel;
import co.id.gradyfernando.model.User;
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
            	.baseUri(ApiConfig.BASE_API_URL)
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

    public static Map<String, Object> setAkses(User user, Akses akses) {
		Map<String, String> formParams = new HashMap<>();
		formParams.put("token", user.getToken());
		formParams.put("iduser", user.getIduser());
		formParams.put("idrole", akses.getIdrole());
		formParams.put("idjabatan", akses.getIdjabatan());
		
        BaseResponseModel<Map<String, Object>> response = RestAssured
            .given()
            	.log().all()
            	.header("Accept", "application/json")
            	.baseUri(ApiConfig.BASE_API_URL)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
            .post(Route.USER_SET_AKSES)
            .then()
            	.log().all()
            	.assertThat()
                .statusCode(200)
                .using().defaultParser(Parser.JSON)
                .extract()
                .as(new TypeRef<BaseResponseModel<Map<String, Object>>>() {});
        
        Map<String, Object> responseData = response.getData();
        return responseData;
	}

}