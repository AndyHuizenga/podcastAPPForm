package com.example.podcastonthego.services

import com.example.podcastonthego.dto.NewEpisodeRequest
import com.example.podcastonthego.dto.NewPodcastRequest
import com.example.podcastonthego.models.Episode
import com.example.podcastonthego.models.Podcast
import com.example.podcastonthego.models.User
import com.example.podcastonthego.repositories.EpisodeRepository
import com.example.podcastonthego.repositories.PodcastRepository
import com.example.podcastonthego.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal
import java.sql.SQLException
import java.util.*
import javax.servlet.http.HttpServletRequest


@Service
class PodcastService {

    @Autowired
    lateinit var episodeRepository: EpisodeRepository
    @Autowired
    lateinit var podcastRepository: PodcastRepository
    @Autowired
    lateinit var userRepository: UserRepository

    fun saveNewEpisode(ner: NewEpisodeRequest): Episode {

        var newEpisode = Episode(episodeName = ner.EpisodeName, audio_location = ner.audio_location )


        var podcastCheck = podcastRepository.findByName(ner.podcastName)


        if(podcastCheck.isPresent){
            newEpisode.inPodcast = (podcastCheck.get())

        } else {
            val newPodcast = podcastRepository.save(
                Podcast(podcastName = ner.podcastName, createdByUser = "user", coverIMG = "default.png", file_location = "default" )
            )
            newEpisode.inPodcast = (newPodcast)
        }

        var userCheck: Optional<User> = userRepository.findById(ner.UserId)

        if (userCheck.isPresent){
            newEpisode.createdByUser = userCheck.get()

        } else {

        }

        return episodeRepository.save(newEpisode)


    }



    fun getAllEpisodes():MutableList<Episode>{
        return episodeRepository.findAll()
    }

    fun getAllPodcast(): MutableList<Podcast>{
        return podcastRepository.findAll()
    }

    fun saveNewPodcast(podcast: NewPodcastRequest): Podcast {
        var newPodcast = Podcast(id = podcast.id, podcastName = podcast.podcastName, createdByUser = podcast.createdByUser, file_location = podcast.file_location, coverIMG = podcast.coverIMG)
        return podcastRepository.save(newPodcast)
    }



}
