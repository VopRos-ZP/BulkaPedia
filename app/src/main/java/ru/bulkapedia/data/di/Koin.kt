package ru.bulkapedia.data.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.category.GetAllCategoryUseCase
import ru.bulkapedia.presentation.screens.categories.CategoriesViewModel

val supabaseModule = module {
    singleOf(::SupabaseWrapper)

    /** UseCases **/
    singleOf(::GetAllCategoryUseCase)

    /** ViewModels **/
    viewModelOf(::CategoriesViewModel)
}
