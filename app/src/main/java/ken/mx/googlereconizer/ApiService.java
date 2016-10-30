package ken.mx.googlereconizer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Body;

/**
 * Created by Ken on 29/10/16.
 */

public interface ApiService {


    @Headers("Ocp-Apim-Subscription-Key: 862f90751d0f4b7ca9ec4ee4eed2a601")
    @POST("/emotion/v1.0/recognize")
    Call<ResponseBody> getFace(@Body BodyImage Body);
}
