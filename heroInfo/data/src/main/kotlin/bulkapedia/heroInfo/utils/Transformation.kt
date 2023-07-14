package bulkapedia.heroInfo.utils

import bulkapedia.heroInfo.HeroInfo
import bulkapedia.heroInfo.HeroInfoDTO

fun HeroInfo.toDTO() = HeroInfoDTO(heroInfoId, video, hero, description)

fun HeroInfoDTO.toPOJO() = HeroInfo(heroInfoId, video, hero, description)