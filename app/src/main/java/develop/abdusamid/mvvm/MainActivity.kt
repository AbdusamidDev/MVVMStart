package develop.abdusamid.mvvm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import develop.abdusamid.mvvm.databinding.ActivityMainBinding
import develop.abdusamid.mvvm.models.MyData
import develop.abdusamid.mvvm.repository.MyRepository
import develop.abdusamid.mvvm.utils.Status
import develop.abdusamid.mvvm.viewmodels.MyViewModel
import develop.abdusamid.mvvm.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData = MyData()
        val myDataRepository = MyRepository(myData)
        myViewModel = ViewModelProvider(
            this,
            ViewModelFactory(myDataRepository)
        )[MyViewModel::class.java]

        loadTv()
        loadEdt()
    }

    private fun loadEdt() {
        binding.edtIndex.addTextChangedListener {
            myViewModel.randomString(it.toString())
        }
    }

    private fun loadTv() {

        myViewModel.getLiveData().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvInfo.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.text = it.data
                    binding.tvInfo.setTextColor(Color.BLACK)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.text = it.message
                    binding.tvInfo.setTextColor(Color.RED)
                }
            }
        }
    }
}