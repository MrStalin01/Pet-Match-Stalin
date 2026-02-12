package com.rodgar00.petmatch;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
public interface ApiInterface {
    @Multipart
    @POST("adoptados/") // URL relativa
    Call<DogModel> crearAnimal(
                @Part("nombre") RequestBody nombre,
                @Part("duenyo") RequestBody duenyo,
                @Part("edad") RequestBody edad,
                @Part("localizacion") RequestBody localizacion,
                @Part("descripcion") RequestBody descripcion,
                @Part("categoria") RequestBody categoria,
                @Part("raza") RequestBody raza,
                @Part MultipartBody.Part imagen
        );

    @GET("adoptados/")
    Call<List<DogModel>> getAdoptados();

    @GET("encontrados/")
    Call<List<DogModel>> getEncontrados();

    @GET("perdidos/")
    Call<List<DogModel>> getPerdidos();

    @GET("favoritos/")
    Call<List<DogModel>> getFavoritos();
}
