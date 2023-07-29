package com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.RestaurantsRepository

class GetInitialRestaurantsUseCase {

    private val repository: RestaurantsRepository = RestaurantsRepository()

    private val getSortedRestaurantsUseCase =
        GetSortedRestaurantsUseCase()

    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}