package org.example.travelingapp.data.remote.services

import org.example.travelingapp.core.request.destination.CreateDestinationRequest
import org.example.travelingapp.core.request.destination.UpdateDestinationRequest
import org.example.travelingapp.core.response.destination.DestinationListResponse
import org.example.travelingapp.core.response.destination.DestinationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IDestinationService {

    @GET("api/Destination/List")
    suspend fun getDestinations(
        @Header("Authorization") token: String,
        @QueryMap query: Map<String, String>
    ): Response<DestinationListResponse>

    @GET("api/Destination/GetById/{id}")
    suspend fun getDestinationById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DestinationResponse>

    @POST("api/Destination/Create")
    suspend fun createDestination(
        @Header("Authorization") token: String,
        @Body request: CreateDestinationRequest
    ): Response<DestinationResponse>

    @PUT("api/Destination/Update")
    suspend fun updateDestination(
        @Header("Authorization") token: String,
        @Body request: UpdateDestinationRequest
    ): Response<DestinationResponse>

    @DELETE("api/Destination/Delete/{id}")
    suspend fun deleteDestination(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DestinationResponse>
}
