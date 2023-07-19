package com.example.pizza_time_compose.screen

data class OrderUiState(
    val pizzaBreads: List<PizzaUiState> = emptyList(),
    val currentPage: Int = 0,
)

data class PizzaUiState(
    val id: Int = 0,
    val defaultSize: Float = 200f,
    val image: Int = 0,
    val pizzaIngredients: List<IngredientsPizzaTypeUiState> = emptyList()
)

data class IngredientsPizzaTypeUiState(
    val id: Int = 0,
    val icon: Int = 0,
    val image: Int = 0,
    val isSelected: Boolean = false
)
