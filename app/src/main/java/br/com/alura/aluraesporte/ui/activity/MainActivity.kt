package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val COMPRA_REALIZADA = "Compra realizada"

class ProdutosActivity : AppCompatActivity() {

    private val controlador by lazy {
        findNavController(R.id.main_activity_nav_host)
    }

    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        configurarNavigation()

        main_activity_bottom_nave.setupWithNavController(controlador)
    }

    private fun configurarNavigation() {
        controlador.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label

            viewModel.componentes.observe(this, Observer {
                it?.let { componentes ->
                    if (componentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }

                    if (componentes.bottonNavegation){
                        main_activity_bottom_nave.visibility = View.VISIBLE
                    }else{
                        main_activity_bottom_nave.visibility = View.GONE
                    }
                }
            })
        }
    }
}
