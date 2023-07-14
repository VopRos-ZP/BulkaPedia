package bulkapedia.heroes.utils

import bulkapedia.heroes.Hero
import bulkapedia.heroes.HeroDTO

fun Hero.toDTO() = HeroDTO(heroId, name, type, icon, difficult, stats, counterpicks, personalGears)

fun HeroDTO.toPOJO() = Hero(heroId, name, type, icon, difficult, stats, counterpicks, personalGears)