package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

/**
 * Created by 00700mm@gmail.com on 2019-08-13
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "feeds")
data class Feed(
        val title: String,
        val contents: String,
        @OneToMany(mappedBy = "feed")
        val comments: MutableList<FeedComment> = mutableListOf(),
        @ManyToMany
        @JoinTable(name = "feed_likes",
                joinColumns = [JoinColumn(name = "feed_id")],
                inverseJoinColumns = [JoinColumn(name = "like_id")])
        val likes: MutableSet<User> = mutableSetOf()
) : BaseEntity() {

    fun addComment(comment: FeedComment) {
        comment.feed = this
        comments.add(comment)
    }

    fun like(user: User) {
        likes.add(user)
    }

}

@Entity
@Table(name = "feed_comments")
data class FeedComment(
        @ManyToOne
        var feed: Feed,
        val contents: String
) : BaseEntity()

interface FeedRepository : JpaRepository<Feed, Long>

interface FeedCommentRepository : JpaRepository<FeedComment, Long>