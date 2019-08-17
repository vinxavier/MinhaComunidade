package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.FeedFragmentBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class FeedFragment:Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addButton: FloatingActionButton


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)



        val binding = DataBindingUtil.inflate<FeedFragmentBinding>(inflater, R.layout.feed_fragment, container, false)

        populateProjetos(binding)

        binding.addButton.setOnClickListener{
            view: View -> view.findNavController().navigate(FeedFragmentDirections.action_feedFragment_to_novoProjetoInfosFragment2())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun populateProjetos(binding: FeedFragmentBinding) {
        viewManager = LinearLayoutManager(requireContext())
        viewAdapter = ProjetoAdapter()

        recyclerView = binding.recycleProjetos.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }


    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.feed_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out_action) {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(FeedFragmentDirections.action_feedFragment_to_landingFragment2())

            return true
        }

        return super.onOptionsItemSelected(item)
    }

}