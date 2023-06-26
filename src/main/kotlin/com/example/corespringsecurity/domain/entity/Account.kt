package com.example.corespringsecurity.domain.entity

import jakarta.persistence.*

@Entity
class Account(

    @Column
    val username: String,

    @Column
    val password: String,

    @Column
    val email: String,

    @Column
    val age: Int,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "account_roles",
        joinColumns = [JoinColumn(name = "account_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val userRoles: MutableSet<Role> = mutableSetOf()
) {

    @Id
    @GeneratedValue
    var id: Long = -1
        protected set
}
