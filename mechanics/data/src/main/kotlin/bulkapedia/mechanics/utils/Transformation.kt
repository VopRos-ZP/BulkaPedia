package bulkapedia.mechanics.utils

import bulkapedia.mechanics.Mechanic
import bulkapedia.mechanics.MechanicDTO

fun Mechanic.toDTO() = MechanicDTO(mechanicId, icon, title, description, video)

fun MechanicDTO.toPOJO() = Mechanic(mechanicId, icon, title, description, video)