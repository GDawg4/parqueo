package main

import java.io.File
import java.io.InputStream

fun showMenu(optionNumber: String): String{
    when (optionNumber){
        "0"->
        return("""
            Menu:
            Elija el tipo de usuario que desea utilizar
            1. Administrador
            2. Conductor
            3. Salir
        """.trimIndent())
    "1" ->
        return("""
            Menu:
            1. Crear nivel
            2. Eliminar nivel
            3. Ver todos los niveles
            4. Salir
        """.trimIndent())
    "2"->
        return("""
            Menu:
            1. Ingresar placa
            2. Salir
        """.trimIndent())
    }
    return ""
}

fun readLines(fileName: String){
    var mapAsLines: ArrayList<String> = ArrayList()
    val file: InputStream = File(fileName).inputStream()
    file.bufferedReader().useLines { lines->lines.forEach { mapAsLines.add(it)} }
}

fun main(args: Array<String>) {
    var levelList: ArrayList<Nivel>
    var wantsToContinueInProgram: Boolean = true
    var wantsToContinueAsUser: Boolean = true
    var wantsToContinueAsAdin: Boolean = true
    var adminOrUser: String

    //main.readLines("C:\\Users\\garoz\\Desktop\\2018 2\\parqueo\\prueba.txt")

    do {
        println(showMenu("1"))
        adminOrUser = readLine()!!
        if (adminOrUser == "1"){
        }

    }while (wantsToContinueInProgram)
}