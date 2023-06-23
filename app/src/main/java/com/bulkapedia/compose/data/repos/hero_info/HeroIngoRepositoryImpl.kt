package com.bulkapedia.compose.data.repos.hero_info

import com.bulkapedia.compose.data.repos.hero_info.HeroInfo.Companion.toHeroInfo
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class HeroIngoRepositoryImpl : FirestoreRepository<HeroInfo>(FirestoreDB.heroInfo, { it.toHeroInfo() })

typealias HeroInfoRepository = Repository<HeroInfo>