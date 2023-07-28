package com.gmail.orlandroyd.restaurants_rtdb

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)