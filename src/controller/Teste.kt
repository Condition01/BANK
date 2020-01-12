package controller

import models.*
import javax.swing.JOptionPane

fun main(){
    var a = PremiumAccount("Carlão", Sex.MASCULINO, "1234", "master", -2000.0)
    a.movimentations.put(1, Movimentation("Casas Bahia", MovimentationType.DEBITO, -200.0))

//    a.movimentations.put("Casas Bahia - Debito  ", -350.0)
//    a.movimentations.put("Eletro Paulo - Debito ", -85.0)
//    a.movimentations.put("Carrefuor - Debito    ", -120.0)
//    a.movimentations.put("Uber - Credito        ", 200.0)
//
//    var total : Double = 0.0
//    for(item in a.movimentations){
//        total += item.value
//        println("${item.key}                      :${item.value}")
////    }
//    println("---------------------------------------------------")
//    println("Total                                       :$total")

//    var c = a.movimentations.toList()
//    c.forEach { println(it) }

//    a.movimentations.let { println(it) }
//    var t : String = JOptionPane.showInputDialog("Digite o valor")
//    println(t)
    var type = MovimentationType.values()
    println(type.get(1))

    var g = JOptionPane.showInputDialog("Digite o valor da movimentação").toDouble()
    println(g)



}

inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}