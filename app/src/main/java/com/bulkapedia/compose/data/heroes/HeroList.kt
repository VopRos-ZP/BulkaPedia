package com.bulkapedia.compose.data.heroes

import com.bulkapedia.compose.data.heroes.Hero.Companion.toHero
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HeroList {
    companion object {
        private var SHORTGUNS = listOf<Hero>()
        private var SCOUTS = listOf<Hero>()
        private var SNIPERS = listOf<Hero>()
        private var TANKS = listOf<Hero>()
        private var TROOPERS = listOf<Hero>()
        var HEROES = listOf<Hero>()

        fun getHeroes(function: (List<Hero>, List<Hero>, List<Hero>,
                                 List<Hero>, List<Hero>, List<Hero>) -> Unit) {
            getAllHeroes(function)
        }

        fun getHeroes(function: (List<Hero>) -> Unit) {
            getAllHeroes { heroes, _, _, _, _, _ -> function.invoke(heroes) }
        }

        private fun getAllHeroes(func: (List<Hero>, List<Hero>, List<Hero>,
                                        List<Hero>, List<Hero>, List<Hero>) -> Unit) {
            Firebase.firestore.collection("heroes").addSnapshotListener { value, _ ->
                if (value != null) {
                    HEROES = value.documents.mapNotNull { it.toHero() }
                    SHORTGUNS = HEROES.filter { it.type == "shortguns" }
                    SCOUTS = HEROES.filter { it.type == "scouts" }
                    SNIPERS = HEROES.filter { it.type == "snipers" }
                    TANKS = HEROES.filter { it.type == "tanks" }
                    TROOPERS = HEROES.filter { it.type == "troopers" }
                    func.invoke(HEROES, SHORTGUNS, SCOUTS, SNIPERS, TANKS, TROOPERS)
                }
            }
        }

    }
}