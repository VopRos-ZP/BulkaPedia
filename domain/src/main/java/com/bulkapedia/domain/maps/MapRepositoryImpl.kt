package com.bulkapedia.domain.maps

import com.bulkapedia.data.Repository
import com.bulkapedia.data.maps.Map
import com.bulkapedia.data.maps.Map.Companion.toData
import com.bulkapedia.data.maps.Map.Companion.toMap
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class MapRepositoryImpl : RepositoryImpl<Map>(
    Refs.Store.maps,
    { it.toMap() },
    { it.mapId },
    { it.toData() }
)

typealias MapRepository = Repository<Map>