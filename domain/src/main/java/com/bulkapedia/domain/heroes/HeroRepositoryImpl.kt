package com.bulkapedia.domain.heroes

import com.bulkapedia.data.Repository
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.heroes.Hero.Companion.toHero
import com.bulkapedia.data.heroes.Hero.Companion.toData
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class HeroRepositoryImpl : RepositoryImpl<Hero>(
    Refs.Store.heroes, { it.toHero() }, { it.heroId }, { it.toData() }
)

typealias HeroRepository = Repository<Hero>