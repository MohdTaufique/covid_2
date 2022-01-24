package com.example.covid22.model

data class State (
    var location: String,
    var confirmedCasesIndia: String,
    var confirmedCasesForeign: String,
    var discharged: String,
    var death: String,
    var totalCases: String
)