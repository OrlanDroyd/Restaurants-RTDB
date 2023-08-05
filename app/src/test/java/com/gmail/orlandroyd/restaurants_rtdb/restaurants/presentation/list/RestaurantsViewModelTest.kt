package com.gmail.orlandroyd.restaurants_rtdb.restaurants.presentation.list

import com.gmail.orlandroyd.restaurants_rtdb.restaurants.DummyContent
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.RestaurantsRepository
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.local.FakeRoomDao
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.data.remote.FakeApiService
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain.GetInitialRestaurantsUseCase
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain.GetSortedRestaurantsUseCase
import com.gmail.orlandroyd.restaurants_rtdb.restaurants.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantsViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private fun getViewModel(isErrorState: Boolean = false): RestaurantsViewModel {
        val restaurantsRepository =
            RestaurantsRepository(
                FakeApiService(isErrorState),
                FakeRoomDao(),
                dispatcher
            )
        val getSortedRestaurantsUseCase =
            GetSortedRestaurantsUseCase(restaurantsRepository)
        val getInitialRestaurantsUseCase =
            GetInitialRestaurantsUseCase(
                restaurantsRepository,
                getSortedRestaurantsUseCase
            )
        val toggleRestaurantUseCase =
            ToggleRestaurantUseCase(
                restaurantsRepository,
                getSortedRestaurantsUseCase
            )
        return RestaurantsViewModel(
            getInitialRestaurantsUseCase,
            toggleRestaurantUseCase,
            dispatcher
        )
    }

    @Test
    fun initialState_isProduced() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value
        assert(
            initialState == RestaurantsScreenState(
                restaurants = emptyList(),
                isLoading = true,
                error = null
            )
        )
    }

    @Test
    fun stateWithContent_isProduced() = scope.runTest {
        val testVM = getViewModel()
        advanceUntilIdle()
        val currentState = testVM.state.value
        assert(
            currentState == RestaurantsScreenState(
                restaurants =
                DummyContent.getDomainRestaurants(),
                isLoading = false,
                error = null
            )
        )
    }

    @Test
    fun stateWithError_isProduced() = scope.runTest {
        val testVM = getViewModel(isErrorState = true)
        advanceUntilIdle()
        val currentState = testVM.state.value
        assert(
            currentState == RestaurantsScreenState(
                restaurants = emptyList(),
                isLoading = false,
                error = "UNKNOWN ERROR"
            )
        )
    }

}