package com.example.corespringsecurity.service

import com.example.corespringsecurity.domain.entity.Resources

interface ResourcesService {

    fun getResources(id: Long): Resources

    fun getResources(): List<Resources>

    fun createResources(resources: Resources)

    fun deleteResources(id: Long)
}
