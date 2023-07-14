package bulkapedia.instances

import bulkapedia.heroes.Hero
import bulkapedia.heroes.HeroDTO
import bulkapedia.heroes.utils.toDTO
import bulkapedia.heroes.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class HeroesRepositoryImpl(ref: CollectionReference): RepositoryImpl<Hero>(
    ref, { it.toObject(HeroDTO::class.java)?.toPOJO() },
    { it.heroId }, { it.toDTO() }
)