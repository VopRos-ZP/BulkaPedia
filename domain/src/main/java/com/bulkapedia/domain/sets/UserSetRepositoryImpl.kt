package com.bulkapedia.domain.sets

import com.bulkapedia.data.Repository
import bulkapedia.sets.UserSet
import bulkapedia.sets.UserSet.Companion.toData
import bulkapedia.sets.UserSet.Companion.toUserSet
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class UserSetRepositoryImpl : RepositoryImpl<UserSet>(
    Refs.Store.sets,
    { it.toUserSet() },
    { it.setId },
    { it.toData() }
)

typealias UserSetRepository = Repository<UserSet>