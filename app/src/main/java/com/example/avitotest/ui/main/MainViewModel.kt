package com.example.avitotest.ui.main

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avitotest.model.CardData
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class MainViewModel : ViewModel() {

  val cardsData = MutableLiveData<List<CardData>>()
  private val deletedPull = MutableLiveData<List<CardData>>()

  private val handler = Handler()

  private val runnable: Runnable = Runnable {
    run {
      addCard()
      handler.postDelayed(runnable, 5000)
    }
  }

  init {
    val data = ArrayList<CardData>()
    for (i in 1..15) {
      data.add(CardData(i.toString()))
      cardsData.value = data.toList()
    }
    handler.postDelayed(runnable, 5000)
  }

  private fun addCard() {
    val data = ArrayList<CardData>()
    val deletedData = ArrayList<CardData>()

    if (cardsData.value != null) {
      data.addAll(cardsData.value!!)
    }
    if (deletedPull.value != null) {
      deletedData.addAll(deletedPull.value!!)
    }

    val position = if (data.isNotEmpty()) Random.nextInt(0, data.size) else 0

    if (deletedData.isNotEmpty()) {
      data.add(position, deletedData.removeLast())
    } else {
      data.add(position, CardData((data.size + 1).toString()))
    }

    cardsData.value = data.toList()
    deletedPull.value = deletedData.toList()
  }

  fun deleteCard(id: String) {
    val deleteHandler = Handler()
    deleteHandler.postDelayed({
      val data = ArrayList<CardData>()
      val deletedData = ArrayList<CardData>()

      if (cardsData.value != null) {
        data.addAll(cardsData.value!!)
      }
      if (deletedPull.value != null) {
        deletedData.addAll(deletedPull.value!!)
      }

      for (card in data) {
        if (card.id == id) {
          data.remove(card)
          deletedData.add(card)
          break
        }
      }
      cardsData.value = data.toList()
      deletedPull.value = deletedData.toList()
    }, 500)
  }

}