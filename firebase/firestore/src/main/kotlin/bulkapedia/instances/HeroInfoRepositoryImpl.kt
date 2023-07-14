package bulkapedia.instances

import bulkapedia.heroInfo.HeroInfo
import bulkapedia.heroInfo.HeroInfoDTO
import bulkapedia.heroInfo.utils.toDTO
import bulkapedia.heroInfo.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class HeroInfoRepositoryImpl(ref: CollectionReference): RepositoryImpl<HeroInfo>(
    ref, { it.toObject(HeroInfoDTO::class.java)?.toPOJO() },
    { it.heroInfoId }, { it.toDTO() }
)