package com.example.corespringsecurity.domain.entity

import jakarta.persistence.*

@Entity
class Resources(

    @Column
    val resourceName: String,

    @Column
    val httpMethod: String,

    @Column
    val orderNum: Int,

    @Column
    val resourceType: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "role_resources",
        joinColumns = [JoinColumn(name = "resource_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roleSet: MutableSet<Role> = mutableSetOf()
) {

    @Id
    @GeneratedValue
    var id: Long = -1
        protected set
}
