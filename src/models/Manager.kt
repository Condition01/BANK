package models

data class Manager (
    override var name: String,
    override var sex: Sex,
    override var accountNumber: String,
    override var pass: String
): Caractelizable{
    init {
        this.accountNumber += "M"
    }
}
