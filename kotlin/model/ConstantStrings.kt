package model

enum class ConstantStrings {
    SIGNUP{
        override val message: String
            get() = "Sign Up"
    },
    USERNAME{
        override val message: String
            get() = "userName"
    },
    PASSWORD{
        override val message: String
            get() = "password"
    },
    ADDRESS{
        override val message: String
            get() = "address"
    },
    DATEOFBIRTH {
        override val message: String
            get() = "dateOfBirth"
    },
    GENDER{
        override val message: String
            get() = "gender"
    },
    LOGIN{
        override val message: String
            get() = "Log In"
    },
    USERID{
        override val message: String
            get() = "userId"
    },
    DUMMY{
        override val message: String
            get() = ""
    },
    A{
        override val message: String
            get() = "A"
    },
    C{
        override val message: String
            get() = "C"
    },
    PASSWORDCHANGE {
        override val message: String
            get() = "Password Change"
    },
    ADDSTATION{
        override val message: String
            get() = "Add Station"
    },
    TRAINADDITION{
        override val message: String
            get() = "Train Addition"
    },
    STATIONNAME{
        override val message: String
            get() = "stationName"
    },
    CITY{
        override val message: String
            get() = "city"
    },
    DISTANCEFROMCAPECOMORIN{
        override val message: String
            get() = "distanceFromCapeComorin"
    },
    COACHNAME{
        override val message: String
            get() = "coachName"
    },
    NUMBEROFCOMPARTMENTS{
        override val message: String
            get() = "noOfCompartments"
    },
    TOTALSEATSINCOACH{
        override val message: String
            get() = "totalSeatsInCoach"
    },
    FAREPERKM{
        override val message: String
            get() = "farePerKm"
    },
    AVAILABLETICKETSINCOACH{
        override val message: String
            get() = "availableTicketsInCoach"
    },
    TICKETBOOKING{
        override val message: String
            get() = "Ticket Booking"
    },
    STARTINGSTATIONNAME{
        override val message: String
            get() = "startingStationName"
    },
    DESTINATIONSTATIONNAME{
        override val message: String
            get() = "destinationStationName"
    },
    STARTINGSTATIONID{
        override val message: String
            get() = "startingStationId"
    },
    DESTINATIONSTATIONID{
        override val message: String
            get() = "destinationStationId"
    },
    PASSENGERNAME {
        override val message: String
            get() = "passengerName"
    },
    PASSENGERAGE{
        override val message: String
            get() = "passengerAge"
    },
    SLEEPER{
        override val message: String
            get() = "SL"
    },
    SEATER{
        override val message: String
            get() = "SE"
    },
    AC{
        override val message: String
            get() = "AC"
    },
    ENTERVALIDINPUT {
        override val message: String
            get() = "Enter Valid Input"
    };
    abstract val message: String
}