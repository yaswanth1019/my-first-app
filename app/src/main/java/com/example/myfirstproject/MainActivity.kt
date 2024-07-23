package com.example.myfirstproject


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstproject.dataSource.avengers
import com.example.myfirstproject.model.Avengers
import com.example.myfirstproject.ui.theme.MyFirstProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AvengersApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AvengersApp(modifier: Modifier=Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ){
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {paddingValues->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(avengers) {index,avenger->
                AvengersDisplay(avenger,
                    modifier=Modifier
                        .padding(10.dp)
                        .animateEnterExit(
                            enter= slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY ={it*(index+1)}
                        )
                        )
                )
            }
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier=Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Avengers",
                    style = MaterialTheme.typography.displayLarge)
            }
        },
        modifier=modifier)
}

@Composable
fun AvengersDisplay(avenger:Avengers, modifier: Modifier=Modifier) {
    var showInfo by remember {
        mutableStateOf(false)
    }

    Card (modifier=modifier){
        Column(modifier= modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )) {
            AvengerImage(avenger.nameRes,
                avenger.imageRes,
                avenger.descriptionRes,
                showInfo,
                onClick={
                   showInfo=!showInfo
                })
        }
    }

}


@Composable
fun AvengerImage(@StringRes nameRes: Int,
                 @DrawableRes imageRes: Int,
                 descriptionRes: Int,
                 showInfo:Boolean,
                 onClick:()->Unit)
{
    Text(text = stringResource(id = nameRes),
        modifier = Modifier.padding(10.dp),
        fontSize = 25.sp
    )

    Image(painter = painterResource(id = imageRes),
        contentDescription =null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
    )

    if(showInfo){
        AvengerDescription(descriptionRes)
    }
}

@Composable
fun AvengerDescription(@StringRes descriptionRes: Int){
    Text(text = stringResource(id = descriptionRes),
        style = MaterialTheme.typography.labelSmall,
        modifier=Modifier.padding(top=13.dp))
}