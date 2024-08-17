package ru.bulkapedia.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.data.room.heroes.HeroDto
import ru.bulkapedia.domain.usecase.UpsertHeroesUseCase

class HeroesWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val supabaseWrapper: SupabaseWrapper,
    private val upsertHeroesUseCase: UpsertHeroesUseCase,
) : CoroutineWorker(context, workerParameters) {

    @OptIn(SupabaseExperimental::class)
    override suspend fun doWork(): Result {
        supabaseWrapper.postgres.from("heroes").selectAsFlow(HeroDto::id)
            .distinctUntilChanged()
            .collect { upsertHeroesUseCase(it) }

        return Result.success()
    }

}