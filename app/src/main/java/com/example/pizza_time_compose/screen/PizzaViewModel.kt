package com.example.pizza_time_compose.screen

import androidx.lifecycle.ViewModel
import com.example.pizza_time_compose.PizzaData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor() : ViewModel(), PizzaInteractionListener {

    private val _state = MutableStateFlow(OrderUiState())
    val state = _state.asStateFlow()


    init {
        _state.update { it.copy(pizzaBreads = PizzaData.pizza()) }
        _state.update {
            it.copy(pizzaBreads = it.pizzaBreads.map {
                it.copy(pizzaIngredients = PizzaData.pizzaIngredients())
            })
        }
    }

    override fun onIngredientsPizzaClick(ingredientPosition: Int, pizzaPosition: Int) {
        _state.update {
            it.copy(
                it.pizzaBreads.mapIndexed { pizzaIndex, pizza ->
                    if (pizzaIndex == pizzaPosition) {
                        pizza.copy(
                            pizzaIngredients = pizza.pizzaIngredients.mapIndexed { index, ingredient ->
                                if (index == ingredientPosition) {
                                    ingredient.copy(isSelected = !ingredient.isSelected)
                                } else {
                                    ingredient.copy(isSelected = ingredient.isSelected)
                                }
                            },
                        )
                    } else {
                        pizza.copy(
                            pizzaIngredients = pizza.pizzaIngredients.mapIndexed { index, ingredient ->
                                ingredient.copy(isSelected = ingredient.isSelected)
                            },
                        )
                    }
                },
                currentPage = pizzaPosition,
            )
        }
    }

    override fun onChangePizzaSize(position: Int, size: Float) {
        val currentState = _state.value
        val updatedPizza = currentState.pizzaBreads.mapIndexed { index, pizzaUiState ->
            if (index == position) {
                pizzaUiState.copy(defaultSize = size)
            } else {
                pizzaUiState
            }
        }
        val updatedState = currentState.copy(pizzaBreads = updatedPizza)
        _state.value = updatedState
    }
}