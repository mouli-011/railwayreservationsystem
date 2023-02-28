package database

import model.*

object Database {
    val customers = mutableSetOf<Customer>()
    val admins = mutableSetOf<Admin>()
    val trains = mutableSetOf<Train>()
    val stations = mutableSetOf<Station>()
    val ticketsSold = mutableSetOf<Ticket>()
}