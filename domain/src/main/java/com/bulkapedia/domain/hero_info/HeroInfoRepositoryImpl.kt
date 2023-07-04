package com.bulkapedia.domain.hero_info

import com.bulkapedia.data.Repository
import com.bulkapedia.data.hero_info.HeroInfo
import com.bulkapedia.data.hero_info.HeroInfo.Companion.toData
import com.bulkapedia.data.hero_info.HeroInfo.Companion.toHeroInfo
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class HeroInfoRepositoryImpl : RepositoryImpl<HeroInfo>(
    Refs.Store.heroInfo,
    { it.toHeroInfo() },
    { it.heroInfoId },
    { it.toData() }
)

typealias HeroInfoRepository = Repository<HeroInfo>