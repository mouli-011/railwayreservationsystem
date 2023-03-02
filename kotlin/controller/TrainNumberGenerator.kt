package controller

object TrainNumberGenerator {
    private var trainIdReference = 0
    fun getTrainId(): Int = trainIdReference++
}