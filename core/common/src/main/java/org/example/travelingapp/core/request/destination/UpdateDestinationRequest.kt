package org.example.travelingapp.core.request.destination

data class UpdateDestinationRequest(
    val id: String,
    val name: String,
    val description: String,
    val country: String,
    val imageUrl: String,
    val category: String
)
