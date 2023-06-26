package com.example.corespringsecurity.domain.entity

import jakarta.persistence.*

@Entity
class Role(

    @Column
    val roleName: String,

    @Column
    val roleDesc: String,

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("ordernum desc")
    val resourcesSet: MutableSet<Resources> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    val accounts: MutableSet<Account> = mutableSetOf()
) {

    @Id
    @GeneratedValue
    var id: Long = -1
        protected set
}
