package com.sales.store.dto

class UserDTO(id: Int?, username: String?, name: String?, lastName: String?, ) {
    var id: Int? = id
    var username: String? = username
    var name: String? = name
    var lastName: String? = lastName
    var fullName: String? = "$name $lastName"
}