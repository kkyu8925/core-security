package com.example.corespringsecurity.repository

import com.example.corespringsecurity.domain.entity.Resources
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ResourcesRepository : JpaRepository<Resources, Long> {

    fun findByResourceNameAndHttpMethod(resourceName: String, httpMethod: String): Optional<Resources>

    @Query("select r from Resources r join fetch r.roleSet where r.resourceType = 'url' order by r.orderNum desc")
    fun findAllResources(): List<Resources>

    @Query("select r from Resources r join fetch r.roleSet where r.resourceType = 'method' order by r.orderNum desc")
    fun findAllMethodResources(): List<Resources>

    @Query("select r from Resources r join fetch r.roleSet where r.resourceType = 'pointcut' order by r.orderNum desc")
    fun findAllPointcutResources(): List<Resources>
}
