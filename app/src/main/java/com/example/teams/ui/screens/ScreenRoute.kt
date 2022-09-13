package com.example.teams.ui.screens

sealed class ScreenRoute(var route: String) {
    object CandidatesScreen : ScreenRoute("CandidateScreen")
    object DetailScreen : ScreenRoute("DetailScreen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
            /*  args.forEach { arg ->

            route += "/$arg"
        }
        return route
       */
        }

    }
}
