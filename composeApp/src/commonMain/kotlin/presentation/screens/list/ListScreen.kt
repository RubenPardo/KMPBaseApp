package presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import base.presentation.screens.BaseScreen
import presentation.screens.list.ListContract.Action
import presentation.screens.list.ListContract.Effect
import presentation.screens.list.ListContract.State


class  ListScreen(
    override val viewModel: ListViewModel,
) : BaseScreen<State, Action, Effect, ListViewModel>() {


    @Composable
    override fun Content(state: State) {
        when {
            state.isLoading -> BuildLoading()
            state.isError -> BuildError()
            else -> BuildContent(state)
        }

    }

    @Composable
    override fun TopBar(state: State) {
        TopAppBar(title = {
            Text(
                text = "List Items"
            )
        })
    }

    @Composable
    fun BuildContent(state: State) {
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.data) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        /* TODO add coil multiplatform
                        Image(
                            painter = rememberAsyncImagePainter(it.urlImage),
                            contentDescription = it.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(128.dp)
                        )*/
                        Box(modifier = Modifier.width(16.dp))
                        Text(text = it.name)
                    }
                }
            }
        }
    }

    @Composable
    fun BuildLoading() {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }

    @Composable
    fun BuildError() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = ":(")
            Box(modifier = Modifier.height(16.dp))
            Button(onClick = { sendAction(Action.Initialize) }) {
                Text(text = "Try again!")
            }
        }
    }

    override fun handleEffect(effect: Effect) {
        // no-op
    }
}