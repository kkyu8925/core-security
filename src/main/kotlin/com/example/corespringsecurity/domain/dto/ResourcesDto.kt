package com.example.corespringsecurity.domain.dto

import com.example.corespringsecurity.domain.entity.Role

data class ResourcesDto(
    val id: String,
    val resourceName: String,
    val httpMethod: String,
    val orderNum: Int,
    val resourceType: String,
    val roleName: String,
    val roleSet: Set<Role>,
)
