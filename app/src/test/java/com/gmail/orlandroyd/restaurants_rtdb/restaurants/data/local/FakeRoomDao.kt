package com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.local

import kotlinx.coroutines.delay

class FakeRoomDao : RestaurantsDao {

    private var restaurants =
        HashMap<Int, LocalRestaurant>()

    override suspend fun getAll(): List<LocalRestaurant> {
        delay(1000)
        return restaurants.values.toList()
    }

    override suspend fun addAll(restaurants: List<LocalRestaurant>) {
        restaurants.forEach {
            this.restaurants[it.id] = it
        }
    }

    override suspend fun update(partialRestaurant: PartialLocalRestaurant) {
        delay(1000)
        updateRestaurant(partialRestaurant)
    }

    override suspend fun updateAll(partialRestaurants: List<PartialLocalRestaurant>) {
        delay(1000)
        partialRestaurants.forEach {
            updateRestaurant(it)
        }
    }

    override suspend fun getAllFavorited(): List<LocalRestaurant> {
        return restaurants.values.toList()
            .filter { it.isFavorite }
    }

    private fun updateRestaurant(partialRestaurant: PartialLocalRestaurant) {
        val restaurant = this.restaurants[partialRestaurant.id]
        restaurant?.let {
            this.restaurants[partialRestaurant.id] = it.copy(
                isFavorite = partialRestaurant.isFavorite
            )
        }
    }
}