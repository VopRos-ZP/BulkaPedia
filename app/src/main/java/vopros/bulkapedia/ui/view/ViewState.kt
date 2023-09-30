package vopros.bulkapedia.ui.view

open class ViewState<T> {
    val loading: ViewState<T> = this
    open var success: T = TODO()
}