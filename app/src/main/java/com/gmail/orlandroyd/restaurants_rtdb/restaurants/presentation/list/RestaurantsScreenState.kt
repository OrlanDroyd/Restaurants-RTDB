package com.gmail.orlandroyd.restaurants_rtdb.restaurants.presentation.list

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)