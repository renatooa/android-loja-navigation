package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.NaveGraghDirections
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment() : Fragment() {

    private val controlador: NavController by lazy {
        findNavController()
    }

    private val estadoViewModel: EstadoAppViewModel by sharedViewModel()

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais(appBar = true)
        login_botao_logar.setOnClickListener {
            viewModel.logar()
            exibirListaProdutos()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_login, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_login_usuario_add) {
            exibirTelaCadastroUsuario()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun exibirTelaCadastroUsuario() {
        val direcao =
            LoginFragmentDirections.actionNaveLoginToNaveCadastroUsuaio()

        controlador.navigate(direcao)
    }

    private fun exibirListaProdutos() {
        val direcao =
            LoginFragmentDirections.actionLoginFragmentToNaveListaProduto()
        controlador.navigate(direcao)
    }
}