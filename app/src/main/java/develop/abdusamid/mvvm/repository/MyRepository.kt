package develop.abdusamid.mvvm.repository

import develop.abdusamid.mvvm.models.MyData

class MyRepository(private val myData: MyData) {
    suspend fun getData(index: Int) = myData.getData(index)
    suspend fun getSize() = myData.getSize()
}