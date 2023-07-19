package com.example.pizza_time_compose

import com.example.pizza_time_compose.screen.IngredientsPizzaTypeUiState
import com.example.pizza_time_compose.screen.PizzaUiState

object PizzaData {
    fun pizza() = listOf(
        PizzaUiState(id = 0, image = R.drawable.bread_1),
        PizzaUiState(id = 1, image = R.drawable.bread_2),
        PizzaUiState(id = 2, image = R.drawable.bread_3),
        PizzaUiState(id = 3, image = R.drawable.bread_4),
        PizzaUiState(id = 4, image = R.drawable.bread_5)
    )

    fun pizzaIngredients() = listOf(
        IngredientsPizzaTypeUiState(
            id = 0,
            icon = R.drawable.basil_1,
            image = R.drawable.image_basil,
            isSelected = false
        ),
        IngredientsPizzaTypeUiState(
            id = 1,
            icon = R.drawable.onion_1,
            image = R.drawable.image_onion,
            isSelected = false
        ),
        IngredientsPizzaTypeUiState(
            id = 2,
            icon = R.drawable.mushroom_1,
            image = R.drawable.image_mushroom,
            isSelected = false
        ),
        IngredientsPizzaTypeUiState(
            id = 3,
            icon = R.drawable.broccoli_1,
            image = R.drawable.image_broccoli,
            isSelected = false
        ),
        IngredientsPizzaTypeUiState(
            id = 4,
            icon = R.drawable.sausage_1,
            image = R.drawable.image_sausage,
            isSelected = false
        ),
    )
}