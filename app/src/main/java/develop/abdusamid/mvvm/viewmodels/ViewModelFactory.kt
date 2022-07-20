package develop.abdusamid.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import develop.abdusamid.mvvm.repository.MyRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val myRepository: MyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(myRepository) as T
        }
        throw IllegalArgumentException("Error")
    }
}