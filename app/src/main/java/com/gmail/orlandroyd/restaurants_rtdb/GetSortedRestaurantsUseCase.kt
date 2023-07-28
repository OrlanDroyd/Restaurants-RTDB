package com.gmail.orlandroyd.restaurants_rtdb

class GetSortedRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getAllRestaurants().sortedBy { it.title }
    }
}