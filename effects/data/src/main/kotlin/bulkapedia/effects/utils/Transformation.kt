package bulkapedia.effects.utils

import bulkapedia.effects.PersonalEffect
import bulkapedia.effects.PersonalEffectDTO

fun PersonalEffect.toDTO() = PersonalEffectDTO(personalId, personalTwo, personalFour, personalSix)

fun PersonalEffectDTO.toPOJO() = PersonalEffect(personalId, personalTwo, personalFour, personalSix)