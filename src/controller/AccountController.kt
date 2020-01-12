package controller

import models.Account
import models.Movimentation
import models.MovimentationType
import javax.swing.JOptionPane

class AccountController (var account : Account){
    fun drawMoney(valueToDraw: Double): Double?{
        if(account.money - valueToDraw >= account.debitLimit){
            account.money -= valueToDraw
            return valueToDraw
        }
        return null
    }

    fun putMoney(valueToPut: Double){
        account.money += valueToPut
    }

    fun printExtract(){
        var total = 0.0
        for(item in account.movimentations){
            println("${item.key} - ${item.value.companyName} -> ${item.value.movimentationType} :  ${item.value.movimentationValue}")
        }
        println("---------------------------------------------------")
        var situation = if(total < 0) "D" else "C"
        println("Total                                      :$total-$situation")

    }

    fun changePass(){
        println("Para trocar a senha digite a senha anterior")
        var pass = JOptionPane.showInputDialog("Por favor, informe a senha anterior nesse campo")
        if(pass.equals(account.pass)){
            putNewPass()
        }else{
           JOptionPane.showMessageDialog(null , "Senha não confere com a original")
        }
    }

    private fun putNewPass() {
        var pass = JOptionPane.showInputDialog("Digite a nova senha")
        account.pass = pass
        println("Senha alterada com sucesso!")
    }

    fun newMovimentation(){
        var company = JOptionPane.showInputDialog("Digite o nome da empresa")
        var type = chooseMovimentationType()
        var valor = JOptionPane.showInputDialog("Digite o valor da movimentação").toDouble()
        var m = Movimentation(company, type, valor)
        if(m.movimentationType.equals(MovimentationType.DEBITO) && m.movimentationValue > 0) m.movimentationValue *= -1
        handleMovimentation(m)
    }

    private fun handleMovimentation(m: Movimentation) {
        if(m.movimentationType.equals(MovimentationType.CREDITO) ||
            (account.money + m.movimentationValue >= account.debitLimit) ){
            insertMovimentation(m)
        }else{
            println("Não é possivel fazer essa movimentação em sua conta")
        }

    }

    private fun insertMovimentation(m: Movimentation) {
        if (account.movimentations.isEmpty()) {
            account.movimentations.put(1, m)
        } else {
            account.movimentations.put(account.movimentations.size.toLong()+1, m)
        }
        account.money += m.movimentationValue
    }

    fun chooseMovimentationType() : MovimentationType{
        var key = 1
        do {
            key = JOptionPane.showInputDialog("Digite 0 para movimentação de credito\n" +
                    "Digite 1 para movimentação de debito").toInt()
        }while (!(key in 0..1))
        var type = MovimentationType.values()
        return type.get(key)
    }

}