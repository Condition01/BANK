package models

data class PremiumAccount(
    override var name: String,
    override var sex: Sex,
    override var accountNumber: String,
    override var pass: String,
    override var debitLimit: Double,
    override var money: Double = 0.0,
    override var movimentations: MutableMap<Long, Movimentation> = mutableMapOf()
) : Account