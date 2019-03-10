package se.sugarest.testdaggar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var info: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBox.create().poke(this)
        text_view.text = info.text
    }
}

@Component
interface MagicBox {
    fun poke(app: MainActivity)
}

class Info @Inject constructor() {
    val text = "Hello Dagger 2"
}