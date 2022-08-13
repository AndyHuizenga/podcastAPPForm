package com.example.podcastonthego.models


import javax.persistence.*


@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Long,
    var name: String,
    var password: String,


    )
{

    @ManyToMany(fetch = FetchType.EAGER)
    var podcastsList: List<Podcast> = listOf()



}