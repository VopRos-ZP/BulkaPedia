package com.vopros.data.mechanic

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.mechanics.Mechanic

class MechanicRepositoryImpl : RepositoryImpl<Mechanic>(
    Database.mechanics, { it.toMechanic() }, "Механика не найдена!"
)