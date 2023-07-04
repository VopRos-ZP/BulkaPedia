package com.bulkapedia.domain.sets

import com.bulkapedia.data.Repository
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.data.sets.UserSet.Companion.toData
import com.bulkapedia.data.sets.UserSet.Companion.toUserSet
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class UserSetRepositoryImpl : RepositoryImpl<UserSet>(
    Refs.Store.sets,
    { it.toUserSet() },
    { it.userSetId },
    { it.toData() }
)

typealias UserSetRepository = Repository<UserSet>