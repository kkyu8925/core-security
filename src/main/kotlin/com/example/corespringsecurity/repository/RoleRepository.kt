package com.example.corespringsecurity.repository

import com.example.corespringsecurity.domain.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByRoleName(name: String): Optional<Role>
}
