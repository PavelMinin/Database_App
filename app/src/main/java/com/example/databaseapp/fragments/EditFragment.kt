package com.example.databaseapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.databaseapp.appDatabase
import com.example.databaseapp.databinding.FragmentDatabaseEditBinding
import com.example.databaseapp.extension.getTextOrSetError
import com.example.databaseapp.room.RoomCharacter

class EditFragment : Fragment() {

    private var _binding: FragmentDatabaseEditBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val characterDao by lazy {
        requireContext().appDatabase.characterDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDatabaseEditBinding.inflate(inflater,container,false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showResult()

        with(binding) {

            buttonAdd.setOnClickListener {
                addData()
            }

            buttonEdit.setOnClickListener {
                editData()
            }

            buttonDelete.setOnClickListener { deleteData() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showResult() {
        with(binding) {
            textResult.text = characterDao.getAll().joinToString(separator = "\n")
            containerCharacter.error = null
            containerActor.error = null
        }
    }

    private fun findCharacterByName(characterName: String): RoomCharacter {
        return characterDao.loadByName(name = characterName)
    }

    private fun addData() {
        with(binding) {
            val characterName = containerCharacter.getTextOrSetError()
            val actorName = containerActor.getTextOrSetError()
            if(characterName == null || actorName == null) return@with

            if(findCharacterByName(characterName) != null) {
                Toast.makeText(
                    context,
                    "Character $characterName already exists",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                characterDao.insertAll(RoomCharacter(name = characterName, actor = actorName))
                showResult()
            }
        }
    }

    private fun editData() {
        with(binding) {
            val characterName = containerCharacter.getTextOrSetError()
            val actorName = containerActor.getTextOrSetError()
            if (characterName == null || actorName == null) return@with

            val characterToEdit = findCharacterByName(characterName)

            if (characterToEdit != null) {
                val characterEdited = RoomCharacter(
                    id = characterToEdit.id,
                    name = characterToEdit.name,
                    actor = actorName
                )
                characterDao.delete(characterToEdit)
                characterDao.insertAll(characterEdited)
                showResult()
            } else {
                Toast.makeText(
                    context,
                    "Character $characterName does not exist",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun deleteData() {
        with(binding) {
            val characterName = containerCharacter.getTextOrSetError()
            val actorName = containerActor.getTextOrSetError()
            if (characterName == null || actorName == null) return@with

            val characterToDelete = findCharacterByName(characterName)

            if (characterToDelete != null) {
                val characterToDelete = characterDao.loadByName(name = characterName)
                characterDao.delete(characterToDelete)
                showResult()
            } else {
                Toast.makeText(
                    context,
                    "Character $characterName does not exist",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}