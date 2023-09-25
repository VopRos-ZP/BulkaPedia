package com.vopros.bulkapedia.firebase

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.bulkapedia.category.Category
import com.vopros.bulkapedia.category.CategoryDTO
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroDTO
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.map.GameMapDTO
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetDTO

/**
 * @param dto class with var params
 *
 * **/
inline fun <reified T, D> toObject(dto: Class<D>, doc: DocumentSnapshot, toPojo: (D) -> T?): T? {
    return toPojo(doc.toObject(dto)!!)
}

fun toHero(doc: DocumentSnapshot): Hero? = toObject(HeroDTO::class.java, doc) {
    Hero(it.id, it.active, it.difficult, it.image, it.type, it.counterpicks, it.stats)
}

fun toCategory(doc: DocumentSnapshot): Category? = toObject(CategoryDTO::class.java, doc) {
    Category(it.id, it.title, it.subTitle, it.enabled, it.icon, it.destination)
}

fun toGameMap(doc: DocumentSnapshot): GameMap? = toObject(GameMapDTO::class.java, doc) {
    GameMap(it.id, it.image, it.spawns, it.mode)
}

fun toUserSet(doc: DocumentSnapshot): UserSet? = toObject(UserSetDTO::class.java, doc) {
    UserSet(it.id, it.author, it.gears, it.hero, it.liked)
}