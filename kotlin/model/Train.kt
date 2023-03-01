package model

import controller.TrainNumberGenerator

data class Train(val trainName: String,
                 val coaches: Set<Coach>,
                 val route:List<Connection>,
                 val trainNumber: Int = TrainNumberGenerator.getTrainId() )
