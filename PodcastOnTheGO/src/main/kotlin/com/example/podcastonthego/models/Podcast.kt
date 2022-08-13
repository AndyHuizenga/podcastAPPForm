package com.example.podcastonthego.models
import javax.persistence.*


@Entity
 data class Podcast(
    @Id
    @GeneratedValue
    val id: Long = -1,
    var podcastName: String?,
    var createdByUser: String?,
    var coverIMG: String?,
    var file_location: String?,
){



    @ManyToMany(mappedBy = "podcastsList", fetch = FetchType.EAGER)
    var admins: List<User> = listOf()


}