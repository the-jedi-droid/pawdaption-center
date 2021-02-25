/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.pets.data.Dog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        AppNavigator()
    }
}

@Composable
fun AppNavigator() {
    val navigator = rememberNavController()

    NavHost(navController = navigator, startDestination = "home") {
        composable("home") { HomeScreen(com.example.pets.data.Pets.dogs, navigator) }
        composable(
            "details/{dog}",
            arguments = listOf(
                navArgument("dog") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("dog")
                ?.let { DetailScreen(dogName = it, navHostController = navigator) }
        }
    }
}

@Composable
fun HomeScreen(dogs: List<Dog>, navHostController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "🐶 Welcome to PAWDOPTION CENTER", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        PetList(pets = dogs, navHostController = navHostController)
    }
}

@Composable
fun DetailScreen(dogName: String, navHostController: NavHostController) {

    val dog = com.example.pets.data.Pets.dogs.find { it.name == dogName }

    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = dog!!.resId),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(text = dog.name, style = MaterialTheme.typography.h6)
        Text(text = dog.description, style = MaterialTheme.typography.body1, color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Adopt 🎉")
        }
    }
}

@Composable
fun PetList(pets: List<Dog>, modifier: Modifier = Modifier, navHostController: NavHostController) {
    LazyColumn(modifier = modifier) {
        items(items = pets) { pet ->
            PetItem(pet, navHostController = navHostController)
        }
    }
}

@Composable
fun PetItem(pet: Dog, modifier: Modifier = Modifier, navHostController: NavHostController) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navHostController.navigate("details/${pet.name}") }
    ) {
        Column(modifier = modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = pet.resId),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(8.dp))
            Text(text = pet.name, style = MaterialTheme.typography.h6)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
