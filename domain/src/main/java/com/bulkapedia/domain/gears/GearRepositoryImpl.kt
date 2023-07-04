package com.bulkapedia.domain.gears

import com.bulkapedia.data.Repository
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.Gear.Companion.toData
import com.bulkapedia.data.gears.Gear.Companion.toGear
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class GearRepositoryImpl : RepositoryImpl<Gear>(
    Refs.Store.gears,
    { it.toGear() },
    { it.gearId },
    { it.toData() }
)

typealias GearRepository = Repository<Gear>