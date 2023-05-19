package com.vopros.data.set

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.set.Set

class SetRepositoryImpl : RepositoryImpl<Set>(
    Database.sets, { it.toSet() }, "Сет не найден!"
)