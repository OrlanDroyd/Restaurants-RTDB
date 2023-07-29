package com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.RestaurantsRepository

class GetSortedRestaurantsUseCase {

    private val repository: RestaurantsRepository =
        RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}