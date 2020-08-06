package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ListaPagamentosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.PagamentoViewModel
import kotlinx.android.synthetic.main.lista_pagamentos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListaPagamentoFragment : BaseFragment(R.layout.lista_pagamentos) {

    private val adapterListaPagamentos: ListaPagamentosAdapter by inject()

    private val viewModelPagamento: PagamentoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoViewModel.temComponentes = ComponentesVisuais(appBar = true, bottonNavegation = true)
        lista_pagamentos_recyclerview.adapter = adapterListaPagamentos
        buscaPagamentos()
    }

    private fun buscaPagamentos() {
        viewModelPagamento.todos().observe(viewLifecycleOwner, Observer {
            it?.let { pagamentosRealizados ->
                adapterListaPagamentos.add(pagamentosRealizados)
            }
        })
    }
}

