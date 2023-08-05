package com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.remote

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.DummyContent
import kotlinx.coroutines.delay

class FakeApiService(private val isErrorState: Boolean = false) : RestaurantsApiService {
    override suspend fun getRestaurants(): List<RemoteRestaurant> {
        delay(1000)
        return if (isErrorState) {
            throw Exception("UNKNOWN ERROR")
        } else {
            DummyContent.getRemoteRestaurants()
        }
    }

    override suspend fun getRestaurant(id: Int): Map<String, RemoteRestaurant> {
        TODO("Not yet implemented")
    }
}