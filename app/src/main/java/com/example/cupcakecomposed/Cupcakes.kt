package com.example.cupcakecomposed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.cupcakecomposed.model.OrderViewModel

@Composable
fun StartComposable(orderCupcake: (Int) -> Unit) {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        CupcakeImage(Modifier.align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(R.string.order_cupcakes),
            style = MaterialTheme.typography.h4
        )
        Button(
            onClick = { orderCupcake(1) },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.one_cupcake).uppercase()
            )
        }
        Button(
            onClick = { orderCupcake(6) },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.six_cupcakes).uppercase()
            )
        }
        Button(
            onClick = { orderCupcake(12) },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.twelve_cupcakes).uppercase()
            )
        }
    }
}

@Composable
fun SummaryComposable(onCancel: () -> Unit, onOrder: () -> Unit, viewModel: OrderViewModel) {

    val price: String by viewModel.price.observeAsState(initial = "")
    val quantity: Int by viewModel.quantity.observeAsState(initial = 1)
    val flavor: String by viewModel.flavor.observeAsState(initial = "")
    val pickupDate: String by viewModel.date.observeAsState(initial = "")

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = stringResource(id = R.string.quantity).uppercase(),
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = quantity.toString()
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.flavor).uppercase(),
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = flavor
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.pickup_date).uppercase(),
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = pickupDate
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.subtotal_price, price),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        OutlinedButton(
            onClick = { onCancel() },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.cancel)
            )
        }

        Button(
            onClick = { onOrder() },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.order_cupcakes)
            )
        }
    }
}

@Composable
fun PickupComposable(onCancel: () -> Unit, onNext: () -> Unit, viewModel: OrderViewModel) {

    val pickupOptions = viewModel.dateOptions
    val price: String by viewModel.price.observeAsState(initial = "")
    val currentDate: String by viewModel.date.observeAsState(initial = "")

    Column(Modifier.selectableGroup()) {
        pickupOptions.forEach { date ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (date == currentDate),
                    onClick = { viewModel.setDate(date) }
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.subtotal_price, price),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Row(Modifier.fillMaxWidth()) {

            OutlinedButton(
                onClick = { onCancel() },
                modifier = Modifier
                    .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                    .padding(
                        vertical = dimensionResource(R.dimen.margin_between_elements),
                        horizontal = 0.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.cancel).uppercase()
                )
            }

            Button(
                onClick = { onNext() },
                modifier = Modifier
                    .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                    .padding(
                        vertical = dimensionResource(R.dimen.margin_between_elements),
                        horizontal = 0.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.next).uppercase()
                )
            }
        }
    }
}

@Composable
fun FlavorComposable(onCancel: () -> Unit, onNext: () -> Unit, viewModel: OrderViewModel) {

    val flavorOptions = listOf(
        stringResource(id = R.string.vanilla),
        stringResource(id = R.string.chocolate),
        stringResource(id = R.string.red_velvet),
        stringResource(id = R.string.salted_caramel),
        stringResource(id = R.string.coffee)
    )

    //var selected by remember { mutableStateOf(flavorOptions[0]) }
    val price: String by viewModel.price.observeAsState(initial = "")
    val currentFlavor: String by viewModel.flavor.observeAsState(initial = "")

    Column(Modifier.selectableGroup()) {
        flavorOptions.forEach { flavor ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (flavor == currentFlavor),
                    onClick = { viewModel.setFlavor(flavor) }
                )
                Text(
                    text = flavor,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.subtotal_price, price),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        OutlinedButton(
            onClick = { onCancel() },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.cancel)
            )
        }

        Button(
            onClick = { onNext() },
            modifier = Modifier
                .requiredWidth(dimensionResource(id = R.dimen.order_cupcake_button_width))
                .padding(
                    vertical = dimensionResource(R.dimen.margin_between_elements),
                    horizontal = 0.dp
                )
        ) {
            Text(
                text = stringResource(R.string.next)
            )
        }
    }
}

@Composable
fun CupcakeImage(modifier: Modifier) {
    val image: Painter = painterResource(id = R.drawable.cupcake)
    Image(
        painter = image,
        contentDescription = "cupcake",
        modifier.padding(
            start = 0.dp,
            top = dimensionResource(R.dimen.margin_between_elements),
            end = 0.dp,
            bottom = 0.dp
        )
    )
}