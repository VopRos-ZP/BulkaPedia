package bulkapedia

typealias SuccessFunc <T> = (T) -> Unit
typealias ErrorFunc = (String) -> Unit

data class Callback<T>(
    val onSuccess: SuccessFunc<T>,
    val onError: ErrorFunc? = null
)