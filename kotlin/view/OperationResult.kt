package view

sealed class OperationResult{
    abstract fun notifyUser(operation: String)
    object Success: OperationResult(){
        override fun notifyUser(operation: String) {
            println("$operation Success!!")
        }
    }
    object Failure: OperationResult(){
        override fun notifyUser(operation: String) {
            println("Sorry $operation Failed!!")
        }
    }
}
