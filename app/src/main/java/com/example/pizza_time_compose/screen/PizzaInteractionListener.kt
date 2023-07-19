package com.example.pizza_time_compose.screen

interface PizzaInteractionListener {

    fun onChangePizzaSize(position: Int, size: Float)

    fun onIngredientsPizzaClick(ingredientPosition: Int, pizzaPosition: Int)
}