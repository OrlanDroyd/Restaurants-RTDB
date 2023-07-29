package com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class GetInitialRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val getSortedRestaurantsUseCase:
    GetSortedRestaurantsUseCase
) {

    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}