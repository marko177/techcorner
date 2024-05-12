package com.sales.store.model

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "username", nullable = false)
    var username: String? = null

    @Column(name = "password", nullable = false)
    var password: String? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "lastname", nullable = false)
    var lastName: String? = null

    @Column(name = "role", nullable = false)
    var role: String? = null
}
