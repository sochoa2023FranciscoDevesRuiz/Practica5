package net.iessochoastf.practica5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.iessochoastf.practica5.databinding.FragmentListaBinding
import net.iessochoastf.practica5.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListaFragment : Fragment() {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Establecer el OnClickListener del FAB dentro de onViewCreated
        binding.fabNuevo.setOnClickListener {
            findNavController().navigate(R.id.action_editar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

