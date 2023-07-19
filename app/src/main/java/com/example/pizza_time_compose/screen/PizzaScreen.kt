package com.example.pizza_time_compose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizza_time_compose.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreen(
    viewModel: PizzaViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()
    PizzaContent(state, pagerState, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaContent(
    state: OrderUiState,
    pagerState: PagerState,
    orderInteraction: PizzaInteractionListener,
) {
    Column(Modifier.fillMaxSize()) {
        CustomAppBar(Modifier.padding(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.Center
        ) {

            Plate(modifier = Modifier.size(256.dp))

            PizzaSlider(
                state = state,
                pagerState = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Box {
                    Image(
                        modifier = Modifier.size(state.pizzaBreads[page].defaultSize.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = state.pizzaBreads[page].image),
                        contentDescription = "pizza"
                    )
                }

                state.pizzaBreads[pagerState.currentPage].pizzaIngredients.forEach {
                    IngredientsPizzaAnimation(it)
                }
            }
        }

        SpacerVertical(8)

        Text(
            text = "17$",
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        SpacerVertical(16)

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = { orderInteraction.onChangePizzaSize(pagerState.currentPage, 160f) }
            ) {
                Text(
                    text = "S", modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp),
                    color = Color.Black
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = { orderInteraction.onChangePizzaSize(pagerState.currentPage, 200f) }
            ) {
                Text(
                    text = "M", modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp),
                    color = Color.Black
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = { orderInteraction.onChangePizzaSize(pagerState.currentPage, 230f) }
            ) {
                Text(
                    text = "L", modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp),
                    color = Color.Black
                )
            }
        }

        SpacerVertical(16)

        Text(
            text = "CUSTOMIZ YOUR PIZZA",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        SpacerVertical(16)
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.pizzaBreads[pagerState.currentPage].pizzaIngredients) { index: Int, item: IngredientsPizzaTypeUiState ->
                PizzaIngredients(state = item,
                    isSelected = item.isSelected,
                    onClick = {
                        orderInteraction.onIngredientsPizzaClick(
                            index,
                            pagerState.currentPage
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFF443533)),
            shape = RoundedCornerShape(24),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "cart",
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add to Cart",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }

    }
}

@Composable
fun CustomAppBar(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back arrow"
        )
        Text(text = "Pizza")
        Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "favorite")
    }
}

@Composable
fun Plate(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.plate), contentDescription = "plate"
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaSlider(
    modifier: Modifier = Modifier,
    state: OrderUiState,
    pagerState: PagerState,
    content: @Composable (page: Int) -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        pageCount = state.pizzaBreads.size,
        state = pagerState,
        contentPadding = PaddingValues(start = 32.dp, end = 32.dp),
        pageSpacing = 16.dp
    ) { page ->
        content(page)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IngredientsPizzaAnimation(state: IngredientsPizzaTypeUiState) {
    AnimatedVisibility(
        visible = state.isSelected,
        enter = scaleIn(initialScale = 3f) + fadeIn(),
        exit = fadeOut()
    ) {
        Image(
            painter = painterResource(id = state.image),
            contentDescription = "plate",
        )
    }
}

@Composable
private fun PizzaIngredients(
    state: IngredientsPizzaTypeUiState,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)
        .clickable { onClick() }
        .background(if (isSelected) Color.Green.copy(alpha = 0.1f) else Color.Transparent)
        .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = state.icon),
            contentDescription = "ingredients"
        )
    }
}

@Composable
fun SpacerVertical(space: Int) {
    Spacer(modifier = Modifier.height(space.dp))
}