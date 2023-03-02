package model

data class Passenger(val passengerName: String,val passengerAge: Short,val seatNumber: String){
    override fun toString(): String = "Passenger Name: $passengerName\nPassenger Age: $passengerAge\nSeat Number: $seatNumber"
}
