package com.bulkapedia.domain.mains

import com.bulkapedia.data.Repository
import bulkapedia.mains.Main
import bulkapedia.mains.Main.Companion.toData
import bulkapedia.mains.Main.Companion.toMain
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class MainRepositoryImpl : RepositoryImpl<Main>(
    Refs.Store.mains,
    { it.toMain() },
    { it.mainId },
    { it.toData() }
)

typealias MainRepository = Repository<Main>