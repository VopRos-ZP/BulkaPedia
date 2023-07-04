package com.bulkapedia.data.mechanics

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Mechanic(
    val mechanicId: String,
    val title: String,
    val video: String,
    val description: String,
    val icon: String
) {
     companion object {

         fun DocumentSnapshot.toMechanic(): Mechanic? {
             return docToObject(MechanicDTO::class.java) { dto ->
                 Mechanic(id, dto.title, dto.video, dto.description, dto.icon)
             }
         }

         fun Mechanic.toData(): Map<String, Any> = asMap().mapValues { it }

     }

}
