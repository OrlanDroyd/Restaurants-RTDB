package com.gmail.orlandroyd.restaurants_rtdb.presentation.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.DummyContent
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.presentation.Description
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.presentation.list.RestaurantsScreen
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.presentation.list.RestaurantsScreenState
import com.gmail.orlandroyd.restaurants_rtdb.ui.theme.RestaurantsRTDBTheme
import org.junit.Rule
import org.junit.Test

class RestaurantsScreenTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun initialState_isRendered() {
        testRule.setContent {
            RestaurantsRTDBTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = emptyList(),
                        isLoading = true
                    ),
                    onFavoriteClick =
                    { _: Int, _: Boolean -> },
                    onItemClick = { })
            }
        }
        testRule.onNodeWithContentDescription(
            Description.RESTAURANTS_LOADING
        ).assertIsDisplayed()
    }

    @Test
    fun stateWithContent_isRendered() {

        val restaurants = DummyContent.getDomainRestaurants()

        testRule.setContent {
            RestaurantsRTDBTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = restaurants,
                        isLoading = false
                    ),
                    onFavoriteClick =
                    { _: Int, _: Boolean -> },
                    onItemClick = { })
            }
        }

        testRule.onNodeWithText(
            restaurants[0].title
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            restaurants[0].description
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.RESTAURANTS_LOADING
        ).assertDoesNotExist()
    }

    @Test
    fun stateWithError_isRendered() {

        val errorMsg = "UNKNOWN ERROR"

        testRule.setContent {
            RestaurantsRTDBTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = emptyList(),
                        isLoading = false,
                        error = errorMsg
                    ),
                    onFavoriteClick =
                    { _: Int, _: Boolean -> },
                    onItemClick = { })
            }
        }

        testRule.onNodeWithText(
            errorMsg
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.RESTAURANTS_LOADING
        ).assertDoesNotExist()
    }

    @Test
    fun stateWithContent_ClickOnItem_isRegistered() {

        val restaurants = DummyContent.getDomainRestaurants()
        val targetRestaurant = restaurants[0]

        testRule.setContent {
            RestaurantsRTDBTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = restaurants,
                        isLoading = false
                    ),
                    onFavoriteClick = { _, _ -> },
                    onItemClick = { id ->
                        assert(id == targetRestaurant.id)
                    })
            }
        }

        testRule.onNodeWithText(targetRestaurant.title).performClick()

    }

}