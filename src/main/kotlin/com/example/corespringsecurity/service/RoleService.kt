package com.example.corespringsecurity.service

import com.example.corespringsecurity.domain.entity.Role

interface RoleService {

    fun getRole(id: Long): Role

    fun getRoles(): List<Role>

    fun createRole(role: Role)

    fun deleteRole(id: Long)
}
