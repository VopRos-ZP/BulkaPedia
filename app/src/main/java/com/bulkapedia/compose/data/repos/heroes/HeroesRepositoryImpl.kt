package com.bulkapedia.compose.data.repos.heroes

import com.bulkapedia.compose.data.repos.heroes.Hero.Companion.toHero
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class HeroesRepositoryImpl : FirestoreRepository<Hero>(FirestoreDB.heroes, { it.toHero() })

typealias HeroesRepository = Repository<Hero>