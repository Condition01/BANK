package view

import controller.AccountController
import controller.ManagerController
import models.Account
import models.Manager
import models.PremiumAccount
import models.Sex
import javax.swing.JOptionPane

val managers = setOf<Manager>(Manager(
    "Carlos", Sex.MASCULINO, "12345", "12345"
))

var accounts = setOf<PremiumAccount>(
    PremiumAccount("Carlinhos", Sex.MASCULINO, "12345",
    "12345", -2000.0))

fun main(){
    var key = 0
    do {
        key = JOptionPane.showInputDialog("Digite 1 para logar em uma conta basica\nDigite 2 para logar em uma conta gerencial").toInt()
    }while (!(key in 1..2))
    if(key == 2) managingLoginAttempt() else normalAccountLoginAttempt()
//    managers.forEach{println(it)}
}

fun normalAccountLoginAttempt() {
    var account : Account?
    do {
        var accNumber = JOptionPane.showInputDialog("Digite o numero da sua conta")
        var accPass = JOptionPane.showInputDialog("Digite a sua senha")
        account = accounts.find { it.accountNumber.equals(accNumber) && it.pass.equals(accPass)}
        println(account)
    }while (account == null)
    if(account!=null){
        accountAcess(account)
    }
}

fun accountAcess(account: Account) {
    val aC = AccountController(account)
    do {
        var n = JOptionPane.showInputDialog("Digite 1 para sacar um valor\nDigite 2 para colocar um valor na conta" +
                "\nDigite 3 para inserir uma nova movimentação\nDigite 4 para imprimir o extrato\n" +
                "Digite 5 para trocar a senha da conta").toInt()
        when{
            n == 1 -> drawMoney(aC)
            n == 2 -> aC.putMoney(JOptionPane.showInputDialog("Digite o valor que deseja que deseja colocar").toDouble())
            n == 3 -> aC.newMovimentation()
            n == 4 -> aC.printExtract()
            n == 5 -> aC.changePass()
            else -> println(account)
        }
    }while (n != 9)
}

fun drawMoney(aC: AccountController){
    var value : Double = JOptionPane.showInputDialog("Digite o valor que deseja sacar").toDouble()
    if (aC.drawMoney(value) == null){
        println("Infelizmente você não pode sacar, pois seu debto atingira o limite " +
                "${aC.account.debitLimit} -> Debito: ${aC.account.money}")
    }else{
        println("Saque realizado com sucesso no valor de $value")
    }
}

fun managingLoginAttempt(){
    var manager : Manager?
    do {
        var accNumber = JOptionPane.showInputDialog("Digite o numero da sua conta")
        var accPass = JOptionPane.showInputDialog("Digite a sua senha")
        manager = managers.find { it.accountNumber.equals(accNumber) && it.pass.equals(accPass)}
    }while (manager == null)
    if(manager!=null){
        managingAcess(manager)
    }
}

fun managingAcess(manager: Manager){
    val mG = ManagerController(manager)
    do {
        var n = JOptionPane.showInputDialog("Digite 1 para visualizar as contas no sistema" +
                "\nDigite 2 para visualizar as contas a partir de um filtro" +
                "\nDigite 9 para sair").toInt()
        when{
            n == 1 -> mG.vizualizeAccounts(accounts)
            n == 2 -> mG.vizualizeAccountsWithKey(accounts, JOptionPane.showInputDialog("Digite " +
                    "um filtro para visualizar as contas respectivas"))
        }
    }while (n != 9)
}
