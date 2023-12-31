package net.iessochoastf.practica5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.iessochoastf.practica5.databinding.FragmentListaBinding
import net.iessochoastf.practica5.databinding.FragmentTareaBinding
import net.iessochoastf.practica5.R
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.graphics.Color
import com.google.android.material.snackbar.Snackbar
import android.widget.SeekBar


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TareaFragment : Fragment() {

    private var _binding: FragmentTareaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTareaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Tu lógica aquí

        iniciaSpCategoria()
        iniciaSpPrioridad()
        iniciaSwPagado()
        iniciaRgEstado()
        iniciaSbHoras()

    }


    //Añadimos el metodo

    private fun iniciaSpCategoria() {
        ArrayAdapter.createFromResource(
            //contexto suele ser la Activity
            requireContext(),
            //array de strings
            R.array.categoria,
            //layout para mostrar el elemento seleccionado
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Layout para mostrar la apariencia de la lista
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // asignamos el adaptador al spinner
            binding.spCategoria.adapter = adapter
            binding.spCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, v: View?, posicion: Int, id: Long) {

                        //recuperamos el valor
                        val valor=binding.spCategoria.getItemAtPosition(posicion)
                        //creamos el mensaje desde el recurso string parametrizado
                        val mensaje=getString(R.string.mensaje_categoria,valor)
                        //mostramos el mensaje donde "binding.root" es el ContrainLayout principal
                        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        // Aquí puedes manejar lo que pasa cuando no se selecciona nada
                    }
                }
        }
    }


    private fun iniciaSpPrioridad() {
        ArrayAdapter.createFromResource(
            //contexto suele ser la Activity
            requireContext(),
            //array de strings
            R.array.prioridad,
            //layout para mostrar el elemento seleccionado
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Layout para mostrar la apariencia de la lista
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // asignamos el adaptador al spinner
            binding.spPrioridad.adapter = adapter

            // El codigo siguiente es al seleccionar posicion 2 ( Alta) cambiamos el fondo
            // del constrait layout al color que tenemos en Colors (lo hemos definido nosotros
            //
            /*Hemos utilizado la posición para evitar problemas con la traducción de la app. Para obtener el
            valor es
            spPrioridad.getItemAtPosition(posicion)
            Si hubiéramos hecho los siguiente
            if(binding.spPrioridad.getItemAtPosition(posicion)=="Alta") {
            …
            }
            Si se traduce la app al inglés, la app no funcionaría en su versión en inglés
            */
            binding.spPrioridad.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, v: View?, posicion: Int, id: Long) {
            //el array son 3 elementos y "alta" ocupa la tercera posición
                    if(posicion==2){
                        binding.clytTarea.setBackgroundColor(requireContext().getColor(R.color.prioridad_alta))
                    }else{//si no es prioridad alta quitamos el color
                        binding.clytTarea.setBackgroundColor(Color.TRANSPARENT)
                    }

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding.clytTarea.setBackgroundColor(Color.TRANSPARENT)
                }
            }

        }

    }

    private fun iniciaSwPagado() {
        binding.swPagado.setOnCheckedChangeListener { _, isChecked ->
            //cambiamos el icono si está marcado o no el switch
            val imagen=if (isChecked) R.drawable.ic_pagado
            else R.drawable.ic_no_pagado
            //asignamos la imagen desde recursos
            binding.ivPagado.setImageResource(imagen)
        }
            //iniciamos a valor false
        binding.swPagado.isChecked=false
        binding.ivPagado.setImageResource(R.drawable.ic_no_pagado)
    }


    private fun iniciaRgEstado() {
        //listener de radioGroup
        binding.rgEstado.setOnCheckedChangeListener { _, checkedId ->
            val imagen= when (checkedId){//el id del RadioButton seleccionado
        //id del cada RadioButon
                R.id.rbAbierta-> R.drawable.ic_abierto
                R.id.rbEnCurso->R.drawable.ic_encurso
                else-> R.drawable.ic_cerrado
            }
            binding.ivEstado.setImageResource(imagen)
        }
        //iniciamos a abierto
        binding.rgEstado.check(R.id.rbAbierta)
    }

    private fun iniciaSbHoras() {
    //asignamos el evento
        binding.sbHoras.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progreso: Int, p2: Boolean) {
    //Mostramos el progreso en el textview
                binding.tvHoras.text=getString(R.string.horas_trabajadas,progreso)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    //inicio del progreso
        binding.sbHoras.progress=0
        binding.tvHoras.text=getString(R.string.horas_trabajadas,0)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
