package com.tpadev

import com.lagradost.cloudstream3.DubStatus
import com.lagradost.cloudstream3.MainPageRequest

suspend fun main() {
    val api = MBoxProvider()
//    val mainPageData = api.mainPage.first()
//    val data =  api.getMainPage(1, MainPageRequest(mainPageData.name, mainPageData.data, true))

    val movieData = api.load("https://moviebox.id/detail/deadpool-and-wolverine-UmD1pHY6xU9?id=8319000940355404624&scene=&page_from=search_detail&type=/movie/detail&utm_source=")
    println(movieData.episodes)
    val urlLoad = api.loadLinks(
        movieData.episodes.values.first().first().data,
//        "https://fmoviesunblocked.net/wefeed-h5-bff/web/subject/play?subjectId=8319000940355404624&se=0&ep=0|https://moviebox.id/detail/deadpool-and-wolverine-UmD1pHY6xU9?id=8319000940355404624&scene=&page_from=search_detail&type=/movie/detail&utm_source=",
        false,
        {

        },
        {
            println(it)
        }
    )
//    ProviderTester(MovieBox())
//    providerTester.testLoad("https://moviebox.ng/movies/niyala-QEfogF0boQa?id=9102201097187460288&scene=&type=/movie/detail")
//    providerTester.testLoadLinks("https://moviebox.ng/wefeed-h5-bff/web/subject/play?subjectId=3089349649006742360&se=1&ep=1")
//    providerTester.testLoadLinks("https://moviebox.ng/wefeed-h5-bff/web/subject/play?subjectId=3989995742919283480&se=4&ep=1|https://moviebox.ng/movies/jack-ryan-qzDHqzOdKK4?id=3989995742919283480&scene=&page_from=suggestion&type=/movie/detail&utm_source=Hola")
//    data.first.forEach {
//        println(it.type)
//        println(it.url)
//        println(it.quality)
//    }
//    providerTester.testAll()
//    providerTester.testSearch("Handmaiden")
//    providerTester.testLoad("https://moviebox.ng/movies/bitch-x-rich-ifBHIf4G5X8?id=7514322883121141456&scene=&page_from=home_Top+20%F0%9F%94%A5&type=/movie/detail&utm_source=")
}
