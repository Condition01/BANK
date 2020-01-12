package controller

import models.Account
import models.Manager

class ManagerController(manager: Manager) {
    fun vizualizeAccounts(contas : Set<Account>){
        contas.forEach { println(it) }
    }

    fun vizualizeAccountsWithKey(contas : Set<Account>, key: String){
        contas.filter { it.accountNumber.contains(key) }.forEach { println(it) }
    }
}