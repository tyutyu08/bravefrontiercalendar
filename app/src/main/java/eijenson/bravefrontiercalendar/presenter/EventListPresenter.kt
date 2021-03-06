package eijenson.bravefrontiercalendar.presenter

import eijenson.bravefrontiercalendar.message.RxBus
import eijenson.bravefrontiercalendar.ui.view.fragment.EventListFragment
import eijenson.bravefrontiercalendar.usecase.BraveNewsUseCase
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.io.IOException

/**
 * メイン画面のUIと画面遷移以外のことをする
 */
class EventListPresenter(private var eventListFragment: EventListFragment?) {

    private val useCase = BraveNewsUseCase()

    fun setHtml() = launch(UI) {
        try {
            val result = getHtmlAsync().await()
            RxBus.send(result)
        } catch (e: CancellationException) {
            eventListFragment?.showToast("canceled")
        } catch (e: IOException) {
            eventListFragment?.showToast("通信に失敗しました")
        } catch (e: Exception) {
            eventListFragment?.showToast("exception")
        } finally {
            eventListFragment?.hideProgressBar()
        }
    }

    fun onDestroy() {
        eventListFragment = null
    }


    private fun getHtmlAsync() = async(CommonPool) {
        try {
            return@async useCase.getHtml()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}