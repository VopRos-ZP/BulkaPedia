package com.vopros.data.hero

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.hero.Hero

class HeroRepositoryImpl : RepositoryImpl<Hero>(
    Database.heroes, { it.toHero() }, "Герой не найден!"
)