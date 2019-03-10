package se.sugarest.testdaggar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

const val LOVE = "Love"
const val HELLO = "Hello"

class MainActivity : AppCompatActivity() {

    @Inject
    @field:Named(LOVE)
    lateinit var infoLove: Info
    @Inject
    @field:Named(HELLO)
    lateinit var infoHello: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBox.create().poke(this)
        text_view.text = "${infoLove.text} ${infoHello.text}"
    }
}

@Module
class Bag {
    @Provides
    @Named(LOVE)
    fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }

    @Provides
    @Named(HELLO)
    fun sayHelloDagger2(): Info {
        return Info("Hello Dagger 2")
    }
}

class Info(val text: String)

@Component(modules = [Bag::class])
interface MagicBox {
    fun poke(app: MainActivity)
}