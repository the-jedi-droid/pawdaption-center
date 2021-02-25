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

package com.example.pets.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class Dog(
    @DrawableRes val resId: Int,
    val name: String,
    val description: String
)

object Pets {
    val dogs = listOf<Dog>(
        Dog(R.drawable.doge,"Tom", "These cute pups are quite versatile and can adapt to both apartment living or a home with a backyard to run in. Though their short, stout body types may lead you to believe they are lazy, these dogs will get bursts of energy and enjoy a game of fetch or a trip to the dog park."),
        Dog(R.drawable.happy,"Jack", "These make for good guard dogs, and they’re alert to strangers. The mixed breed is very energetic and athletic, so access to a safe outdoor space is preferred."),
        Dog(R.drawable.hat,"Russell", "Playful"),
        Dog(R.drawable.doge,"Stuart", "Cute"),
        Dog(R.drawable.happy,"Dan", "Aggressive"),
        Dog(R.drawable.hat,"Bill", "Playful"),
        Dog(R.drawable.doge,"Ben", "Cute"),
        Dog(R.drawable.happy,"Nathan", "Aggressive"),
        Dog(R.drawable.hat,"Johnnie", "Playful"),
        Dog(R.drawable.doge,"Frank", "Cute"),
        Dog(R.drawable.happy,"Lily", "Aggressive"),
        Dog(R.drawable.hat,"Johnnie", "Playful")
    )
}
