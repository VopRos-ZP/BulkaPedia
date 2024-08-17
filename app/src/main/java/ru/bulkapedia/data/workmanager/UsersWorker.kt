package ru.bulkapedia.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.data.room.user.UserDto
import ru.bulkapedia.data.room.user.fromDto
import ru.bulkapedia.domain.usecase.user.UpsertUsersUseCase

class UsersWorker(
    context: Context,
    params: WorkerParameters,
    private val supabaseWrapper: SupabaseWrapper,
    private val upsertUsersUseCase: UpsertUsersUseCase,
) : CoroutineWorker(context, params) {

    @OptIn(SupabaseExperimental::class)
    override suspend fun doWork(): Result {
        supabaseWrapper.postgres.from("users").selectAsFlow(UserDto::id)
            .distinctUntilChanged()
            .map { it.map(UserDto::fromDto) }
            .collect { upsertUsersUseCase(it) }

        return Result.success()
    }

}
