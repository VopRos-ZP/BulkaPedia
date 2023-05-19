package com.vopros.data.heroinfo

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.heroinfo.HeroInfo

class HeroInfoRepositoryImpl : RepositoryImpl<HeroInfo>(
    Database.heroInfo, { it.toHeroInfo() }, "Гайд на героя не найден!"
)