package com.bulkapedia.domain.mains

import com.bulkapedia.data.Repository
import com.bulkapedia.data.mains.Main
import com.bulkapedia.data.mains.Main.Companion.toData
import com.bulkapedia.data.mains.Main.Companion.toMain
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class MainRepositoryImpl : RepositoryImpl<Main>(
    Refs.Store.mains,
    { it.toMain() },
    { it.mainId },
    { it.toData() }
)

typealias MainRepository = Repository<Main>