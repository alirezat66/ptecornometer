package ir.greencode.cornometer.api;

import io.reactivex.Observable;
import ir.greencode.cornometer.mvp.model.GroupResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApiService {
    // the first one is just for sample we can get other sample in below comments
   /* @GET("repositories")
    Observable<List<GitHubRepoModel>> getRepoFromRemote();*/
   @FormUrlEncoded
    @POST("user/getCategories")
    Observable<GroupResponse> getGroups(@Field("timestamp") long timeStamp , @Field("appId") String appId);

}
