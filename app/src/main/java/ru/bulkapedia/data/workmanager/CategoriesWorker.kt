package ru.bulkapedia.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.core.component.KoinComponent
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.usecase.category.UpsertCategoryUseCase
import ru.bulkapedia.domain.model.Category

class CategoriesWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val supabaseWrapper: SupabaseWrapper,
    private val upsertCategoryUseCase: UpsertCategoryUseCase,
) : CoroutineWorker(context, workerParameters), KoinComponent {

    @OptIn(SupabaseExperimental::class)
    override suspend fun doWork(): Result {
        supabaseWrapper.postgres.from("categories")
            .selectAsFlow(Category::id)
            .distinctUntilChanged()
            .collect { upsertCategoryUseCase(it) }

        return Result.success()
    }

}