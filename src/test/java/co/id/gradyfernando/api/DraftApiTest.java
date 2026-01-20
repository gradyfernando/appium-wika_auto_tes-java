package co.id.gradyfernando.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.model.BaseDaftarSurat;
import co.id.gradyfernando.model.BaseRecords;
import co.id.gradyfernando.model.BaseResponseModel;
import co.id.gradyfernando.model.Draft;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class DraftApiTest {
    
	public static List<Draft> getListDraft(String token, String offset, String limit, Map<String, String> additionalFilter) {
		Map<String, String> formParams = new HashMap<>();
		formParams.put("token", token);
		formParams.put("offset", offset);
		formParams.put("limit", limit);
		
        BaseResponseModel<BaseRecords<BaseDaftarSurat<Draft>>> response = RestAssured
            .given()
            	.log().all()
            	.header("Accept", "application/json")
            	.baseUri(ApiConfig.BASE_API_URL)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
            .post(Route.GET_LIST_DRAFT)
            .then()
            	.log().all()
            	.assertThat()
                .statusCode(200)
                .using().defaultParser(Parser.JSON)
                .extract()
                .as(new TypeRef<BaseResponseModel<BaseRecords<BaseDaftarSurat<Draft>>>>() {});
        
        return response.getData().getRecords().getData();
	}
}
