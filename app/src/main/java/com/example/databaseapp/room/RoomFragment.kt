package com.example.databaseapp.room

import androidx.fragment.app.Fragment
import com.example.databaseapp.appDatabase


class RoomFragment : Fragment() {

    private var _binding: FragmentDatabaseBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val userDao by lazy {
        requireContext().appDatabase.characterDao()
    }
}