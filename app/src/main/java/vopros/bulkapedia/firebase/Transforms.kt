package vopros.bulkapedia.firebase

import com.google.firebase.firestore.DocumentSnapshot
import vopros.bulkapedia.category.Category
import vopros.bulkapedia.category.CategoryDTO
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.hero.HeroDTO
import vopros.bulkapedia.map.GameMap
import vopros.bulkapedia.map.GameMapDTO
import vopros.bulkapedia.user.User
import vopros.bulkapedia.user.UserDTO
import vopros.bulkapedia.userSet.UserSet
import vopros.bulkapedia.userSet.UserSetDTO

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
    UserSet(it.documentId, it.author, it.gears, it.hero, it.liked)
}

fun toUser(doc: DocumentSnapshot): User? = toObject(UserDTO::class.java, doc) {
    User(it.id, it.admin, it.email, it.nickname, it.password, it.updateEmail, it.updateNickname)
}