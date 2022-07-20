package develop.abdusamid.mvvm.models

class MyData {
    private val list = arrayOf("You", "are", "so", "smart")
    suspend fun getData(index: Int): String = list[index]
    suspend fun getSize() = list.size
}