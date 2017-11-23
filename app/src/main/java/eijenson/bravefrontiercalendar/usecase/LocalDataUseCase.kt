package eijenson.bravefrontiercalendar.usecase

import eijenson.bravefrontiercalendar.Application
import eijenson.bravefrontiercalendar.model.LocalData
import eijenson.bravefrontiercalendar.repository.repository.LocalRepository
import javax.inject.Inject

/**
 * Created by kobayashimakoto on 2017/11/23.
 */
class LocalDataUseCase {
    @Inject protected lateinit var repository: LocalRepository
    private val localData: LocalData

    init {
        Application.localComponent.inject(this)
        localData = repository.get()
    }

    fun isFirstStart(): Boolean {
        return localData.isFirstStart
    }

    fun finishFirstStart() {
        localData.isFirstStart = false
        repository.save(localData)
    }
}