package eijenson.bravefrontiercalendar.usecase

import android.util.Log
import eijenson.bravefrontiercalendar.message.RxBus
import eijenson.bravefrontiercalendar.model.BraveNews
import eijenson.bravefrontiercalendar.model.BraveNewsHeader
import eijenson.bravefrontiercalendar.model.Progress
import eijenson.bravefrontiercalendar.repository.scraping.BraveNewsDetailScraping
import eijenson.bravefrontiercalendar.repository.scraping.BraveNewsScraping
import eijenson.bravefrontiercalendar.repository.scraping.RegexUtil

/**
 * スクレイピングを指示するクラス
 */
class ScrapingUseCase {
    private val news = BraveNewsScraping()

    fun startScraping(): List<BraveNews> {
        RxBus.send(Progress(0, news.getTitleList().size))
        return news.getTitleList().zip(news.getUrlList()).mapIndexed { index, it ->
            RxBus.send(Progress(index + 1, news.getTitleList().size))
            getBraveNews(it.first, it.second)
        }
    }

    fun getHeaderList(): List<BraveNewsHeader> {
        //TODO:Pairじゃなくて専用のクラスを作る
        return news.getTitleList().zip(news.getUrlList())
                .map { BraveNewsHeader(it.first, it.second) }
    }

    fun getBraveNews(title: String, url: String): BraveNews {
        val newsDetail = BraveNewsDetailScraping(url)
        val timeList = RegexUtil.dateTime(newsDetail.period)
        sleep()
        return BraveNews(title = title,
                detail = newsDetail.report,
                period = newsDetail.period,
                url = url,
                startTime = timeList?.first(),
                endTime = timeList?.last())
    }

    private fun sleep() {
        Log.d("ScrapingManager", "start sleep")
        Thread.sleep(1000)
        Log.d("ScrapingManager", "stop sleep")
    }
}