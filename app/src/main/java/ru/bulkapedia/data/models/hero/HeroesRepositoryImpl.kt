package ru.bulkapedia.data.models.hero

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.repository.HeroesRepository
import ru.bulkapedia.domain.utils.Table
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(): HeroesRepository {

    private val collection = Firebase.firestore.collection(Table.HEROES)

    override val heroes: Flow<List<Hero>> = collection
        .snapshots()
        .map { it.toObjects(HeroDto::class.java).map(HeroDto::toHero) }

}