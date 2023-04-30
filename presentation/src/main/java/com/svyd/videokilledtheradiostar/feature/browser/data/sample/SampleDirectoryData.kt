package com.svyd.videokilledtheradiostar.feature.browser.data.sample

import com.google.gson.Gson
import com.svyd.data.repository.model.DirectoryEntity
import com.svyd.data.repository.model.mapper.DirectoryMapper
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory
import com.svyd.videokilledtheradiostar.feature.browser.model.mapper.UiDirectoryMapper

class SampleDirectoryData {

    fun directory(): UiDirectory {
        return UiDirectoryMapper().map(DirectoryMapper().map(getSampleDirectory(topFortyDirectory)))
    }

    fun rootDirectory(): UiDirectory {
        return UiDirectoryMapper().map(DirectoryMapper().map(getSampleDirectory(rootDirectory)))
    }

    private fun getSampleDirectory(path: String): DirectoryEntity {
        return Gson().fromJson(path, DirectoryEntity::class.java)
    }

    private val topFortyDirectory = "{\n" +
            "  \"head\": {\n" +
            "    \"title\": \"Top 40 & Pop Music\",\n" +
            "    \"status\": \"200\"\n" +
            "  },\n" +
            "  \"body\": [\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"text\": \"Local Stations (1)\",\n" +
            "      \"key\": \"local\",\n" +
            "      \"children\": [\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"audio\",\n" +
            "          \"text\": \"Zakarpattya FM (Transcarpathian region)\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?id=s112621\",\n" +
            "          \"bitrate\": \"128\",\n" +
            "          \"reliability\": \"99\",\n" +
            "          \"guide_id\": \"s112621\",\n" +
            "          \"subtext\": \"Закарпатська недержавна...\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"formats\": \"mp3\",\n" +
            "          \"item\": \"station\",\n" +
            "          \"image\": \"http://cdn-radiotime-logos.tunein.com/s112621q.png\",\n" +
            "          \"now_playing_id\": \"s112621\",\n" +
            "          \"preset_id\": \"s112621\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"text\": \"Stations\",\n" +
            "      \"key\": \"stations\",\n" +
            "      \"children\": [\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"audio\",\n" +
            "          \"text\": \"Y100 Michiana (US)\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?id=s209504\",\n" +
            "          \"bitrate\": \"320\",\n" +
            "          \"reliability\": \"99\",\n" +
            "          \"guide_id\": \"s209504\",\n" +
            "          \"subtext\": \"Dinah Jane - SZNS (feat. A Boogie Wit Da Hoodie)\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"formats\": \"mp3\",\n" +
            "          \"playing\": \"Dinah Jane - SZNS (feat. A Boogie Wit Da Hoodie)\",\n" +
            "          \"playing_image\": \"http://cdn-albums.tunein.com/gn/GL0X66VXPGq.jpg\",\n" +
            "          \"show_id\": \"p3084085\",\n" +
            "          \"item\": \"station\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/s209504/images/logoq.jpg?t=1\",\n" +
            "          \"current_track\": \"Brooke\",\n" +
            "          \"now_playing_id\": \"s209504\",\n" +
            "          \"preset_id\": \"s209504\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"audio\",\n" +
            "          \"text\": \"Liberation Radio Hits (UK)\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?id=s302061\",\n" +
            "          \"bitrate\": \"96\",\n" +
            "          \"reliability\": \"97\",\n" +
            "          \"guide_id\": \"s302061\",\n" +
            "          \"subtext\": \"Justin Bieber - Sorry\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"formats\": \"mp3\",\n" +
            "          \"playing\": \"Justin Bieber - Sorry\",\n" +
            "          \"playing_image\": \"http://cdn-albums.tunein.com/gn/KQ7HHKG6QDq.jpg\",\n" +
            "          \"item\": \"station\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/s302061/images/logoq.png\",\n" +
            "          \"now_playing_id\": \"s302061\",\n" +
            "          \"preset_id\": \"s302061\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"audio\",\n" +
            "          \"text\": \"100hitz Hot Hitz : Today's Hitz without rap! (US)\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?id=s111388\",\n" +
            "          \"bitrate\": \"128\",\n" +
            "          \"reliability\": \"98\",\n" +
            "          \"guide_id\": \"s111388\",\n" +
            "          \"subtext\": \"Imagine Dragons - Hot-Enemy\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"formats\": \"mp3\",\n" +
            "          \"playing\": \"Imagine Dragons - Hot-Enemy\",\n" +
            "          \"playing_image\": \"http://cdn-albums.tunein.com/gn/LH26QRTQNWq.jpg\",\n" +
            "          \"item\": \"station\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/s111388/images/logoq.png\",\n" +
            "          \"now_playing_id\": \"s111388\",\n" +
            "          \"preset_id\": \"s111388\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"audio\",\n" +
            "          \"text\": \"The Fox 99.9 (Sarnia, Canada)\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?id=s31121\",\n" +
            "          \"bitrate\": \"96\",\n" +
            "          \"reliability\": \"99\",\n" +
            "          \"guide_id\": \"s31121\",\n" +
            "          \"subtext\": \"Lizzo - SPECIAL\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"formats\": \"mp3\",\n" +
            "          \"playing\": \"Lizzo - SPECIAL\",\n" +
            "          \"playing_image\": \"http://cdn-albums.tunein.com/gn/5XZ6875JJ8q.jpg\",\n" +
            "          \"show_id\": \"p127002\",\n" +
            "          \"item\": \"station\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/s31121/images/logoq.jpg\",\n" +
            "          \"current_track\": \"Eve Morgan\",\n" +
            "          \"now_playing_id\": \"s31121\",\n" +
            "          \"preset_id\": \"s31121\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"More Stations\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Browse.ashx?offset=26&id=c57943&filter=s\",\n" +
            "          \"key\": \"nextStations\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"text\": \"Shows\",\n" +
            "      \"key\": \"shows\",\n" +
            "      \"children\": [\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Radio 1's Scott Mills Daily Podcast\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=p67936\",\n" +
            "          \"guide_id\": \"p67936\",\n" +
            "          \"subtext\": \"The podcast that walks the line between the...\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"item\": \"show\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/p67936/images/logoq.jpg?t=493\",\n" +
            "          \"current_track\": \"The podcast that walks the line between the...\",\n" +
            "          \"preset_id\": \"p67936\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Steve Wright’s Big Guests\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=p423\",\n" +
            "          \"guide_id\": \"p423\",\n" +
            "          \"subtext\": \"Daily highlights from Steve Wright's afternoon...\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"item\": \"show\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/p423/images/logoq.jpg?t=38\",\n" +
            "          \"current_track\": \"Daily highlights from Steve Wright's afternoon...\",\n" +
            "          \"preset_id\": \"p423\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Top 20 Countdown\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=p2181\",\n" +
            "          \"guide_id\": \"p2181\",\n" +
            "          \"subtext\": \"Ray McDonald surveys the Top 20 pop singles...\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"item\": \"show\",\n" +
            "          \"image\": \"http://cdn-radiotime-logos.tunein.com/p2181q.png\",\n" +
            "          \"current_track\": \"Ray McDonald surveys the Top 20 pop singles...\",\n" +
            "          \"preset_id\": \"p2181\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Ned & Josh\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=p779246\",\n" +
            "          \"guide_id\": \"p779246\",\n" +
            "          \"subtext\": \"We're two mates and we do a radio show.\",\n" +
            "          \"genre_id\": \"g61\",\n" +
            "          \"item\": \"show\",\n" +
            "          \"image\": \"http://cdn-profiles.tunein.com/p779246/images/logoq.png?t=4\",\n" +
            "          \"current_track\": \"We're two mates and we do a radio show.\",\n" +
            "          \"preset_id\": \"p779246\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"K92 Mornin' Thang\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=p421430\",\n" +
            "          \"guide_id\": \"p421430\",\n" +
            "          \"subtext\": \"The K92 Mornin' Thang airs weekdays from...\",\n" +
            "          \"genre_id\": \"g364\",\n" +
            "          \"item\": \"show\",\n" +
            "          \"image\": \"http://cdn-radiotime-logos.tunein.com/p421430q.png\",\n" +
            "          \"current_track\": \"The K92 Mornin' Thang airs weekdays from...\",\n" +
            "          \"preset_id\": \"p421430\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"More Shows\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Browse.ashx?offset=6&id=c57943&filter=p\",\n" +
            "          \"key\": \"nextShows\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Recent Episodes\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Browse.ashx?id=c57943&filter=p:topic\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"text\": \"Explore Top 40 & Pop Music\",\n" +
            "      \"key\": \"related\",\n" +
            "      \"children\": [\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"Most Popular\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Browse.ashx?id=c57943&filter=s:popular\",\n" +
            "          \"key\": \"popular\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"element\": \"outline\",\n" +
            "          \"type\": \"link\",\n" +
            "          \"text\": \"By Location\",\n" +
            "          \"URL\": \"http://opml.radiotime.com/Browse.ashx?id=c57943&pivot=country&filter=s\",\n" +
            "          \"key\": \"pivotLocation\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    private val rootDirectory = "{\n" +
            "  \"head\": {\n" +
            "    \"title\": \"Browse\",\n" +
            "    \"status\": \"200\"\n" +
            "  },\n" +
            "  \"body\": [\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"Local Radio\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=local\",\n" +
            "      \"key\": \"local\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"Music\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=music\",\n" +
            "      \"key\": \"music\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"Talk\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=talk\",\n" +
            "      \"key\": \"talk\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"Sports\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=sports\",\n" +
            "      \"key\": \"sports\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"By Location\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?id=r0\",\n" +
            "      \"key\": \"location\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"By Language\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=lang\",\n" +
            "      \"key\": \"language\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"element\": \"outline\",\n" +
            "      \"type\": \"link\",\n" +
            "      \"text\": \"Podcasts\",\n" +
            "      \"URL\": \"http://opml.radiotime.com/Browse.ashx?c=podcast\",\n" +
            "      \"key\": \"podcast\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n"

}