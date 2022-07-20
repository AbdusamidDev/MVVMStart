package develop.abdusamid.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import develop.abdusamid.mvvm.repository.MyRepository
import develop.abdusamid.mvvm.utils.Resource
import kotlinx.coroutines.launch

class MyViewModel(private val myDataRepository: MyRepository) : ViewModel() {
    private val liveData = MutableLiveData<Resource<String>>()

    init {
        randomString()
    }

    fun randomString(indexString: String? = null) {
        viewModelScope.launch {
            try {
                val index: Int = indexString?.toInt()!!

                if (index >= 0 && index < myDataRepository.getSize()) {
                    liveData.postValue(Resource.success(myDataRepository.getData(index)))
                } else {
                    liveData.postValue(
                        Resource.error(
                            "Number Not Found",
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                liveData.postValue(Resource.error("Enter Number(0-3)", null))
            }
        }
    }

    fun getLiveData() = liveData
}