package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.NaveGraghDirections
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment(private val idView: Int) : Fragment() {

    protected val loginViewModel: LoginViewModel by viewModel()

    protected val controlador: NavController by lazy {
        findNavController()
    }

    protected val estadoViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(idView, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        verificarAcaoDeslogado()
    }

    private fun verificarAcaoDeslogado() {
        if (!loginViewModel.isLogado()) {
            exibirTelaLogin()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bsse, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_base_deslogar) {
            loginViewModel.deslogar()

            exibirTelaLogin()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun exibirTelaLogin() {
        val direcao =
            NaveGraghDirections.actionGlobalNaveLogin()

        controlador.navigate(direcao)
    }
}