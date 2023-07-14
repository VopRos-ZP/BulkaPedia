package bulkapedia.mains.utils

import bulkapedia.mains.Main
import bulkapedia.mains.MainDTO

fun Main.toDTO() = MainDTO(mainId, kills, winrate, revives)

fun MainDTO.toPOJO() = Main(mainId, mainId.split(" ").last(), kills, winrate, revives)