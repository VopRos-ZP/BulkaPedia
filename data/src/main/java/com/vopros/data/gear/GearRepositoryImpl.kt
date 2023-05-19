package com.vopros.data.gear

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.gear.Gear

class GearRepositoryImpl : RepositoryImpl<Gear>(
    Database.gears, { it.toGear() }, "Снаряжение не найдено!"
)