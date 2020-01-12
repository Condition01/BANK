package models

interface Account : Caractelizable {
    var money : Double
    var debitLimit: Double
    var movimentations: MutableMap<Long, Movimentation>
}