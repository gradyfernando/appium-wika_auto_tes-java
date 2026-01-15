package co.id.gradyfernando.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.model.BaseDaftarSurat;
import co.id.gradyfernando.model.BaseRecords;
import co.id.gradyfernando.model.BaseResponseModel;
import co.id.gradyfernando.model.Disposisi;
import co.id.gradyfernando.model.enumeration.Jenis;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class DisposisiApiTest {
     
	public static List<Disposisi> getListSurat(Jenis jenis, String token, String offset, String limit, String sifat, Map<String, String> additionalFilter) {
		Map<String, String> formParams = new HashMap<>();
		formParams.put("token", token);
		formParams.put("offset", offset);
		formParams.put("limit", limit);
		formParams.put("sifat", sifat);

        if (additionalFilter.size() > 0) {
            for (Map.Entry<String, String> entry: additionalFilter.entrySet()) {
                formParams.put(entry.getKey(), entry.getValue());
            }
        }

        String apiUrl = "";
        switch (jenis) {
            case MASUK:
                apiUrl = Route.GET_LIST_DISPOSISI_MASUK;
                break;
            case KELUAR:
                apiUrl = Route.GET_LIST_DISPOSISI_KELUAR;
                break;
            default:
                break;
        }
		
        BaseResponseModel<BaseRecords<BaseDaftarSurat<Disposisi>>> response = RestAssured
            .given()
            	.log().all()
            	.header("Accept", "application/json")
            	.baseUri(ApiConfig.BASE_API_URL)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
            .post(apiUrl)
            .then()
            	.log().all()
            	.assertThat()
                .statusCode(200)
                .using().defaultParser(Parser.JSON)
                .extract()
                .as(new TypeRef<BaseResponseModel<BaseRecords<BaseDaftarSurat<Disposisi>>>>() {});
        
        List<Disposisi> suratList = response.getData().getRecords().getData();
        return suratList;
	}
    
}
