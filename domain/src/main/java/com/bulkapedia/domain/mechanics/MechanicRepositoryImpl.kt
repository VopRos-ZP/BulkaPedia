package com.bulkapedia.domain.mechanics

import com.bulkapedia.data.Repository
import com.bulkapedia.data.mechanics.Mechanic
import com.bulkapedia.data.mechanics.Mechanic.Companion.toData
import com.bulkapedia.data.mechanics.Mechanic.Companion.toMechanic
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class MechanicRepositoryImpl : RepositoryImpl<Mechanic>(
    Refs.Store.mechanics,
    { it.toMechanic() },
    { it.mechanicId },
    { it.toData() }
)

typealias MechanicRepository = Repository<Mechanic>