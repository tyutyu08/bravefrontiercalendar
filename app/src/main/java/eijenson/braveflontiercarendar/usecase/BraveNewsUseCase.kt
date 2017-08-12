package eijenson.braveflontiercarendar.usecase

import android.content.Context
import eijenson.braveflontiercarendar.repository.orma.BraveNewsRepository
import eijenson.braveflontiercarendar.repository.scraping.ScrapingManager

/**
 * Created by kobayashimakoto on 2017/08/12.
 */
class BraveNewsUseCase(context: Context) {
    val repository = BraveNewsRepository(context)

    fun getHtml(): String {
        if (repository.countAll() == 0) {
            repository.insert(ScrapingManager().startScraping())
        }
        val braveNewsList = repository.selectAll()
        val result = braveNewsList.map { "${it.title}\n${it.period}" }
        return result.joinToString(separator = "\n\n")
    }
}