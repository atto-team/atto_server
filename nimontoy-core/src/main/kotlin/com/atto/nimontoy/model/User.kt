package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.hibernate.annotations.NaturalId
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "users")
data class User(
        val name: String? = null,
        @ManyToMany
        @JoinTable(name = "user_roles",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")])
        val roles: Set<Role>? = null
) : BaseEntity()

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Enumerated(EnumType.STRING)
        @NaturalId
        val name: RoleName
)

enum class RoleName {
    ROLE_USER,
    ROLE_CREATOR,
    ROLE_ADMIN
}

interface UserRepository : JpaRepository<User, Long>

interface RoleRepository : JpaRepository<Role, Long>