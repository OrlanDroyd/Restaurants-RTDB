package com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.RestaurantsRepository

class ToggleRestaurantUseCase {

    private val repository: RestaurantsRepository =
        RestaurantsRepository()

    private val getSortedRestaurantsUseCase =
        GetSortedRestaurantsUseCase()

    suspend operator fun invoke(
        id: Int,
        oldValue: Boolean
    ): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id, newFav)
        return getSortedRestaurantsUseCase()
    }
}