package main

class Spot(
        var x: Int,
        var y: Int,
        var spotName: String,
        var isTaken: Boolean = false,
        var plateHere: String = ""
){
    fun ocuupySpot(plate: String){
        this.isTaken = false
        this.plateHere = plate
    }
}