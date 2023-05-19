package com.vopros.data.map

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.map.Map

class MapRepositoryImpl : RepositoryImpl<Map>(
    Database.maps, { it.toMap() }, "Карта не найдена!"
)