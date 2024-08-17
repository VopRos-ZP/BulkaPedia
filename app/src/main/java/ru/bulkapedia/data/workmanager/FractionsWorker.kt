package ru.bulkapedia.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.data.room.heroes.FractionDto
import ru.bulkapedia.domain.usecase.UpsertFractionUseCase

class FractionsWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val supabaseWrapper: SupabaseWrapper,
    private val upsertFractionUseCase: UpsertFractionUseCase,
) : CoroutineWorker(context, workerParameters) {

    @OptIn(SupabaseExperimental::class)
    override suspend fun doWork(): Result {
        supabaseWrapper.postgres.from("fraction_images").selectAsFlow(FractionDto::id)
            .distinctUntilChanged()
            .collect { upsertFractionUseCase(it) }

        return Result.success()
    }
}